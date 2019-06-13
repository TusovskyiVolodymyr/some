package pageObjects;

import org.openqa.selenium.By;
import utils.Verify;

public class HeaderPO extends BasePO {
    private enum HeaderPOElements {
        USER_PROFILE_ICON(By.xpath("//*[contains(@data-click,'profile_icon')]")),
        NOTIFICATION_COUNT(By.xpath("//*[contains(@id,'notificationsCountValue')]")),
        BTN_HOME(By.xpath("//*[contains(@data-click,'home_icon')]")),
        BTN_FIND_FRIENDS(By.xpath("//*[contains(@data-click,'home_icon')]")),
        BTN_FRIENDS_REQUEST(By.xpath("//*[contains(@data-tooltip-content,'Friend Requests')]")),
        BTN_MESSAGES(By.xpath("//*[contains(@data-tooltip-content,'Messages')]")),
        BTN_NOTIFICATIONS(By.xpath("//*[contains(@data-tooltip-content,'Notifications')]")),
        BTN_HELP(By.xpath("//*[contains(@data-tooltip-content,'Quick Help')]")),
        BTN_NAVIGATION_ARROW(By.xpath("//*[contains(@aria-labelledby,'userNavigationLabel')]"));

        private By by;

        HeaderPOElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }
    }

    public HeaderPO act_clickUserProfileIcon() {
        Verify.elementVisible(HeaderPOElements.USER_PROFILE_ICON.get());
        click(HeaderPOElements.USER_PROFILE_ICON.get());
        return this;
    }
}
