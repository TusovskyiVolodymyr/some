import annotations.Credentials;
import annotations.Injector;
import bussinesObjects.HeaderBO;
import bussinesObjects.PostBO;
import bussinesObjects.UserProfileBO;
import driver.WebDriverManager;
import org.testng.annotations.Test;

public class PostLikeTest extends BaseTest {
    @Injector
    private HeaderBO headerBO;
    @Injector
    private UserProfileBO userProfileBO;
    @Injector
    private PostBO postBO;

    private enum Events {
        DONE, POST_LIKED, POST_SENT
    }

    @Test
    @Credentials(creds = {"testLogin1", "testPassword1"})
    public void createPost() throws InterruptedException {
        headerBO.act_clickUserProfileIcon();
        postBO.act_createPostWithText("Test post1!");
        postMessage(Events.POST_SENT);
        waitForMessage(Events.POST_LIKED);
        WebDriverManager.getDriver().navigate().refresh();
        postBO.ver_postLikedByUser("Volodymyr Tysovskyi");
        postMessage(Events.DONE);
    }

    @Test
    @Credentials(creds = {"testLogin2", "testPassword2"})
    public void likePost() throws InterruptedException {
        waitForMessage(Events.POST_SENT);
        headerBO.act_clickUserProfileIcon();
        userProfileBO.act_chooseFriendByFullName("Ross Geller");
        WebDriverManager.getDriver().navigate().refresh();
        postBO.act_likePostWithText("Test post1!");
        postMessage(Events.POST_LIKED);
        waitForMessage(Events.DONE);
    }

}