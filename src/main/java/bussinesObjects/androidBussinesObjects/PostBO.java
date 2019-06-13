package bussinesObjects.androidBussinesObjects;

import annotations.Injector;
import pageObjects.androidPO.PostPO;
import pageObjects.androidPO.ProfilePO;

public class PostBO extends BaseBO {

    @Injector
    private PostPO postPO;
    @Injector
    private ProfilePO profilePO;

    public PostBO act_createPost(String text) {
        profilePO.act_scrollToPostArea()
                .act_clickOnPostArea();
        postPO.act_typePostText(text)
                .act_clickPostButton();
        log.info(String.format("Created post with text: [%s]", text));
        return this;
    }
}
