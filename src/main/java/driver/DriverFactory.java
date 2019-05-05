package driver;

import consts.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.WebDriverProperties;

import java.io.IOException;
import java.util.Optional;
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

    public synchronized static WebDriver getWebDriver(String browserType) {
        Optional<String> checkNull = Optional.ofNullable(browserType);
        browserType = checkNull.orElse("chrome");
        WebDriver instance = null;
        switch (browserType) {
            case "chrome": {
                System.setProperty("webdriver.chrome.driver", webDriverProperties.getProperty("chromeDriver"));
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--disable-notifications");
                chromeOptions.addArguments("--disable-infobars");
                instance = new ChromeDriver(chromeOptions);
                break;
            }
            case "firefox": {
                System.setProperty("webdriver.gecko.driver", webDriverProperties.getProperty("firefoxDriver"));
                FirefoxOptions ffprofile = new FirefoxOptions();
                ffprofile.addPreference("dom.webnotifications.enabled", false);
                instance = new FirefoxDriver(ffprofile);
                break;
            }
            case "ie": {
                System.setProperty("webdriver.ie.driver", "src\\main\\resources\\drivers\\IEDriverServer.exe");
                DesiredCapabilities ds = new DesiredCapabilities();
                ds.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
                ds.setCapability("allow-blocked-content", true);
                ds.setCapability("allowBlockedContent", true);
                ds.setCapability(CapabilityType.PLATFORM_NAME, "WINDOWS");
                DesiredCapabilities internetExplorer8 = DesiredCapabilities.internetExplorer();
                internetExplorer8.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                internetExplorer8.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
                instance = new InternetExplorerDriver(internetExplorer8);
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
