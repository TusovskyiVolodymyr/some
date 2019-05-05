package pageObjects;

import org.openqa.selenium.By;
import utils.StringUtils;
import utils.Verify;

public class PostPO extends BasePO {

    private enum PostElements {
        CREATE_POST(By.xpath("//*[@aria-label='Create a post']//*[@role='presentation']//div[contains(@role,'textbox')]")),
        BTN_POST(By.xpath("//*[contains(@data-testid,'post-button')]")),
        LBL_MAKE_POST(By.xpath("//*[@aria-label='Create a post']//*[contains(text(),'Make Post')]")),
        BTN_LIKE_POST(By.xpath(("//p[contains(text(),'%s')]//following::*[contains(@role,'button') and text()='Like']"))),
        LBL_POST_LIKED(By.xpath("//*[@data-testid='UFI2ReactionLink']//preceding::*[contains(@data-testid," +
                " 'sentenceWithSocialContext')]//span[text()='%s']"));

        private By by;

        PostElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }

        public By getWithParams(Object... params) {
            return StringUtils.getLocator(by, params);
        }

    }

    public PostPO act_typePostText(String text) {
        typeText(PostElements.CREATE_POST.get(), text);
        return this;
    }

    public PostPO act_clickPostButton() {
        click(PostElements.BTN_POST.get());
        return this;
    }


    public PostPO act_likePostWithText(String textInPost) {
        click(PostElements.BTN_LIKE_POST.getWithParams(textInPost));
        return this;
    }

    public PostPO ver_postLiked(String userFullName) {
        Verify.elementVisible(PostElements.LBL_POST_LIKED.getWithParams(userFullName));
        return this;
    }
}
