package elements;

import org.openqa.selenium.WebElement;
import utils.WaitManager;

public class Button extends BaseElement {

    public Button(WebElement webElement) {
        super(webElement);
    }

    public void clickOn() {
        WaitManager.waitUntillBeClickable(webElement);
            click();
    }

    public boolean isClickable() {
        return webElement.isEnabled();
    }
}
