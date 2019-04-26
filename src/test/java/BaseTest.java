import annotations.Credentials;
import annotations.Injector;
import annotations.Instance;
import bussinesObjects.LoginBO;
import driver.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import pageObjects.LoginPO;
import utils.Appender;
import utils.WebDriverProperties;

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

    protected void waitForMessage(Enum message) throws InterruptedException {
        int counter = 0;
        log.warn("Thread was locked by " + Thread.currentThread().getName());
        while (true) {
            if (set.contains(message)) {
                break;
            }
            log.warn("waiting for event: " + message);
            Thread.sleep(1000);
            counter++;
            if (counter == 100) {
                throw new SkipException("TimeOut");
            }
        }
        set.remove(message);
    }

    protected void postMessage(Enum message) throws InterruptedException {
        set.add(message);
        log.warn("Swt" + set.toString());
        log.warn("Thread was unlocked by " + Thread.currentThread().getName());
    }

    @Parameters(value = "browser")
    @BeforeMethod
    public void setup(Method method, @Optional String browserType) {
        Instance.create(this);
        Appender.fileAppender(method.getName() + method.getDeclaringClass().getName());
        WebDriverManager.setDriver(browserType);
        proceed(method);
    }

    @AfterMethod
    protected void tearDown(Method method) {
        log.error("Calling driver.quit...");
        WebDriverManager.remove();
        log.error("Driver has been stop");
    }

    private void proceed(Method method) {
        if (method.isAnnotationPresent(Credentials.class)) {
            String[] strings = method.getAnnotation(Credentials.class).creds();
            boolean initialState = method.getAnnotation(Credentials.class).initialState();
            if (initialState) {
                loginPO.act_getLoginUrl();
            } else {
                if (strings == null || strings.length != 2) throw new RuntimeException("Invalid credentials");
                loginBO.logIn(WebDriverProperties.getProperty(strings[0]), WebDriverProperties.getProperty(strings[1]));
            }
        }
    }
}
