package pageObjects.androidPO;

import static pageObjects.androidPO.ProfilePO.ProfileElements.DIV_POSTS;

import org.openqa.selenium.By;
import utils.StringUtils;

public class ProfilePO extends BasePO {
    public enum ProfileElements {
        DIV_POSTS(By.xpath("//*[contains(@content-desc, 'Manage Posts')]//following-sibling::android.view.ViewGroup"));

        private By by;

        ProfileElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }

        public By getWithParams(Object... params) {
            return StringUtils.getLocator(by, params);
        }
    }

    public ProfilePO act_scrollToPostArea() {
        scrollTo(DIV_POSTS.get());
        return this;
    }

    public ProfilePO act_clickOnPostArea() {
        click(DIV_POSTS.get());
        return this;
    }
}
