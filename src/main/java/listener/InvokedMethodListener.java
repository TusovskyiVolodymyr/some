package listener;

import driver.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InvokedMethodListener implements IInvokedMethodListener {

    private final static Logger log = Logger.getLogger(InvokedMethodListener.class);

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            getResult(testResult);
            if (testResult.getThrowable() != null) {
                log.error(ExceptionUtils.getStackTrace(testResult.getThrowable()));
                attachScreenshot();
            }
            sortLogFiles(testResult);
            attachlogFie(testResult);
        }
    }

    @Attachment(type = "image/png")
    private byte[] attachScreenshot() {
        log.warn("Attaching screen shot...");
        return ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(type = "text/plain")
    private byte[] attachlogFie(ITestResult iTestResult) {
        log.info("Attaching log file ...");
        byte[] array = null;
        try {
            File file = new File("logs\\".concat(iTestResult.getTestClass().getName()).concat("\\")
                    .concat(iTestResult.getMethod().getMethodName()).concat(".log"));
            array = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            log.error("Some failure during log file attaching" +
                    iTestResult.getMethod().getMethodName() + iTestResult.getTestName(), e);
        }
        return array;
    }

    private void sortLogFiles(ITestResult iTestResult) {
        log.info("Sorting log files...");
        String threadName = Thread.currentThread().getName();
        List<String> generalLogFile = new ArrayList<>();
        String directory = "logs\\" + iTestResult.getTestClass().getName();
        if (Files.notExists(Paths.get(directory))) {
            try {
                Files.createDirectories(Paths.get(directory));
            } catch (IOException e) {
                log.error("Failed to create directory : " + directory, e);
            }
        }
        File file = new File(directory.concat("\\").concat(iTestResult.getMethod().getMethodName()).concat(".log"));
        try (BufferedReader br = new BufferedReader(new FileReader("logs\\log_file.log"));
             FileWriter fr = new FileWriter(file)) {
            while (br.ready()) {
                generalLogFile.add(br.readLine());
            }
            for (String line : generalLogFile) {
                if (line.startsWith(threadName)) {
                    line = line.concat("\n");
                    fr.write(line);
                }
            }
            if (iTestResult.getThrowable() != null) {
                fr.write(ExceptionUtils.getStackTrace(iTestResult.getThrowable()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getResult(ITestResult iTestResult) {
        String result = null;
        try {
            Method method = TestResult.class.getDeclaredMethod("toString", int.class);
            method.setAccessible(true);
            result = (String) method.invoke(null, iTestResult.getStatus());
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        log.info(String.format("***** Test [%s] finished with result: [%s] *****", iTestResult.getMethod().getMethodName(), result));
    }
}
