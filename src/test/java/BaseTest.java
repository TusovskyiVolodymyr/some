import annotations.Credentials;
import annotations.Injector;
import annotations.Instance;
import bussinesObjects.LoginBO;
import driver.WebDriverManager;
import listener.TestListener;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.LoginPO;
import utils.Appender;
import utils.WebDriverProperties;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public abstract class BaseTest {

    @Injector
    private LoginBO loginBO;
    @Injector
    private LoginPO loginPO;

    private final static Logger log = LogManager.getLogger(BaseTest.class);
    private Set<Enum> set = new HashSet<>();
    private final static int EVENT_DEAD_LINE = 100;

    protected void waitForMessage(Enum message) throws InterruptedException {
        int counter = 0;
        log.warn("Thread was locked by " + Thread.currentThread().getName());
        log.warn(String.format("Waiting for event [%s] in [%d] seconds", message.name(), EVENT_DEAD_LINE));
        while (true) {
            if (set.contains(message)) {
                break;
            }
            Thread.sleep(1000);
            counter++;
            if (counter == 100) {
                throw new SkipException("TimeOut");
            }
        }
        log.warn(String.format("Event [%s] was published in [%d] seconds", message.name(), counter));
        set.remove(message);
    }

    protected void postMessage(Enum message) {
        set.add(message);
        log.warn(String.format("Posted event: [%s]", message.name()));
        log.warn("Swt" + set.toString());
        log.warn("Thread was unlocked by " + Thread.currentThread().getName());
    }

    @Parameters(value = "browser")
    @BeforeMethod
    public void setup(Method method, @Optional String browserType) {
//       new File(method.getName() + method.getDeclaringClass().getName());
        Instance.create(this);
        String s = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("ieMethod");
        if (method.getName().equals(s)) {
            WebDriverManager.setDriver("ie");
        } else {
            WebDriverManager.setDriver(browserType);
        }
        proceed(method);
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown(ITestResult iTestResult) {
        log.error("Calling driver.quit...");
        WebDriverManager.remove();
        log.error("Driver has been stop");
//        TestListener.sortLogFiles(iTestResult);
    }

    private void proceed(Method method) {
        if (method.isAnnotationPresent(Credentials.class)) {
            boolean initialState = method.getAnnotation(Credentials.class).initialState();
            if (initialState) {
                loginPO.act_getLoginUrl();
            } else {
                String[] strings = method.getAnnotation(Credentials.class).creds();
                if (strings.length != 2) throw new RuntimeException("Invalid credentials");
                loginBO.act_logIn(WebDriverProperties.getProperty(strings[0]), WebDriverProperties.getProperty(strings[1]));
            }
        }
    }


}
