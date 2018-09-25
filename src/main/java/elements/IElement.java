package elements;

import org.openqa.selenium.WebElement;

public interface IElement extends WebElement {

    String getXpath();

    WebElement getWebElement();

    String getInputValue();
}
