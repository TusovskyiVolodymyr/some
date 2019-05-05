package driver;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public static void setDriver(String browserType) {
        threadLocal.set(DriverFactory.getWebDriver(browserType));
    }

    public static WebDriver getDriver() {
        if (threadLocal.get() != null) {
            return threadLocal.get();
        }
        return null;
    }

    public static void remove() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocal.remove();
        }
    }
}
