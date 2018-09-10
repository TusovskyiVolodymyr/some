import annotations.Credentials;
import annotations.Injector;
import bussinesObjects.LoginBO;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.HeaderPO;
import pageObjects.SearchPO;
import pageObjects.SearchResultsPO;
import pageObjects.UserProfilePO;
import utils.CsvUtils;

import static pageObjects.SearchResultsPO.SearchType;

public class SearchTest extends BaseTest {

    @Injector
    private LoginBO loginBO;
    @Injector
    private HeaderPO headerPO;
    @Injector
    private UserProfilePO homePO;
    @Injector
    private SearchPO searchPO;
    @Injector
    private SearchResultsPO searchResultsPO;

    @BeforeMethod
    public void init(){
        loginBO = new LoginBO();
        headerPO = new HeaderPO();
        homePO = new UserProfilePO();
        searchPO = new SearchPO();
        searchResultsPO = new SearchResultsPO();
    }


    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test
    public void test() throws InterruptedException {
        System.out.println("current thread: " + Thread.currentThread().getName());

        searchPO.act_typeSearchWorld("Chandler Bing");
        searchPO.act_clickSearchButton();
        searchResultsPO.act_chooseSearchType(SearchType.PEOPLE);
    }

    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Test()
    public void test3() throws InterruptedException {
        searchPO.act_typeSearchWorld("Chandler Bing")
                .ver_textTyped("Chandler Bing");
    }


    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test(priority = 2)
    public void test2() throws InterruptedException {
        loginBO = new LoginBO();
        searchPO = new SearchPO();
        searchResultsPO = new SearchResultsPO();
        loginBO.logIn(CsvUtils.getParam("testLogin1"),CsvUtils.getParam("testPassword1"));
        searchPO.act_typeSearchWorld("Chandler Bing")
                .act_clickSearchButton();
        unlock("2");
        lock("1");
        searchResultsPO.act_chooseSearchType(SearchType.PEOPLE);
    }

    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Test(priority = 2)
    public void test4() throws InterruptedException {
        loginBO = new LoginBO();
        searchPO = new SearchPO();
        searchResultsPO = new SearchResultsPO();
        loginBO.logIn(CsvUtils.getParam("testLogin2"),CsvUtils.getParam("testPassword2"));
        searchPO.act_typeSearchWorld("Chandler Bing");
        lock("2");
        searchPO  .ver_textTyped("Chandler Bing");
        System.out.println("Sleeeeping");
        Thread.sleep(7000);
        System.out.println("awake");
        unlock("1");
    }
}
