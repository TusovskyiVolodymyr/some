package bussinesObjects;

import annotations.Injector;
import pageObjects.UserProfilePO;

import static pageObjects.UserProfilePO.TimeLineElements;

public class UserProfileBO extends BaseBO {

    @Injector
    private UserProfilePO userProfilePO;

    public UserProfileBO act_chooseFriendByFullName(String fullName) {
        userProfilePO.act_chooseFromTimeLine(TimeLineElements.FRIENDS)
                .act_searchFriendByFullName(fullName)
                .act_chooseFriendByFullName(fullName);
        log.info(String.format("Clicking on [%s] from friends list", fullName));
        return this;
    }
}
