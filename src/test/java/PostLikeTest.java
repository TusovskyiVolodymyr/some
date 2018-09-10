import annotations.Injector;
import bussinesObjects.HeaderBO;
import bussinesObjects.LoginBO;
import bussinesObjects.PostBO;
import bussinesObjects.UserProfileBO;
import org.testng.annotations.Test;
import utils.CsvUtils;

public class PostLikeTest extends BaseTest {
    @Injector
    private LoginBO loginBO;
    @Injector
    private HeaderBO headerBO;
    @Injector
    private UserProfileBO userProfileBO;
    @Injector
    private PostBO postBO;

    @Test
    public void createPost() {
        loginBO.logIn(CsvUtils.getParam("testLogin1"), CsvUtils.getParam("testPassword1"));
        headerBO.act_clickUserProfileIcon();
        postBO.act_createPostWithText("Test post!");
    }

    @Test(priority = 1)
    public void likePost() throws InterruptedException {
        loginBO.logIn(CsvUtils.getParam("testLogin2"), CsvUtils.getParam("testPassword2"));
        headerBO.act_clickUserProfileIcon();
        userProfileBO.act_chooseFriendByFullName("Ross Geller");
        postBO.act_likePostWithText("Test post!");
        Thread.sleep(5000);
    }

}