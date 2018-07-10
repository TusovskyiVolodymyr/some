package pageObjects;

import static utils.WaitManager.*;

import elements.Button;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.WaitManager;

public class UserProfilePO extends BasePO {

    @FindBy(xpath = "//*[contains(@class,'photoContainer')]//ancestor::*[contains(text(),'Add Photo')]")
    private WebElement uploadProfilePhoto;

    @FindBy(xpath = "//*[contains(text(),'Upload Photo')]//parent::div")
    private WebElement uploadPhoto;

    public UserProfilePO act_clickUploadProfilePhoto() {
        click(uploadProfilePhoto);
        return this;
    }

    public UserProfilePO act_clickUploadPhoto() {
        waitElementToBePresent(uploadPhoto, 5);
        click(uploadPhoto);
        return this;
    }


}
