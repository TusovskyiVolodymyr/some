package pageObjects;

import elements.Button;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Verify;
import utils.WaitManager;

public class PostPO extends BasePO {

    @FindBy(xpath = "//*[@aria-label='Create a post']//*[@role='presentation']//div[contains(@role,'textbox')]")
    private WebElement createPost;

    @FindBy(xpath = "//*[contains(@data-testid,'post-button')]")
    private Button postButton;

    @FindBy(xpath = "//*[@aria-label='Create a post']//*[contains(text(),'Make Post')]")
    private WebElement makePostLabel;

    public PostPO act_typePostText(String text) {
        typeText(createPost, text);
        return this;
    }

    public PostPO act_clickPostButton() {
        System.out.println(postButton);
        click(postButton);
        return this;
    }

    public PostPO ver_makePostLabel() {
        WaitManager.waitElementToBeVisible(makePostLabel);
        Verify.elementVisible(makePostLabel);
        return this;
    }
}
