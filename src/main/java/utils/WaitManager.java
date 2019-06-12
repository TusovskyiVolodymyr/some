package utils;

import consts.Constants;
import driver.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitManager {

    private final static Logger log = LogManager.getLogger(WaitManager.class);

    public static void waitElementToBePresent(By by, int seconds) {
        log.info(String.format("Waiting for element with such locator: %s to be present in %d seconds", by.toString(), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        log.info(String.format("Element with locator: %s is present", by.toString()));
    }

    public static void waitElementToBePresent(By by) {
        waitElementToBePresent(by, Constants.BASE_TIMEOUT);
    }

    public static void waitElementToBeVisible(By by, int seconds) {
        log.info(String.format("Waiting for element visibility with such locator: %s to be present in %d seconds", by.toString(), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        log.info(String.format("Element with locator: %s is visible", by.toString()));
    }

    public static void waitElementToBeVisible(By by) {
        waitElementToBeVisible(by, Constants.BASE_TIMEOUT);
    }

    public static void waitElementToBeNotVisible(By by, int seconds) {
        log.info(String.format("Waiting for element disappear with such locator: %s in %d seconds", by.toString(), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
        log.info(String.format("Element with locator: %s disappear", by.toString()));
    }

    public static void waitElementToBeNotVisible(By by) {
        waitElementToBeNotVisible(by, Constants.BASE_TIMEOUT);
    }

    public static void waitUntilBeClickable(By by, int seconds) {
        log.info(String.format("Waiting for element be clickable with such locator: %s in %d seconds", by.toString(), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementClickInterceptedException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(by));
        log.info(String.format("Element with locator: %s is clickable ", by.toString()));
    }

    public static void waitUntilBeClickable(By by) {
        waitUntilBeClickable(by, Constants.BASE_TIMEOUT);
    }

    public static void waitUntilJSLoad() {
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(20))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementClickInterceptedException.class, StaleElementReferenceException.class);
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public static boolean isElementVisible(By by, int seconds) {
        boolean isVisible;
        try {
            waitElementToBeVisible(by, seconds);
            isVisible = true;
        } catch (TimeoutException e) {
            isVisible = false;
        }
        return isVisible;
    }

    public static boolean isElementVisible(By by) {
        return isElementVisible(by, Constants.BASE_TIMEOUT);
    }

    public static boolean isElementPresent(By by, int seconds) {
        boolean isVisible;
        try {
            waitElementToBePresent(by, seconds);
            isVisible = true;
        } catch (TimeoutException e) {
            isVisible = false;
        }
        return isVisible;
    }

    public static boolean isElementPresent(By by) {
        return isElementPresent(by, Constants.BASE_TIMEOUT);
    }

    public static boolean isElementInvisible(By by) {
        boolean isElementInvsisble;
        try {
            waitElementToBeNotVisible(by);
            isElementInvsisble = true;
        } catch (TimeoutException e) {
            isElementInvsisble = false;
        }
        return isElementInvsisble;
    }
}