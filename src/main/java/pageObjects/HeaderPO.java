package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Verify;

public class HeaderPO extends BasePO {

    @FindBy(xpath = "//*[contains(@data-click,'profile_icon')]")
    private WebElement userProfileIcon;

    @FindBy(xpath = "//*[contains(@id,'notificationsCountValue')]")
    private WebElement notificationCount;

    @FindBy(xpath = "//*[contains(@data-click,'home_icon')]")
    private WebElement homeButton;

    @FindBy(xpath = "//*[contains(@id,'findFriendsNav')]")
    private WebElement findFriends;

    @FindBy(xpath = "//*[contains(@data-tooltip-content,'Friend Requests')]")
    private WebElement friendsRequests;

    @FindBy(xpath = "//*[contains(@data-tooltip-content,'Messages')]")
    private WebElement messages;

    @FindBy(xpath = "//*[contains(@data-tooltip-content,'Notifications')]")
    private WebElement notifications;

    @FindBy(xpath = "//*[contains(@data-tooltip-content,'Quick Help')]")
    private WebElement help;

    @FindBy(xpath = "//*[contains(@aria-labelledby,'userNavigationLabel')]")
    private WebElement navigationArrow;

    public HeaderPO act_clickUserProfileIcon() {
        click(userProfileIcon);
        return this;
    }

    public HeaderPO act_clickHome() {
        click(homeButton);
        return this;
    }

    public HeaderPO act_clickFindFriends() {
        click(findFriends);
        return this;
    }

    public HeaderPO act_clickFriendsRequests() {
        click(friendsRequests);
        return this;
    }

    public HeaderPO act_clickMessages() {
        click(messages);
        return this;
    }

    public HeaderPO act_clickNotifications() {
        click(notifications);
        return this;
    }

    public HeaderPO act_clickHelp() {
        click(help);
        return this;
    }

    public HeaderPO act_clickNavigationArrow() {
        click(navigationArrow);
        return this;
    }

    public HeaderPO ver_userProfileIconPresent() {
        Verify.elementVisible(userProfileIcon);
        return this;
    }
}
