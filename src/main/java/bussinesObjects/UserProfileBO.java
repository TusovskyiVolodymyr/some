package bussinesObjects;

import static pageObjects.UserProfilePO.TimeLineElements;

import annotations.Injector;
import pageObjects.UserProfilePO;

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

    public UserProfileBO act_clickSendMessage() {
        userProfilePO.act_clickSendMessage();
        log.info("Clicked send message button");
        return this;
    }
}
