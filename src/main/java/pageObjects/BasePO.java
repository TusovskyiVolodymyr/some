package pageObjects;

import driver.WebDriverManager;
import elements.CustomFieldDecorator;
import elements.IElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utils.Instance;
import utils.PropertiesUtill;
import utils.StringUtils;
import utils.WaitManager;

import java.io.IOException;

public abstract class BasePO {

    private static final Logger log = LogManager.getLogger(BasePO.class);

    protected PropertiesUtill utill;

    protected BasePO() {
        PageFactory.initElements(new CustomFieldDecorator(WebDriverManager.getDriver()), this);
        try {
            utill = new PropertiesUtill();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Instance.create(this);
    }

    protected BasePO click(WebElement webElement) {
        WaitManager.waitUntilJSLoad();
        WaitManager.waitUntillBeClickable(webElement);
//        String color = highlightElement(webElement);
        webElement.click();
        WaitManager.waitUntilJSLoad();
//        unHighlightElement(webElement, color);
        log.info(String.format("Element with locator: %s was clicked!", StringUtils.getXpathOfWebElement(webElement)));
        return this;
    }

    protected BasePO click(IElement webElement) {
//        WaitManager.waitUntilJSLoad();
//        WaitManager.waitUntillBeClickable(webElement);
//        String color = highlightElement(webElement);
        webElement.click();
//        WaitManager.waitUntilJSLoad();
//        unHighlightElement(webElement, color);
//        log.info(String.format("Element with locator: %s was clicked!", StringUtils.getXpathOfWebElement(webElement)));
        return this;
    }

    protected BasePO typeText(WebElement webElement, String text) {
//        String s = highlightElement(webElement);
        WaitManager.waitUntilJSLoad();
        webElement.sendKeys(text);
        WaitManager.waitUntilJSLoad();
//        unHighlightElement(webElement, s);
        log.info(String.format("In element with locator: %s was typed: %s", StringUtils.getXpathOfWebElement(webElement), text));
        return this;
    }

    protected String getText(WebElement webElement) {
//        String s = highlightElement(webElement);
        String text = webElement.getText();
//        unHighlightElement(webElement, s);
        log.info(String.format("Getting text from element with locator: %s text: %s", StringUtils.getXpathOfWebElement(webElement), webElement.getText()));
        return text;
    }

    protected BasePO ver_textPresent(WebElement element, String text) {
        String color = highlightElement(element);
        Assert.assertEquals(element.getText(), (text));
        unHighlightElement(element, color);
        return this;
    }

    protected BasePO ver_ElementIsPresent(WebElement webElement) {
        WaitManager.waitElementToBePresent(webElement);
//        String c = highlightElement(webElement);
        log.info("Verifying element with locator: " + StringUtils.getXpathOfWebElement(webElement));
        Assert.assertTrue(webElement.isDisplayed());
//        unHighlightElement(webElement, c);
        return this;
    }


    protected String highlightElement(WebElement element) {
        String color = element.getCssValue("background-Color");
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript("arguments[0].style.backgroundColor = 'orange';", element);
        return color;
    }

    protected void unHighlightElement(WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "';", element);
    }
}
