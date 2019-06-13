package pageObjects.androidPO;

import static pageObjects.androidPO.PostPO.PostElements.BTN_POST;
import static pageObjects.androidPO.PostPO.PostElements.DIV_POST_AREA;

import org.openqa.selenium.By;
import utils.StringUtils;

public class PostPO extends BasePO {
    public enum PostElements {
        DIV_POST_AREA(By.xpath("//*[contains(@text, 'your mind')]")),
        BTN_POST(By.xpath("//android.widget.Button[@content-desc=\"POST\"]"));

        private By by;

        PostElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }

        public By getWithParams(Object... params) {
            return StringUtils.getLocator(by, params);
        }
    }

    public PostPO act_typePostText(String text) {
        typeText(DIV_POST_AREA.get(), text);
        return this;
    }

    public PostPO act_clickPostButton() {
        click(BTN_POST.get());
        return this;
    }
}
