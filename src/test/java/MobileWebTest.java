import annotations.Credentials;
import annotations.Injector;
import annotations.Property;
import bussinesObjects.HeaderBO;
import bussinesObjects.LoginBO;
import bussinesObjects.PostBO;
import bussinesObjects.UserProfileBO;
import driver.WebDriverManager;
import org.testng.annotations.Test;
import pageObjects.androidPO.HeaderPO;
import pageObjects.androidPO.UserMenuPO;

public class MobileWebTest extends BaseTest {

    @Injector
    private HeaderBO headerBO;
    @Injector
    private UserProfileBO userProfileBO;
    @Injector
    private PostBO postBO;
    @Injector
    private HeaderPO headerPO;
    @Injector
    private UserMenuPO userMenuPO;
    @Injector
    private bussinesObjects.androidBussinesObjects.PostBO androidPostBo;
    @Injector
    private LoginBO loginBO;
    @Injector
    private bussinesObjects.androidBussinesObjects.LoginBO androidLoginBO;

    @Property("user1FullName")
    private String userFullName;
    @Property("testLogin1")
    private String testLogin1;
    @Property("testPassword1")
    private String testPassword1;
    @Property("testLogin2")
    private String testLogin2;
    @Property("testPassword2")
    private String testPassword2;

    private enum Events {
        DONE, POST_LIKED, USER_1_LOGGED, POST_SENT
    }

    @Test
//    @Credentials(creds = {"testLogin1", "testPassword1"})
    @Credentials(initialState = true)
    public void createPost() throws InterruptedException {
        androidLoginBO.act_logIn(testLogin1, testPassword1);
        postMessage(Events.USER_1_LOGGED);
        headerPO.act_clickUserMenu();
        userMenuPO.act_clickOnUser(userFullName);
        androidPostBo.act_createPost("Test post12!");
        postMessage(Events.POST_SENT);
        waitForMessage(Events.POST_LIKED);
    }


    @Test
//    @Credentials(creds = {"testLogin2", "testPassword2"})
    @Credentials(initialState = true)
    public void likePost() throws InterruptedException {
        waitForMessage(Events.USER_1_LOGGED);
        loginBO.act_logIn(testLogin2, testPassword2);
        waitForMessage(Events.POST_SENT);
        headerBO.act_clickUserProfileIcon();
        userProfileBO.act_chooseFriendByFullName(userFullName);
        WebDriverManager.getDriver().navigate().refresh();
        postBO.act_likePostWithText("Test post12!");
        postMessage(Events.POST_LIKED);
    }
}
