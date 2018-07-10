package pageObjects;

import driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends BasePO {

    @FindBy(xpath = "//*[contains(@type,'email')]")
    private WebElement login;

    @FindBy(css = "#pass")
    private WebElement password;

    @FindBy(xpath = "//*[contains(@type,'submit')]")
    private WebElement submit;

    public LoginPO act_getLoginUrl() {
        WebDriverManager.getDriver().get(utill.getBaseUrl());
        return this;
    }

    public LoginPO act_typeLogin(String login) {
        typeText(this.login, login);
        return this;
    }

    public LoginPO act_typePassword(String password) {
        typeText(this.password, password);
        return this;
    }

    public LoginPO act_clickLoginButton() {
        click(submit);
        return this;
    }

    public LoginPO ver_Loged(String user) {
        By by = By.xpath("//*[@data-click='profile_icon']//span[contains(text(),'" + user + "')]");
        WebElement webElement = WebDriverManager.getDriver().findElement(by);
        ver_ElementIsPresent(webElement);
        return this;
    }
}
