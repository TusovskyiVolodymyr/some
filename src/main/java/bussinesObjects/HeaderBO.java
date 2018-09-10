package bussinesObjects;

import annotations.Injector;
import pageObjects.HeaderPO;

public class HeaderBO extends BaseBO {

    @Injector
    private HeaderPO headerPO;

    public BaseBO act_clickUserProfileIcon() {
        log.info("Clicking user profile icon");
        headerPO.act_clickUserProfileIcon();
        return this;
    }
}
