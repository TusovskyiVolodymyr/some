package utils;

import driver.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static utils.JSUtils.highlightElement;

public class Verify {

    private final static Logger log = LogManager.getLogger(Verify.class);

    public static boolean elementPresent(WebElement webElement) {
        boolean isPresent = webElement != null;
        Assert.assertTrue(isPresent, "Element with locator: " + StringUtils.getXpathOfWebElement(webElement) + "is present!");
        return isPresent;
    }

    public static boolean elementVisible(WebElement webElement) {
        boolean isVisible = webElement.isDisplayed();
        try {
            Assert.assertTrue(isVisible, "Element with locator: " + StringUtils.getXpathOfWebElement(webElement) + "is visible!");
            highlightElement(webElement, "green");
            log.info("ASSERTED: " + StringUtils.getXpathOfWebElement(webElement)
                    + "expected visible [true], found [" + isVisible + "]");
        } catch (AssertionError assertionError) {
            log.error("ASSERTION FAILED: " + StringUtils.getXpathOfWebElement(webElement) + "expected visible [true], but found ["
                    + isVisible + "]");
            highlightElement(webElement, "red");
            throw new AssertionError();
        }
        return isVisible;
    }

    public static boolean elementVisible(By by) {
        WebElement webElement = WebDriverManager.getDriver().findElement(by);
        return elementVisible(webElement);
    }

    public static boolean elementNotVisible(WebElement webElement) {
        boolean isVisible = webElement.isDisplayed();
        Assert.assertFalse(isVisible, "Element with locator: "
                + StringUtils.getXpathOfWebElement(webElement) + "is visible!");
        return isVisible;
    }

    public static boolean textPresent(WebElement element, String expectedText) {
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
        }
        return isTextPresent;
    }

    public static boolean isTrue(boolean isTrue, String message) {
        try {
            Assert.assertTrue(isTrue, message);
            log.info("ASSERTED: [EXPECTED] " + message + "expected [true], found: [" + isTrue + "]");
        } catch (AssertionError assertionError) {
            log.error("ASSERTION FAILED" + message + "expected [true], but found: [" + isTrue + "]");
        }
        return isTrue;
    }
}
