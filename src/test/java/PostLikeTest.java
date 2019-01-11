import annotations.Injector;
import bussinesObjects.HeaderBO;
import bussinesObjects.LoginBO;
import bussinesObjects.PostBO;
import bussinesObjects.UserProfileBO;
import driver.WebDriverManager;
import org.testng.annotations.Test;
import utils.WebDriverProperties;

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
    public void createPost() throws InterruptedException {
        loginBO.logIn(WebDriverProperties.getProperty("testLogin1"), WebDriverProperties.getProperty("testPassword1"));
        headerBO.act_clickUserProfileIcon();
        postBO.act_createPostWithText("Test post1!");
        postMessage(Events.POST_SENT);
        waitForMessage(Events.DONE);
    }

    @Test()
    public void likePost() throws InterruptedException {
        loginBO.logIn(WebDriverProperties.getProperty("testLogin2"), WebDriverProperties.getProperty("testPassword2"));
        waitForMessage(Events.POST_SENT);
        headerBO.act_clickUserProfileIcon();
        userProfileBO.act_chooseFriendByFullName("Ross Geller");
        WebDriverManager.getDriver().navigate().refresh();
        postBO.act_likePostWithText("Test post1!");
        postMessage(Events.DONE);

    }

}