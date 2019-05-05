package bussinesObjects;

import annotations.Injector;
import pageObjects.HeaderPO;
import pageObjects.LoginPO;

public class LoginBO extends BaseBO {

    @Injector
    private LoginPO loginPO;
    @Injector
    private HeaderPO headerPO;

    public LoginBO act_logIn(String login, String password) {
        loginPO.act_getLoginUrl()
                .act_typeLogin(login)
                .act_typePassword(password)
                .act_clickLoginButton();
        log.info(String.format("Successfully logged with such credentials: [%s], and [%s]", login, password));
        return this;
    }

    public LoginBO act_getLoginUrl() {
        loginPO.act_getLoginUrl();
        return this;
    }
}
