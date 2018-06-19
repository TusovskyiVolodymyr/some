package pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePO extends BasePO {

    @FindBy(xpath = "//*[contains(@class,'photoContainer')]//ancestor::*[contains(text(),'Add Photo')]")
    private WebElement uploadProfilePhoto;

    @FindBy(xpath = "//*[contains(text(),'Upload Photo')]//parent::div")
    private WebElement uploadPhoto;

    public HomePO act_clickUploadProfilePhoto() {
        click(uploadProfilePhoto);
        return this;
    }

    public HomePO act_clickUploadPhoto() {
        waitELementToBePresent(uploadPhoto, 5);
        click(uploadPhoto);
        return this;
    }
}
