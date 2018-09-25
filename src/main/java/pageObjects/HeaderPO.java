package pageObjects;

import elements.BaseElement;
import org.openqa.selenium.support.FindBy;
import utils.Verify;

public class HeaderPO extends BasePO {

    @FindBy(xpath = "//*[contains(@data-click,'profile_icon')]")
    private BaseElement userProfileIcon;

    @FindBy(xpath = "//*[contains(@id,'notificationsCountValue')]")
    private BaseElement notificationCount;

    @FindBy(xpath = "//*[contains(@data-click,'home_icon')]")
    private BaseElement homeButton;

    @FindBy(xpath = "//*[contains(@id,'findFriendsNav')]")
    private BaseElement findFriends;

    @FindBy(xpath = "//*[contains(@data-tooltip-content,'Friend Requests')]")
    private BaseElement friendsRequests;

    @FindBy(xpath = "//*[contains(@data-tooltip-content,'Messages')]")
    private BaseElement messages;

    @FindBy(xpath = "//*[contains(@data-tooltip-content,'Notifications')]")
    private BaseElement notifications;

    @FindBy(xpath = "//*[contains(@data-tooltip-content,'Quick Help')]")
    private BaseElement help;

    @FindBy(xpath = "//*[contains(@aria-labelledby,'userNavigationLabel')]")
    private BaseElement navigationArrow;

    public HeaderPO act_clickUserProfileIcon() {
        userProfileIcon.click();
        return this;
    }

    public HeaderPO act_clickHome() {
        homeButton.click();
        return this;
    }

    public HeaderPO act_clickFindFriends() {
        findFriends.click();
        return this;
    }

    public HeaderPO act_clickFriendsRequests() {
        friendsRequests.click();
        return this;
    }

    public HeaderPO act_clickMessages() {
        messages.click();
        return this;
    }

    public HeaderPO act_clickNotifications() {
        notifications.click();
        return this;
    }

    public HeaderPO act_clickHelp() {
        help.click();
        return this;
    }

    public HeaderPO act_clickNavigationArrow() {
        navigationArrow.click();
        return this;
    }

    public HeaderPO ver_userProfileIconPresent() {
        Verify.elementVisible(userProfileIcon);
        return this;
    }
}
