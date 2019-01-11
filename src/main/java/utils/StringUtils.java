package utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static String getXpathOfWebElement(WebElement webElement) {
        String locator = null;
        Pattern patternXpath = Pattern.compile("(xpath:.(.*))|(elector:.(.*))");
        Matcher matcher = patternXpath.matcher(webElement.toString());
        if (webElement.toString().contains("xpath") && matcher.find()) {
            locator = matcher.group(2);
        } else if (webElement.toString().contains("css") && matcher.find()) {
            locator = matcher.group(4);
        }
        return locator.substring(0, locator.length() - 1);
    }

    public static By getLocator(By by, Object... params) {
        By newLocator = null;
        String expression = by.toString().replaceFirst(".*: ", "");
        if (by.toString().startsWith("By.xpath")) {
            newLocator = By.xpath(String.format(expression, params));
        } else if (by.toString().startsWith("By.name")) {
            newLocator = By.name(String.format(expression, params));
        } else if (by.toString().startsWith("By.id")) {
            newLocator = By.id(String.format(expression, params));
        } else if (by.toString().startsWith("By.className")) {
            newLocator = By.className(String.format(expression, params));
        } else if (by.toString().startsWith("By.cssSelector")) {
            newLocator = By.cssSelector(String.format(expression, params));
        }
        return newLocator;
    }

}
