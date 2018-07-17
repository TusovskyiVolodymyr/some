package listener;

import driver.WebDriverManager;
import io.qameta.allure.Attachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private final static Logger log = LogManager.getLogger();

    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        log.info("Failure");
        attachScreenshot();
//        File scrFile = ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
//        InputStream inputStream = null;
//        try {
//            inputStream = new FileInputStream(scrFile);
//        Files.copyFile(inputStream, new File("errorScreenshots\\" + iTestResult.getName() + "-"
//                + Arrays.toString(iTestResult.getParameters()) +  ".jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

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
    public byte[] attachScreenshot(){
        return ((TakesScreenshot) WebDriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
