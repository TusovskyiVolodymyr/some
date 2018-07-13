import annotations.Credentials;
import annotations.Injector;
import bussinesObjects.LoginBO;
import org.testng.annotations.Test;
import pageObjects.HeaderPO;
import pageObjects.SearchPO;
import pageObjects.SearchResultsPO;
import pageObjects.UserProfilePO;

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

    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test
    public void test() throws InterruptedException {
        System.out.println("current thread: "+Thread.currentThread().getName());

        searchPO.act_typeSearchWorld("Chandler Bing");
        searchPO  .act_clickSearchButton();
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
        searchPO.act_typeSearchWorld("Chandler Bing")
                .act_clickSearchButton();
        searchResultsPO.act_chooseSearchType(SearchType.PEOPLE);
    }

    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Test(priority = 2)
    public void test4() throws InterruptedException {
        searchPO.act_typeSearchWorld("Chandler Bing")
        .ver_textTyped("Chandler Bing");
    }
}
