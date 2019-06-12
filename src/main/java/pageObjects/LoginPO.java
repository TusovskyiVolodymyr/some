package pageObjects;

import annotations.Property;
import driver.WebDriverManager;
import org.openqa.selenium.By;
import utils.Verify;

public class LoginPO extends BasePO {

    private enum LoginElements {
        IPF_LOGIN(By.xpath("//*[contains(@type,'email')]")),
        IPF_PASSWORD(By.cssSelector("#pass")),
        BTN_SUBMIT(By.xpath("//*[contains(@type,'submit')]"));

        private By by;

        LoginElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }
    }

    @Property("base.url")
    private String baseUrl;

    public LoginPO act_getLoginUrl() {
        WebDriverManager.getDriver().get(baseUrl);
        return this;
    }

    public LoginPO act_typeLogin(String login) {
        typeText(LoginElements.IPF_LOGIN.get(), login);
        return this;
    }

    public LoginPO act_typePassword(String password) {
        typeText(LoginElements.IPF_PASSWORD.get(), password);
        return this;
    }

    public LoginPO act_clickLoginButton() {
        click(LoginElements.BTN_SUBMIT.get());
        return this;
    }

    public LoginPO ver_Loged(String user) {
        By by = By.xpath("//*[@data-click='profile_icon']//span[contains(text(),'" + user + "')]");
        Verify.elementVisible(by);
        return this;
    }
}
