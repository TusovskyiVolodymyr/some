import bussinesObjects.LoginBO;
import driver.BrowserType;
import driver.DriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.CsvUtils;
import utils.Instance;

public class BaseTest {

    private final static Logger log = LogManager.getLogger();

    private LoginBO loginBO;

    @BeforeSuite
    protected void init() {
        Instance.create(this);
    }

    @BeforeMethod
    protected void login(){
        String[] strings = Creds.login(this);
        if (!(strings[0] == null && strings[1] == null)) {
            loginBO = new LoginBO();
            loginBO.logIn(CsvUtils.getParam(strings[0]), CsvUtils.getParam(strings[1]));
        }
    }

    @AfterMethod
    protected void tearDown() {
        log.info("Calling driver.quit...");
        DriverFactory.quit();
        log.info("Driver has been stop");
    }
}
