package utils;

import io.qameta.allure.Step;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

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
        } catch (AssertionError assertionError) {
            System.out.println("Is assertion error do smht");
        }
        return isVisible;
    }

    public static boolean elementNotVisible(WebElement webElement) {
        boolean isVisible = webElement.isDisplayed();
        Assert.assertFalse(isVisible, "Element with locator: " + StringUtils.getXpathOfWebElement(webElement) + "is visible!");
        return isVisible;
    }

    public static boolean textPresent(WebElement element, String text) {
        Assert.assertEquals(element.getText(), text, "text");
        return true;
    }

    public static boolean isTrue(boolean isTrue) {
        Assert.assertTrue(isTrue, "condition is true");
        return true;
    }

    @Step("message")
    public static void info(String message) {
        log.info(message);
    }

}
