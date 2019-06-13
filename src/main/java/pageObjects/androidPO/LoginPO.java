package pageObjects.androidPO;

import static pageObjects.androidPO.LoginPO.LoginElements.*;

import org.openqa.selenium.By;

public class LoginPO extends BasePO {
    public enum LoginElements {
        IPF_LOGIN(By.xpath("//*[contains(@content-desc, 'Username')]")),
        IPF_PASSWORD(By.xpath("//*[contains(@content-desc, 'Password')]")),
        BTN_SUBMIT(By.xpath("//*[contains(@content-desc, 'Login')]")),
        BTN_NOT_NOW(By.xpath("//*[contains(@text, 'Not Now')]"));

        private By by;

        LoginElements(By by) {
            this.by = by;
        }

        public By get() {
            return by;
        }
    }

    public LoginPO act_typeLogin(String login) {
        typeText(IPF_LOGIN.get(), login);
        return this;
    }

    public LoginPO act_typePassword(String password) {
        typeText(IPF_PASSWORD.get(), password);
        return this;
    }

    public LoginPO act_clickLoginButton() {
        click(BTN_SUBMIT.get());
        return this;
    }

    public LoginPO act_clickNotNow() {
        click(BTN_NOT_NOW.get());
        return this;
    }
}
