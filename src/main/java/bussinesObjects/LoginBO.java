package bussinesObjects;

import annotations.Injector;
import pageObjects.LoginPO;

public class LoginBO extends BaseBO {

    @Injector
    private LoginPO loginPO;

    public LoginBO logIn(String login, String password) {
        loginPO.act_getLoginUrl()
                .act_typeLogin(login)
                .act_typePassword(password)
                .act_clickLoginButton();
        log.info(String.format("Logging with such credentials: [%s], and [%s]", login, password));
        return this;
    }
}
