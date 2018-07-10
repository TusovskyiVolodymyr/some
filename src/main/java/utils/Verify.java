package utils;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Verify {
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
}
