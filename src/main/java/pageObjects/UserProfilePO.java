package pageObjects;

import driver.WebDriverManager;
import elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitManager;

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
    private BaseElement uploadProfilePhoto;

    @FindBy(xpath = "//*[contains(text(),'Upload Photo')]//parent::div")
    private BaseElement uploadPhoto;

    @FindBy(xpath = "//*[@class='inputtext']")
    private BaseElement friendsSearchInput;

    public UserProfilePO act_clickUploadProfilePhoto() {
        uploadProfilePhoto.click();
        return this;
    }

    public UserProfilePO act_clickUploadPhoto() {
        uploadPhoto.click();
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
        friendsSearchInput.sendKeys(fullName);
        return this;
    }


}
