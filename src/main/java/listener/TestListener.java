package listener;

import driver.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TestListener implements ITestListener {
    private final static Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        attachlogFie(iTestResult);
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.error("Failure", iTestResult.getThrowable());
        attachScreenshot();
        attachlogFie(iTestResult);
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        log.error("Failure", iTestResult.getThrowable());
        attachScreenshot();
        attachlogFie(iTestResult);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {

    }

    @Attachment(type = "image/png")
    private byte[] attachScreenshot() {
        log.warn("Attaching screen shot...");
        return ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(type = "text/plain")
    private byte[] attachlogFie(ITestResult iTestResult) {
        byte[] array = null;
        try {
            array = Files.readAllBytes(new File("logs\\" + iTestResult.getMethod().getMethodName()
                    + iTestResult.getTestClass().getName() +
                    ".log").toPath());
        } catch (IOException e) {
            log.error("Some failure during log file attaching" +
                    iTestResult.getMethod().getMethodName() + iTestResult.getTestName(), e);
        }
        return array;
    }
}
