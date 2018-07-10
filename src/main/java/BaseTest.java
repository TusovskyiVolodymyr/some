import annotations.Credentials;
import annotations.Instance;
import bussinesObjects.LoginBO;
import driver.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.CsvUtils;

import java.lang.reflect.Method;

public abstract class BaseTest {

    private final static Logger log = LogManager.getLogger();

    private LoginBO loginBO;

    @Parameters(value = "browser")
    @BeforeMethod
    public void login(Method method, String browserType) {
        WebDriverManager.setDriver(browserType);
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
