import static pageObjects.SearchResultsPO.SearchType;

import bussinesObjects.LoginBO;
import events.Publisher;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import utils.CsvUtils;
import utils.Injector;

public class LoginTest extends BaseTest {

    @Injector
    private LoginBO loginBO;
    @Injector
    private LoginPO loginPO;
    @Injector
    private HeaderPO headerPO;
    @Injector
    private HomePO homePO;
    @Injector
    private SearchPO searchPO;
    @Injector
    private SearchResultsPO searchResultsPO;

    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Test
    public void test() throws InterruptedException {
        String user = CsvUtils.getParam("user1FullName").split(" ")[0];

        loginPO.ver_Loged(user);
        headerPO.act_clickUserProfileIcon();
//        Assert.assertFalse(headerPO.isNewNotification());
        searchPO.act_typeSearchWorld("Chandler Bing")
                .act_clickSearchButton();
//        Publisher.publishEvent("CLID");
        searchResultsPO.act_chooseSearchType(SearchType.PEOPLE);
    }

    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test
    public void gffkjggf(){
//        Publisher.waitForEvent("CLID");
        System.out.println("after");
    }
}
