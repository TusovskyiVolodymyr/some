package pageObjects;

import static utils.WaitManager.*;

import elements.Button;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPO extends HeaderPO {

    @FindBy(xpath = "//*[@name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@aria-label='Search' and @type='submit']")
    private Button searchButton;

    public SearchPO act_typeSearchWorld(String text) {
        waitElementToBePresent(searchInput, 10);
        waitUntilJSLoad();
        typeText(searchInput, text);
        return this;
    }

    public SearchPO act_clickSearchButton() {
        searchButton.clicko();
//        click(searchButton);
        return this;
    }
}
