package elements;

import org.openqa.selenium.WebElement;

public class Button extends BaseElement {

    public Button(WebElement webElement) {
        super(webElement);
    }

    public void clickOn() {
            click();
    }

    public boolean isClickable() {
        return webElement.isEnabled();
    }
}
