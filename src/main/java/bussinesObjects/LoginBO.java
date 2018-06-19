package bussinesObjects;

import pageObjects.LoginPO;
import utils.Injector;

public class LoginBO extends BaseBO{

    @Injector
    private LoginPO loginPO;

    public LoginBO logIn(String login, String password) {
        loginPO.act_getLoginUrl();
        loginPO.act_typeLogin(login);
        loginPO.act_typePassword(password);
        loginPO.act_clickLoginButton();
        return this;
    }

    public LoginBO getLoginUrl(){
        loginPO.act_getLoginUrl();
        return this;
    }
}
