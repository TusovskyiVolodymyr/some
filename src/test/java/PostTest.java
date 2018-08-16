import annotations.Credentials;
import annotations.Injector;
import bussinesObjects.LoginBO;
import bussinesObjects.PostBO;
import org.testng.annotations.Test;
import pageObjects.HeaderPO;
import pageObjects.SearchPO;
import pageObjects.SearchResultsPO;

public class PostTest extends BaseTest {

    @Injector
    private LoginBO loginBO;
    @Injector
    private HeaderPO headerPO;
    @Injector
    private SearchPO searchPO;
    @Injector
    private SearchResultsPO searchResultsPO;
    @Injector
    private PostBO postBO;

    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Test
    public void test() {
        System.out.println("current thread: " + Thread.currentThread().getName());
        headerPO.act_clickUserProfileIcon();
        postBO.act_createPostWithText("Hello all!");
    }
}
