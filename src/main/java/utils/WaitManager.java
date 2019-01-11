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
    }

    public static void waitElementToBeVisible(By by) {
        waitElementToBeVisible(by, Constants.BASE_TIMEOUT);
    }

    public static void waitElementToBeNotPresent(By by, int seconds) {
        log.info(String.format("Waiting for element dissapear with such locator: %s to be present in %d seconds", by.toString(), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NotFoundException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public static void waitElementToBeNotPresent(By by) {
        waitElementToBeNotPresent(by, Constants.BASE_TIMEOUT);
    }

    public static void waitUntilBeClickable(By by, int seconds) {
        log.info(String.format("Waiting for element be clickable with such locator: %s in %d seconds", by.toString(), seconds));
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementClickInterceptedException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public static void waitUntilBeClickable(By by) {
        waitUntilBeClickable(by, Constants.BASE_TIMEOUT);
    }

    public static void waitUntilJSLoad() {
        Wait<WebDriver> wait = new FluentWait<>(WebDriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(ElementClickInterceptedException.class, StaleElementReferenceException.class);
        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }
}