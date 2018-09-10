package pageObjects;

import driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitManager;

import static utils.WaitManager.waitElementToBePresent;

public class UserProfilePO extends BasePO {

    public enum TimeLineElements {
        TIMELINE("Timeline"), ABOUT("About"), FRIENDS("Friends"), PHOTOS("Photos"), ARCHIVE("Archive"), MORE("More");

        TimeLineElements(String value) {
            this.value = value;
        }

        private String value;

        public String get() {
            return value;
        }
    }

    @FindBy(xpath = "//*[contains(@class,'photoContainer')]//ancestor::*[contains(text(),'Add Photo')]")
    private WebElement uploadProfilePhoto;

    @FindBy(xpath = "//*[contains(text(),'Upload Photo')]//parent::div")
    private WebElement uploadPhoto;

    @FindBy(xpath = "//*[@class='inputtext']")
    private WebElement friendsSearchInput;

    public UserProfilePO act_clickUploadProfilePhoto() {
        click(uploadProfilePhoto);
        return this;
    }

    public UserProfilePO act_clickUploadPhoto() {
        waitElementToBePresent(uploadPhoto, 5);
        click(uploadPhoto);
        return this;
    }

    public UserProfilePO act_chooseFromTimeLine(TimeLineElements timeLineElements) {
        WebElement timeLineElement = WebDriverManager.getDriver().findElement(By.xpath("//*[@id='fbTimelineHeadline']" +
                "//following::*[@data-referrer='timeline_light_nav_top']//a[contains(text(),'"
                + timeLineElements.get() + "')]"));
        WaitManager.waitUntilBeClickable(timeLineElement, 10);
        timeLineElement.click();
        return this;
    }

    public UserProfilePO act_chooseFriendByFullName(String fullName) {
        WebElement friend = WebDriverManager.getDriver().findElement(By.xpath("//*[contains(text(),'" + fullName + "') and @class='name']"));
        friend.click();
        return this;
    }

    public UserProfilePO act_searchFriendByFullName(String fullName) {
        typeText(friendsSearchInput, fullName);
        return this;
    }


}
