package bussinesObjects;

import annotations.Injector;
import pageObjects.LoginPO;

public class LoginBO extends BaseBO {

    @Injector
    private LoginPO loginPO;

    public LoginBO logIn(String login, String password) {
        loginPO.act_getLoginUrl();
        loginPO.act_typeLogin(login);
        loginPO.act_typePassword(password);
        loginPO.act_clickLoginButton();
        return this;
    }
}
