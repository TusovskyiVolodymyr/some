package pageObjects;

import annotations.Instance;
import driver.WebDriverManager;
import elements.CustomFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import utils.LoggerWrapper;
import utils.PropertiesUtill;
import utils.WaitManager;

import java.io.IOException;
import java.util.Arrays;

public abstract class BasePO {

    protected LoggerWrapper log = LoggerWrapper.getLogger(this.getClass());
    protected PropertiesUtill utill;

    protected BasePO() {
        PageFactory.initElements(new CustomFieldDecorator(WebDriverManager.getDriver()), this);
        try {
            utill = new PropertiesUtill();
        } catch (IOException e) {
            log.error(Arrays.toString(e.getStackTrace()));
        }
        Instance.create(this);
    }

    protected void click(By by) {
        WaitManager.waitElementToBeVisible(by);
        WaitManager.waitUntilBeClickable(by);
        WebDriverManager.getDriver().findElement(by).click();
        log.info(String.format("Clicked on element with locator: [%s]", by.toString()));
    }

    protected void typeText(By by, String text) {
        WaitManager.waitElementToBeVisible(by);
        WaitManager.waitUntilBeClickable(by);
        WebDriverManager.getDriver().findElement(by).sendKeys(text);
        log.info(String.format("Typed text on element with locator: [%s], text: [%s] ", by.toString(), text));
    }
}
