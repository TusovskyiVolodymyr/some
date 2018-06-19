package utils;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;

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
}
