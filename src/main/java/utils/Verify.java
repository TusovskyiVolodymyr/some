package utils;

import static utils.JSUtils.highlightElement;

import driver.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Verify {

    private final static Logger log = LogManager.getLogger(Verify.class);

    public static void elementPresent(By by) {
        boolean isPresent = WaitManager.isElementPresent(by);
        try {
            Assert.assertTrue(isPresent, "Element with locator: " + by.toString() + "is present!");
            highlightElement(WebDriverManager.getDriver().findElement(by), "green");
            log.info("ASSERTED: " + by.toString()
                    + "expected visible [true], found [" + isPresent + "]");
        } catch (AssertionError assertionError) {
            log.error("ASSERTION FAILED: " + by.toString() + "expected visible [true], but found ["
                    + isPresent + "]");
            throw assertionError;
        }
    }

    public static void elementVisible(By by) {
        boolean isVisible = WaitManager.isElementVisible(by, 5);
        try {
            Assert.assertTrue(isVisible, "Element with locator: " + by.toString() + " is visible");
            highlightElement(WebDriverManager.getDriver().findElement(by), "green");
            log.info("ASSERTED: " + by.toString()
                    + "expected visible [true], found [" + isVisible + "]");
        } catch (AssertionError assertionError) {
            log.error("ASSERTION FAILED: " + by.toString() + "expected visible [true], but found ["
                    + isVisible + "]");
            throw assertionError;
        }
    }

    public static void elementNotVisible(By by) {
        boolean isNotVisible = WaitManager.isElementInvisible(by);
        try {
            Assert.assertTrue(isNotVisible, "Element with locator: " + by.toString() + " is visible");
            highlightElement(WebDriverManager.getDriver().findElement(by), "green");
            log.info("ASSERTED: " + by.toString()
                    + "expected visible [true], found [" + isNotVisible + "]");
        } catch (AssertionError assertionError) {
            log.error("ASSERTION FAILED: " + by.toString() + "expected visible [true], but found ["
                    + isNotVisible + "]");
            throw assertionError;
        }
    }

    public static void textPresent(WebElement element, String expectedText) {
        String text = element.getText();
        boolean isTextPresent = text.equals(expectedText);
        try {
            Assert.assertTrue(isTextPresent);
            log.info("ASSERTED: [EXPECTED] " + StringUtils.getXpathOfWebElement(element)
                    + "text: [" + expectedText + "], found: [" + text + "]");
        } catch (AssertionError assertionError) {
            log.error("ASSERTION FAILED: [EXPECTED] "
                    + StringUtils.getXpathOfWebElement(element) + "text: [" + expectedText + "] but found: ["
                    + text + "]");
            throw assertionError;
        }
    }

    public static void textPresent(By by, String expectedText) {
        WebElement webElement = WebDriverManager.getDriver().findElement(by);
        textPresent(webElement, expectedText);
    }

    public static void isTrue(boolean isTrue, String message) {
        try {
            Assert.assertTrue(isTrue, message);
            log.info("ASSERTED: [EXPECTED] " + message + " expected [true], found: [" + isTrue + "]");
        } catch (AssertionError assertionError) {
            log.error("ASSERTION FAILED" + message + " expected [true], but found: [" + isTrue + "]");
            throw assertionError;
        }
    }
}
