package pageObjects;

import driver.WebDriverManager;
import elements.BaseElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Verify;

public class LoginPO extends BasePO {

    @FindBy(xpath = "//*[contains(@type,'email')]")
    private BaseElement login;

    @FindBy(css = "#pass")
    private BaseElement password;

    @FindBy(xpath = "//*[contains(@type,'submit')]")
    private BaseElement submit;

    public LoginPO act_getLoginUrl() {
        WebDriverManager.getDriver().get(utill.getBaseUrl());
        return this;
    }

    public LoginPO act_typeLogin(String login) {
        this.login.sendKeys(login);
        return this;
    }

    public LoginPO act_typePassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public LoginPO act_clickLoginButton() {
        submit.click();
        return this;
    }

    public LoginPO ver_Loged(String user) {
        By by = By.xpath("//*[@data-click='profile_icon']//span[contains(text(),'" + user + "')]");
        WebElement webElement = WebDriverManager.getDriver().findElement(by);
        Verify.elementVisible(webElement);
        return this;
    }
}
