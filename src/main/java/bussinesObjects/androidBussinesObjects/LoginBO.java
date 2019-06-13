package bussinesObjects.androidBussinesObjects;

import annotations.Injector;
import pageObjects.androidPO.LoginPO;

public class LoginBO extends BaseBO {

    @Injector
    private LoginPO loginPO;

    public LoginBO act_logIn(String login, String password) {
        loginPO.act_typeLogin(login)
                .act_typePassword(password)
                .act_clickLoginButton()
                .act_clickNotNow();
        log.info(String.format("Successfully logged with such credentials: [%s], and [%s]", login, password));
        return this;
    }
}
