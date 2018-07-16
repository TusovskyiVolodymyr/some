package driver;

import consts.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.WebDriverProperties;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriverProperties webDriverProperties;

    static {
        try {
            webDriverProperties = new WebDriverProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DriverFactory() {
    }

    public static WebDriver getWebDriver(String browserType) {

        WebDriver instance = null;
        switch (browserType) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", webDriverProperties.getProperty("chrome.exe"));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-infobars");
                instance = new ChromeDriver(chromeOptions);
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", webDriverProperties.getProperty("firefox.exe"));
                FirefoxOptions ffprofile = new FirefoxOptions();
                ffprofile.addPreference("dom.webnotifications.enabled", false);
                instance = new FirefoxDriver(ffprofile);
                break;
            }
            // add default variant
        }
        instance.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        instance.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        return instance;
    }
}
