import bussinesObjects.LoginBO;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.CsvUtils;
import utils.Injector;

import static pageObjects.SearchResultsPO.SearchType;

public class LoginTest extends BaseTest {

    @Injector
    private LoginBO loginBO;
    @Injector
    private HeaderPO headerPO;
    @Injector
    private HomePO homePO;
    @Injector
    private SearchPO searchPO;
    @Injector
    private SearchResultsPO searchResultsPO;


    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test
    public void test() throws InterruptedException {
//        String user = CsvUtils.getParam("user1FullName").split(" ")[0];
//
//        loginPO.ver_Loged(user);
//        headerPO.act_clickUserProfileIcon();
//        Assert.assertFalse(headerPO.isNewNotification());
        searchPO.act_typeSearchWorld("Chandler Bing")
                .act_clickSearchButton();
//        Publisher.publishEvent("CLID");
        searchResultsPO.act_chooseSearchType(SearchType.PEOPLE);
    }

    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Test()
    public void gffkjggf() throws InterruptedException {
//        Publisher.waitForEvent("CLID");
        System.out.println("after");
//        Thread.sleep(7000);
//        headerPO.act_clickUserProfileIcon();
//        Assert.assertFalse(headerPO.isNewNotification());
        searchPO.act_typeSearchWorld("Chandler Bing");
    }
    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test(priority = 2)
    public void test2() throws InterruptedException {
//        String user = CsvUtils.getParam("user1FullName").split(" ")[0];
//
//        loginPO.ver_Loged(user);
//        headerPO.act_clickUserProfileIcon();
//        Assert.assertFalse(headerPO.isNewNotification());
        searchPO.act_typeSearchWorld("Chandler Bing")
                .act_clickSearchButton();
//        Publisher.publishEvent("CLID");
        searchResultsPO.act_chooseSearchType(SearchType.PEOPLE);
    }

    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Test(priority = 2)
    public void gffkjggf2() throws InterruptedException {
//        Publisher.waitForEvent("CLID");
        System.out.println("after");
//        Thread.sleep(7000);
//        headerPO.act_clickUserProfileIcon();
//        Assert.assertFalse(headerPO.isNewNotification());
        searchPO.act_typeSearchWorld("Chandler Bing");
    }
}
