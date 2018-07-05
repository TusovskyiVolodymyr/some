import bussinesObjects.LoginBO;
import driver.BrowserType;
import driver.DriverFactory;
import driver.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import utils.CsvUtils;
import utils.Instance;

import java.lang.reflect.Method;
import java.util.function.Supplier;

public abstract class BaseTest {

    private final static Logger log = LogManager.getLogger();

    private LoginBO loginBO;

    @BeforeMethod
    public void login(Method method) {
        WebDriverManager.setDriver(BrowserType.FIREFOX);
        System.out.println("Processing method: " + method.getName());
        if (method.isAnnotationPresent(Credentials.class)) {
            String[] strings = method.getAnnotation(Credentials.class).creds();
            if (!(strings[0] == null && strings[1] == null)) {
                loginBO = new LoginBO();
                loginBO.logIn(CsvUtils.getParam(strings[0]), CsvUtils.getParam(strings[1]));
            }
        }
        Instance.create(this);
    }

    @AfterMethod
    protected void tearDown() {
        log.info("Calling driver.quit...");
        WebDriverManager.remove();
        log.info("Driver has been stop");
    }
}
