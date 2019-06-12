package bussinesObjects;

import annotations.Injector;
import pageObjects.HeaderPO;

public class HeaderBO extends BaseBO {

    @Injector
    private HeaderPO headerPO;

    public BaseBO act_clickUserProfileIcon() {
        headerPO.act_clickUserProfileIcon();
        log.info("Clicked user profile icon");
        return this;
    }
}
