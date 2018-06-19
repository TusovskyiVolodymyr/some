package pageObjects;

import driver.BrowserType;
import driver.DriverFactory;
import elements.CustomFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import utils.Instance;
import utils.PropertiesUtill;
import utils.StringUtils;

import java.io.IOException;
import java.time.Duration;

public abstract class BasePO {

    private static final Logger log = LogManager.getLogger(BasePO.class);

    protected WebDriver driver;

    protected PropertiesUtill utill;

    protected BasePO() {
        Instance.create(this);
        driver = DriverFactory.getWebDriver(BrowserType.CHROME);
//        PageFactory.initElements(driver, this);
        PageFactory.initElements(new CustomFieldDecorator(driver), this);
        try {
            utill = new PropertiesUtill();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected BasePO click(WebElement webElement) {
        String color = highlightElement(webElement);
        webElement.click();
        unHighlightElement(webElement, color);
        log.info(String.format("Element with locator: %s was clicked!", StringUtils.getXpathOfWebElement(webElement)));
        return this;
    }

    protected BasePO typeText(WebElement webElement, String text) {
        String s = highlightElement(webElement);
        webElement.sendKeys(text);
        unHighlightElement(webElement, s);
        log.info(String.format("In element with locator: %s was typed: %s", StringUtils.getXpathOfWebElement(webElement), text));
        return this;
    }

    protected String getText(WebElement webElement){
        String s = highlightElement(webElement);
        String text = webElement.getText();
        unHighlightElement(webElement, s);
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
        waitELementToBePresent(webElement, 5);
        String c = highlightElement(webElement);
        log.info("Verifying element with locator: " + StringUtils.getXpathOfWebElement(webElement));
        Assert.assertTrue(webElement.isDisplayed());
        unHighlightElement(webElement, c);
        return this;
    }

    protected BasePO waitELementToBePresent(WebElement webElement, int seconds) {
        log.info(String.format("Waiting for element with such locator: %s to be present in %d seconds", StringUtils.getXpathOfWebElement(webElement), seconds));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(StringUtils.getXpathOfWebElement(webElement))));
        return this;
    }

    protected BasePO waitELementToBeVisible(WebElement webElement, int seconds) {
        log.info(String.format("Waiting for element visibility with such locator: %s to be present in %d seconds", StringUtils.getXpathOfWebElement(webElement), seconds));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return this;
    }

    protected BasePO waitELementToBeNotPresent(WebElement webElement, int seconds) {
        log.info(String.format("Waiting for element dissapear with such locator: %s to be present in %d seconds", StringUtils.getXpathOfWebElement(webElement), seconds));
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
        return this;
    }

    protected String highlightElement(WebElement element) {
        String color = element.getCssValue("background-Color");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = 'orange';", element);
        return color;
    }

    protected void unHighlightElement(WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "';", element);
    }
}
