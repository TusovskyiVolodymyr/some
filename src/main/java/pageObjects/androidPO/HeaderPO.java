package pageObjects.androidPO;

import static pageObjects.androidPO.HeaderPO.HeaderElements.BTN_USER_MENU;
import static pageObjects.androidPO.HeaderPO.HeaderElements.DIV_DENY_LOCATION;

import org.openqa.selenium.By;
import utils.WaitManager;

public class HeaderPO extends BasePO {
    public enum HeaderElements {
        BTN_USER_MENU(By.xpath("//*[contains(@content-desc, 'Tab 6')]")),
        DIV_DENY_LOCATION(By.xpath("//android.widget.Button[@text='DENY']"));

        private By by;

        HeaderElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }
    }

    public HeaderPO act_clickUserMenu() {
        if (WaitManager.isElementVisible(DIV_DENY_LOCATION.get(), 5)) {
            click(DIV_DENY_LOCATION.get());
        }
        click(BTN_USER_MENU.get());
        return this;
    }
}
