import annotations.*;
import bussinesObjects.LoginBO;
import consts.Constants;
import driver.WebDriverManager;
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
import utils.WebDriverProperties;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseTest {

    @Injector
    private LoginBO loginBO;
    @Injector
    private LoginPO loginPO;
    @Injector
    private bussinesObjects.androidBussinesObjects.LoginBO androidLoginBo;
    @Property("user1FullName")
    protected String string;

    private final static Logger log = LogManager.getLogger(BaseTest.class);
    private Set<Enum> set = new HashSet<>();

    protected void waitForMessage(Enum message) throws InterruptedException {
        int counter = 0;
        log.warn(String.format("Waiting for event [%s] in [%d] seconds", message.name(), Constants.EVENT_DEAD_LINE));
        while (!set.contains(message)) {
            Thread.sleep(1000);
            counter++;
            if (counter == Constants.EVENT_DEAD_LINE) {
                throw new SkipException("Time out for publishing event: " + message.name());
            }
        }
        log.warn(String.format("Event [%s] was published in [%d] seconds", message.name(), counter));
        set.remove(message);
    }

    protected void postMessage(Enum message) {
        set.add(message);
        log.warn(String.format("Posted event: [%s]", message.name()));
    }

    @Parameters(value = "browser")
    @BeforeMethod()
    public void setup(Method method, @Optional String browserType) {
        List<String> strings = new ArrayList<>();
        Instance.create(this);
        AnnotationProcessor.proceed(this);
        String s = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("firefoxMethod");
        String s2 = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("appiumMethod");
        if (s != null && s.contains(",")) {
            strings = Arrays.stream(s.split(",")).map(String::trim).collect(Collectors.toList());
        }
        if (strings.contains(method.getName())) {
            WebDriverManager.setDriver("firefox");
        } else if (method.getName().equals(s2)) {
            WebDriverManager.setDriver("appium");
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
    }

    private void proceed(Method method) {
        boolean isAppiumMethod = method.getName().equals(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("appiumMethod"));
        if (method.isAnnotationPresent(Credentials.class)) {
            boolean initialState = method.getAnnotation(Credentials.class).initialState();
            if (initialState) {
                if (isAppiumMethod) {
                    log.info("Initial state is true, no login is required");
                } else {
                    loginPO.act_getLoginUrl();
                }
            } else {
                String[] strings = method.getAnnotation(Credentials.class).creds();
                if (strings.length != 2) throw new RuntimeException("Invalid credentials");
                if (isAppiumMethod) {
                    androidLoginBo.act_logIn(WebDriverProperties.getProperty(strings[0]), WebDriverProperties.getProperty(strings[1]));
                } else {
                    loginBO.act_logIn(WebDriverProperties.getProperty(strings[0]), WebDriverProperties.getProperty(strings[1]));
                }
            }
        }
    }
}
