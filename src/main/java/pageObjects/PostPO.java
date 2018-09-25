package pageObjects;

import driver.WebDriverManager;
import elements.BaseElement;
import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Verify;
import utils.WaitManager;

public class PostPO extends BasePO {

    @FindBy(xpath = "//*[@aria-label='Create a post']//*[@role='presentation']//div[contains(@role,'textbox')]")
    private BaseElement createPost;

    @FindBy(xpath = "//*[contains(@data-testid,'post-button')]")
    private Button postButton;

    @FindBy(xpath = "//*[@aria-label='Create a post']//*[contains(text(),'Make Post')]")
    private BaseElement makePostLabel;

    public PostPO act_typePostText(String text) {
        createPost.sendKeys(text);
        return this;
    }

    public PostPO act_clickPostButton() {
        System.out.println(postButton);
        postButton.click();
        return this;
    }

    public PostPO ver_makePostLabel() {
        WaitManager.waitElementToBeVisible(makePostLabel);
        Verify.elementVisible(makePostLabel);
        return this;
    }

    public PostPO act_likePostWithText(String textInPost) {
        WebElement webElement = WebDriverManager.getDriver().findElement(By.xpath("//p[contains(text(),'" + textInPost + "')]" +
                "//following::*[contains(@role,'button') and text()='Like']"));
        WaitManager.waitUntilBeClickable(webElement, 5);
        webElement.click();
        return this;
    }
}
