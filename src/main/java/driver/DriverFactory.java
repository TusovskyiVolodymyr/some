package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class DriverFactory {
    private static ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getWebDriver(BrowserType browserType) {
        if (webDriverThreadLocal.get() != null) {
            return webDriverThreadLocal.get();
        }

        WebDriver instance = null;
        switch (browserType) {
            case CHROME: {
                    System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\drivers\\chromedriver.exe");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--disable-notifications");
                    chromeOptions.addArguments("--disable-infobars");
                    instance = new ChromeDriver(chromeOptions);
                break;
            }
            case IE: {
                System.setProperty("webdriver.ie.driver", "src\\main\\resources\\drivers\\IEDriverServer.exe");
                DesiredCapabilities ds = new DesiredCapabilities();
                ds.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
                ds.setCapability("allow-blocked-content", true);
                ds.setCapability("allowBlockedContent", true);
                ds.setCapability(CapabilityType.PLATFORM_NAME, "WINDOWS");
                DesiredCapabilities internetExplorer8 = DesiredCapabilities.internetExplorer();

                instance = new InternetExplorerDriver();
                break;
            }
            case OPERA: {
                String operaBrowserLocation = "C:\\Program Files\\Opera\\51.0.2830.34\\opera.exe";
                System.setProperty("webdriver.opera.driver", "src\\main\\resources\\drivers\\operadriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setBinary(operaBrowserLocation);
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                instance = new OperaDriver(capabilities);
                break;
            }
            case FIREFOX: {
                System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");
                instance = new FirefoxDriver();
                break;
            }
            case EDGE: {
                System.setProperty("webdriver.edge.driver", "src\\main\\resources\\drivers\\MicrosoftWebDriver.exe");
                instance = new EdgeDriver();
                break;
            }
        }
        instance.manage().window().maximize();
        instance.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        instance.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
        webDriverThreadLocal.set(instance);
        return webDriverThreadLocal.get();
    }

    public static void quit() {
        try {
            webDriverThreadLocal.get().quit();
        } finally {
            webDriverThreadLocal.remove();
        }
    }
}
