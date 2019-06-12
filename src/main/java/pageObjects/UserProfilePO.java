package pageObjects;

import org.openqa.selenium.By;
import utils.StringUtils;

public class UserProfilePO extends BasePO {

    private enum UserProfileElements {
        BTN_UPLOAD_PROFILE_PHOTO(By.xpath("//*[contains(@class,'photoContainer')]//ancestor::*[contains(text(),'Add Photo')]")),
        BTN_UPLOAD_PHOTO(By.xpath("//*[contains(text(),'Upload Photo')]//parent::div")),
        IPF_FRIEND_SEARCH(By.xpath("//*[@class='inputtext']")),
        DIV_TIMELINE_ITEM(By.xpath("//*[@id='fbTimelineHeadline']//following::*[@data-referrer='timeline_light_nav_top']//a[contains(text(),'%s')]")),
        DIV_FRIEND_IN_SEARCH(By.xpath("//*[contains(text(),'%s') and @class='name']")),
        BTN_SEND_MESSAGE(By.xpath("//*[@id='pagelet_timeline_profile_actions']//*[@role='button' and contains(@href, 'messages/t/')]"));

        private By by;

        UserProfileElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }

        public By getWithParams(Object... params) {
            return StringUtils.getLocator(by, params);
        }

    }

    public enum TimeLineElements {
        TIMELINE("Timeline"),
        ABOUT("About"),
        FRIENDS("Friends"),
        PHOTOS("Photos"),
        ARCHIVE("Archive"),
        MORE("More");

        private String value;

        TimeLineElements(String value) {
            this.value = value;
        }

        public String get() {
            return value;
        }
    }

    public UserProfilePO act_chooseFromTimeLine(TimeLineElements timeLineElements) {
        click(UserProfileElements.DIV_TIMELINE_ITEM.getWithParams(timeLineElements.get()));
        return this;
    }

    public UserProfilePO act_chooseFriendByFullName(String fullName) {
        click(UserProfileElements.DIV_FRIEND_IN_SEARCH.getWithParams(fullName));
        return this;
    }

    public UserProfilePO act_searchFriendByFullName(String fullName) {
        typeText(UserProfileElements.IPF_FRIEND_SEARCH.get(), fullName);
        return this;
    }

    public UserProfilePO act_clickSendMessage() {
        click(UserProfileElements.BTN_SEND_MESSAGE.get());
        return this;
    }
}
