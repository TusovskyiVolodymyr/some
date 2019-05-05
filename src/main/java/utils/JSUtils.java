package utils;

import driver.WebDriverManager;
import elements.IElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JSUtils {
    public static String highlightElement(WebElement element, String hightlightColor) {
        WaitManager.waitUntilJSLoad();
        String color = element.getCssValue("background-Color");
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        if (element instanceof IElement) {
            js.executeScript("arguments[0].style.backgroundColor = '" + hightlightColor + "';", ((IElement) element).getWebElement());
        } else {
            js.executeScript("arguments[0].style.backgroundColor = '" + hightlightColor + "';", element);
        }
        WaitManager.waitUntilJSLoad();
        return color;
    }

    public static String highlightElement(By by) {
        WebElement webElement = WebDriverManager.getDriver().findElement(by);
        return highlightElement(webElement, "orange");
    }

    public static void unHighlightElement(WebElement element, String color) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverManager.getDriver();
        if (element instanceof IElement) {
            js.executeScript("arguments[0].style.backgroundColor = '" + color + "';", ((IElement) element).getWebElement());
        } else {
            js.executeScript("arguments[0].style.backgroundColor = '" + color + "';", element);
        }
    }

    public static void unHighlightElement(By by, String color) {
        WebElement webElement = WebDriverManager.getDriver().findElement(by);
        unHighlightElement(webElement, color);
    }
}
