import static pageObjects.SearchResultsPO.SearchType;

import annotations.Credentials;
import annotations.Injector;
import bussinesObjects.LoginBO;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageObjects.HeaderPO;
import pageObjects.SearchPO;
import pageObjects.SearchResultsPO;
import pageObjects.UserProfilePO;
import utils.CsvUtils;

public class SearchTest extends BaseTest {

    @Injector
    private LoginBO loginBO;
    @Injector
    private HeaderPO headerPO;
    @Injector
    private UserProfilePO homePO ;
    @Injector
    private SearchPO searchPO ;
    @Injector
    private SearchResultsPO searchResultsPO ;

    private enum Events {
        FIRST, SECOND, THIRD, ONE, TWO
    }

    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test
    public void test() throws InterruptedException {
        System.out.println(Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getAllParameters().toString());
        loginBO.logIn(CsvUtils.getParam("testLogin1"),CsvUtils.getParam("testPassword1"));
        System.out.println("current thread: " + Thread.currentThread().getName());

        searchPO.act_typeSearchWorld("Chandler Bing");
        postMessage(Events.FIRST);
        searchPO.act_clickSearchButton();
        searchResultsPO.act_chooseSearchType(SearchType.PEOPLE);
    }

    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Test()
    public void test3() throws InterruptedException {
        waitForMessage(Events.SECOND);
        loginBO.logIn(CsvUtils.getParam("testLogin2"),CsvUtils.getParam("testPassword2"));
        searchPO.act_typeSearchWorld("Chandler Bing");
//                .ver_textTyped("Chandler Bing");
        postMessage(Events.THIRD);
    }


    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test()
    public void test2() throws InterruptedException {
        waitForMessage(Events.FIRST);
        loginBO.logIn(CsvUtils.getParam("testLogin1"),CsvUtils.getParam("testPassword1"));
        searchPO.act_typeSearchWorld("Chandler Bing");
        postMessage(Events.SECOND);
        postMessage(Events.TWO);
        Thread.sleep(5000);
        searchPO    .act_clickSearchButton();

        waitForMessage(Events.ONE);
        searchResultsPO.act_chooseSearchType(SearchType.PEOPLE);
    }

    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Test()
    public void test4() throws InterruptedException {
        waitForMessage(Events.THIRD);
        loginBO.logIn(CsvUtils.getParam("testLogin2"),CsvUtils.getParam("testPassword2"));
        searchPO.act_typeSearchWorld("Ross Geller");
        waitForMessage(Events.TWO);
        System.out.println("Sleeeeping");
        Thread.sleep(7000);
        System.out.println("awake");
        postMessage(Events.ONE);
    }
}
