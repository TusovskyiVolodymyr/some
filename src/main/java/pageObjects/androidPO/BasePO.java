package pageObjects.androidPO;

import annotations.AnnotationProcessor;
import annotations.Instance;
import consts.Constants;
import driver.WebDriverManager;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import utils.LoggerWrapper;
import utils.WaitManager;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public abstract class BasePO {

    protected LoggerWrapper log = LoggerWrapper.getLogger(this.getClass());

    protected BasePO() {
        AnnotationProcessor.proceed(this);
        Instance.create(this);
    }

    protected void click(By by) {
        WaitManager.waitUntilBeClickable(by);
        WebDriverManager.getDriver().findElement(by).click();
        log.info(String.format("Clicked on element with locator: [%s]", by.toString()));
    }

    protected void typeText(By by, String text) {
        for (int i = 1; i <= 3; i++) {
            try {
                WaitManager.waitElementToBeVisible(by);
                WaitManager.waitUntilBeClickable(by);
                WebElement input = WebDriverManager.getDriver().findElement(by);
                //click on input to set focus on it and prevent missing of first characters from typed string
                input.click();
                input.sendKeys(text);
                log.info(String.format("Typed text on element with locator: [%s], text: [%s] ", by.toString(), text));
                return;
            } catch (StaleElementReferenceException e) {
                System.out.println(String.format("Attempt #[%d] to type text failed", i));
            }
        }
    }

    public String getInputValue(By by) {
        WaitManager.waitElementToBeVisible(by);
        WaitManager.waitUntilBeClickable(by);
        WebElement input = WebDriverManager.getDriver().findElement(by);
        String inputValue = input.getAttribute("value");
        log.info(String.format("Getting input value from element with locator: [%s], value: [%s] ", by.toString(), inputValue));
        return inputValue;
    }

    protected void scrollTo(By by) {
        TouchAction ta = new TouchAction((AndroidDriver) Objects.requireNonNull(WebDriverManager.getDriver()));
        WebDriverManager.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        while (!WaitManager.isElementVisible(by, 0)) {
            ta.press(PointOption.point(WebDriverManager.getDriver().manage().window().getSize().getWidth() / 2, WebDriverManager.getDriver().manage().window().getSize().getHeight() / 2))
                    .waitAction().moveTo(PointOption.point(100, 100)).release().perform();
        }
        WebDriverManager.getDriver().manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
    }

    public void longPress(By by) {
        TouchAction touchAction = new TouchAction((AndroidDriver) Objects.requireNonNull(WebDriverManager.getDriver()));
        touchAction.longPress(LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(WebDriverManager.getDriver().findElement(by)))
                .withDuration(Duration.ofSeconds(8)))
                .release()
                .perform();
    }
}
