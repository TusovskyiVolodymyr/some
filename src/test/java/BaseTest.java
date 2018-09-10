import annotations.Instance;
import driver.WebDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.Appender;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class BaseTest {

    private final static Logger log = LogManager.getLogger(BaseTest.class);

    private Set<String> set = new HashSet<>();

    public  void lock(String message) throws InterruptedException {
        int c  = 0;
        log.warn("Thread was locked by " + Thread.currentThread().getName());
        while (true) {
            if (set.contains(message)){
                break;
            }
            log.warn("waiting for event: " + message);
            Thread.sleep(1000);
            c++;
            if (c==20){
               throw new SkipException("TimeOut");
            }
        }
    }

    public  void unlock(String message) throws InterruptedException {
        set.add(message);
        log.warn("Swt" + set.toString());
        log.warn("Thread was unlocked by " + Thread.currentThread().getName());
    }

    @Parameters(value = "browser")
    @BeforeMethod
    public void setup(Method method, @Optional String browserType) {
        Appender.fileAppender(method.getName()+method.getDeclaringClass().getName());
        WebDriverManager.setDriver(browserType);
        Instance.create(this);
    }

    @AfterMethod
    protected void tearDown(Method method) {
        log.error("Calling driver.quit...");
        WebDriverManager.remove();
        log.error("Driver has been stop");
    }
}
