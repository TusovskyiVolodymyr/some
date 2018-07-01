package utils;

import consts.Constants;
import driver.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitManager {
    private final static Logger log = LogManager.getLogger();

    public static void waitElementToBePresent(WebElement webElement, int seconds) {
        log.info(String.format("Waiting for element with such locator: %s to be present in %d seconds", StringUtils.getXpathOfWebElement(webElement), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(StringUtils.getXpathOfWebElement(webElement))));
    }

    public static void waitElementToBePresent(WebElement webElement) {
        waitElementToBePresent(webElement, Constants.BASE_TIMEOUT);
    }

    public static void waitElementToBeVisible(WebElement webElement, int seconds) {
        log.info(String.format("Waiting for element visibility with such locator: %s to be present in %d seconds", StringUtils.getXpathOfWebElement(webElement), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    public static void waitElementToBeVisible(WebElement webElement) {
        waitElementToBeVisible(webElement, Constants.BASE_TIMEOUT);
    }

    public static void waitElementToBeNotPresent(WebElement webElement, int seconds) {
        log.info(String.format("Waiting for element dissapear with such locator: %s to be present in %d seconds", StringUtils.getXpathOfWebElement(webElement), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }

    public static void waitElementToBeNotPresent(WebElement webElement) {
        waitElementToBeNotPresent(webElement, Constants.BASE_TIMEOUT);
    }

    public static void waitUntilBeClickable(WebElement webElement, int seconds) {
        log.info(String.format("Waiting for element be clickable with such locator: %s in %d seconds", StringUtils.getXpathOfWebElement(webElement), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public static void waitUntillBeClickable(WebElement webElement) {
        waitUntilBeClickable(webElement, Constants.BASE_TIMEOUT);
    }
}