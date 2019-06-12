package listener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.WebDriverProperties;

public class TestListener implements ITestListener {
    private final static Logger log = LogManager.getLogger(TestListener.class);

    @Override
    public void onTestStart(ITestResult iTestResult) {
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        log.info(String.format("***** Test [%s] started *****", iTestContext.getName()));
        WebDriverProperties.initProperties();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }
}