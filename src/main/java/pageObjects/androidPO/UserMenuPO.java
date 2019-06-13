package pageObjects.androidPO;

import static pageObjects.androidPO.UserMenuPO.UserMenuElements.BTN_PROFILE;

import org.openqa.selenium.By;
import utils.StringUtils;

public class UserMenuPO extends BasePO {
    public enum UserMenuElements {
        BTN_PROFILE(By.xpath("//*[contains(@content-desc, '%s')]"));

        private By by;

        UserMenuElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }

        public By getWithParams(Object... params) {
            return StringUtils.getLocator(by, params);
        }
    }

    public UserMenuPO act_clickOnUser(String userFullName) {
        click(BTN_PROFILE.getWithParams(userFullName));
        return this;
    }
}
