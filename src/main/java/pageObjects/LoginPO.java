package pageObjects;

import driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

    public LoginPO act_getLoginUrl() {
        WebDriverManager.getDriver().get(utill.getBaseUrl());
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
        WebElement webElement = WebDriverManager.getDriver().findElement(by);
        Verify.elementVisible(webElement);
        return this;
    }
}
