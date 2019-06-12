package pageObjects;

import driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class MessageWindowPO extends SearchPO {
    private enum MessageWindowElements {
        IPF_MESSAGE(By.xpath("//*[@role='complementary']//*[contains(@aria-describedby,'placeholder')]")),
        DIV_MESSAGES(By.xpath("//*[@data-tooltip-position=\"right\" and contains(@class, 'direction_ltr')]"));

        private By by;

        MessageWindowElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }
    }

    public MessageWindowPO act_sendMessage(String text) {
        typeText(MessageWindowElements.IPF_MESSAGE.get(), text);
        new Actions(WebDriverManager.getDriver()).sendKeys(Keys.ENTER).perform();
        return this;
    }

    public String getLastMeassage() {
        List<WebElement> messages = WebDriverManager.getDriver().findElements(MessageWindowElements.DIV_MESSAGES.get());
        return messages.get(messages.size() - 1).getText();
    }
}
