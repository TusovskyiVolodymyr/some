package utils;

import driver.WebDriverManager;
import elements.IElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSUtils {
    public static String highlightElement(WebElement element) {
        String color = element.getCssValue("background-Color");
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        if (element instanceof IElement) {
            js.executeScript("arguments[0].style.backgroundColor = 'orange';", ((IElement) element).getWebElement());
        } else {
            js.executeScript("arguments[0].style.backgroundColor = 'orange';", element);
        }
        WaitManager.waitUntilJSLoad();
        return color;
    }

    public static void unHighlightElement(WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        if (element instanceof IElement) {
            js.executeScript("arguments[0].style.backgroundColor = '" + color + "';", ((IElement) element).getWebElement());
        } else {
            js.executeScript("arguments[0].style.backgroundColor = '" + color + "';", element);
        }
    }
}
