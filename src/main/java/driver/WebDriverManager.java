package driver;

import org.openqa.selenium.WebDriver;

public class WebDriverManager {

    private static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    public synchronized static void setDriver(String browserType) {
        if (getDriver() == null) {
            threadLocal = ThreadLocal.withInitial(() -> DriverFactory.getWebDriver(browserType));
        }
    }

    public synchronized static WebDriver getDriver() {
        if (threadLocal.get() != null) {
            return threadLocal.get();
        }
        return null;
    }

    public synchronized static void remove() {
        if (getDriver() != null) {
            getDriver().quit();
            threadLocal.remove();
        }
    }
}
