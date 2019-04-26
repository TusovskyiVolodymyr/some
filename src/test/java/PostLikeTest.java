import annotations.Credentials;
import annotations.Injector;
import bussinesObjects.HeaderBO;
import bussinesObjects.LoginBO;
import bussinesObjects.PostBO;
import bussinesObjects.UserProfileBO;
import driver.WebDriverManager;
import org.testng.annotations.Test;

public class PostLikeTest extends BaseTest {
    @Injector
    private LoginBO loginBO;
    @Injector
    private HeaderBO headerBO;
    @Injector
    private UserProfileBO userProfileBO;
    @Injector
    private PostBO postBO;

    private enum Events {
        DONE, POST_SENT
    }

    @Test
    @Credentials(creds = {"testLogin1", "testPassword1"})
    public void createPost() throws InterruptedException {
        headerBO.act_clickUserProfileIcon();
        postBO.act_createPostWithText("Test post1!");
        postMessage(Events.POST_SENT);
        waitForMessage(Events.DONE);
    }

    @Test
    @Credentials(creds = {"testLogin2", "testPassword2"})
    public void likePost() throws InterruptedException {
        waitForMessage(Events.POST_SENT);
        headerBO.act_clickUserProfileIcon();
        userProfileBO.act_chooseFriendByFullName("Ross Geller");
        WebDriverManager.getDriver().navigate().refresh();
        postBO.act_likePostWithText("Test post1!");
        postMessage(Events.DONE);
    }

}