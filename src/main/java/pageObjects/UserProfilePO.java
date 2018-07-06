package pageObjects;

import static utils.WaitManager.*;

import elements.Button;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserProfilePO extends BasePO {

    @FindBy(xpath = "//*[contains(@class,'photoContainer')]//ancestor::*[contains(text(),'Add Photo')]")
    private WebElement uploadProfilePhoto;

    @FindBy(xpath = "//*[contains(text(),'Upload Photo')]//parent::div")
    private WebElement uploadPhoto;

    @FindBy(xpath = "//*[@aria-label='Create a post']//*[@role='presentation']//div[contains(@role,'textbox')]")
    private WebElement createPost;

    @FindBy(xpath = "//*[contains(@data-testid,'post-button')]")
    private Button postButton;

    public UserProfilePO act_clickUploadProfilePhoto() {
        click(uploadProfilePhoto);
        return this;
    }

    public UserProfilePO act_clickUploadPhoto() {
        waitElementToBePresent(uploadPhoto, 5);
        click(uploadPhoto);
        return this;
    }

    public UserProfilePO act_typePostText(String text){
        typeText(createPost, text);
        return this;
    }

    public UserProfilePO act_clickPostButton(){
        System.out.println(postButton);
        click(postButton);
        return this;
    }
}
