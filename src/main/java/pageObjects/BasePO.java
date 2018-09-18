package pageObjects;

import annotations.Instance;
import driver.WebDriverManager;
import elements.CustomFieldDecorator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.PropertiesUtill;
import utils.StringUtils;
import utils.WaitManager;

import java.io.IOException;
import java.util.Arrays;

import static utils.JSUtils.highlightElement;

public abstract class BasePO {

    private static Logger log = LogManager.getLogger(BasePO.class);
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

    protected BasePO click(WebElement webElement) {
        WaitManager.waitUntilJSLoad();
        WaitManager.waitUntillBeClickable(webElement);
        highlightElement(webElement);
        webElement.click();
        WaitManager.waitUntilJSLoad();
        log.info(String.format("Element with locator: %s was clicked!", StringUtils.getXpathOfWebElement(webElement)));
        return this;
    }

    protected BasePO typeText(WebElement webElement, String text) {
        WaitManager.waitUntilJSLoad();
        WaitManager.waitElementToBeVisible(webElement);
        highlightElement(webElement);
        webElement.sendKeys(text);
        WaitManager.waitUntilJSLoad();
        log.info(String.format("In element with locator: %s was typed: %s", StringUtils.getXpathOfWebElement(webElement), text));
        return this;
    }

    protected String getText(WebElement webElement) {
        WaitManager.waitUntilJSLoad();
        WaitManager.waitElementToBeVisible(webElement);
        highlightElement(webElement);
        String text = webElement.getText();
        log.info(String.format("Getting text from element with locator: %s text: %s", StringUtils.getXpathOfWebElement(webElement), webElement.getText()));
        return text;
    }

    protected String getInputValue(WebElement webElement) {
        WaitManager.waitElementToBeVisible(webElement);
        WaitManager.waitUntilJSLoad();
        String text = webElement.getAttribute("value");
        log.info(String.format("Getting text from input with locator: %s text: %s", StringUtils.getXpathOfWebElement(webElement), text));
        WaitManager.waitUntilJSLoad();
        return text;
    }
}
