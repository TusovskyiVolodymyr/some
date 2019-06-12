package driver;

import consts.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.WebDriverProperties;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private DriverFactory() {
    }

    public synchronized static WebDriver getWebDriver(String browserType) {
        Optional<String> checkNull = Optional.ofNullable(browserType);
        browserType = checkNull.orElse("chrome");
        WebDriver instance = null;
        switch (browserType) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", WebDriverProperties.getProperty("chromeDriver"));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-infobars");
                instance = new ChromeDriver(chromeOptions);
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", WebDriverProperties.getProperty("firefoxDriver"));
                FirefoxOptions ffprofile = new FirefoxOptions();
                ffprofile.setCapability("marionette", true);
                ffprofile.addPreference("dom.webnotifications.enabled", false);
                instance = new FirefoxDriver(ffprofile);
                break;
            }
            default:
                if (!browserType.equals("chrome") && !browserType.equals("firefox")) {
                    throw new RuntimeException(String.format("Such name of browser: %s is invalid", browserType));
                }
        }
        instance.manage().timeouts().implicitlyWait(Constants.IMPLICIT_WAIT, TimeUnit.SECONDS);
        instance.manage().timeouts().pageLoadTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        instance.manage().timeouts().setScriptTimeout(Constants.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        return instance;
    }
}
