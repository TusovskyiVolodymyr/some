package listener;

import driver.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TestListener implements ITestListener {
    private final static Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
        log.info("test start");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println(String.format("Current thread name : [%s] - current method name: [%s]"
                , Thread.currentThread().getName(), iTestResult.getMethod().getMethodName()));
        sortLogFiles(iTestResult);
        attachlogFie(iTestResult);

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error(ExceptionUtils.getStackTrace(iTestResult.getThrowable()));
        attachScreenshot();
        sortLogFiles(iTestResult);
        attachlogFie(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.error(ExceptionUtils.getStackTrace(iTestResult.getThrowable()));
        attachScreenshot();
        sortLogFiles(iTestResult);
        attachlogFie(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info("Started.......");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("finished.......");
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
                    line = line.replaceAll(threadName + " ", "").concat("\n");
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
}