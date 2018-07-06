import bussinesObjects.LoginBO;
import org.testng.annotations.Test;
import pageObjects.HeaderPO;
import pageObjects.SearchPO;
import pageObjects.SearchResultsPO;
import pageObjects.UserProfilePO;
import utils.Injector;

public class PostTest extends BaseTest {

    @Injector
    private LoginBO loginBO;
    @Injector
    private HeaderPO headerPO;
    @Injector
    private UserProfilePO userProfilePO;
    @Injector
    private SearchPO searchPO;
    @Injector
    private SearchResultsPO searchResultsPO;



    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test
    public void test() throws InterruptedException {
        System.out.println("current thread: " + Thread.currentThread().getName());

       headerPO.act_clickUserProfileIcon();
       userProfilePO.act_typePostText("Hello Word!")
       .act_clickPostButton();
       Thread.sleep(5000);
    }
}
