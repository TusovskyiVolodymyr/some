package pageObjects;

import elements.BaseElement;
import elements.Button;
import org.openqa.selenium.support.FindBy;
import utils.Verify;

public class SearchPO extends HeaderPO {

    @FindBy(xpath = "//*[@name='q']")
    private BaseElement searchInput;

    @FindBy(xpath = "//*[@aria-label='Search' and @type='submit']")
    private Button searchButton;

    public SearchPO act_typeSearchWorld(String text) {
        searchInput.sendKeys(text);
        return this;
    }

    public SearchPO act_clickSearchButton() {
        searchButton.clickOn();
        return this;
    }

    public SearchPO ver_textTyped(String text) {
        Verify.isTrue(searchInput.getInputValue().equals(text), "text is typed is search field");
        return this;
    }
}
