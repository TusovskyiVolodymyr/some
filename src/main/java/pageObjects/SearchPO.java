package pageObjects;

import elements.Button;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPO extends HeaderPO {

    @FindBy(xpath = "//*[@name='q']")
    private WebElement searchInput;

    @FindBy(xpath = "//*[@aria-label='Search' and @type='submit']")
    private Button searchButton;

    public SearchPO act_typeSearchWorld(String text) {
        waitELementToBePresent(searchInput,10);
        searchInput.clear();
        typeText(searchInput, text);
        return this;
    }

    public SearchPO act_clickSearchButton(){
        searchButton.clicko();
        return this;
    }
}
