package bussinesObjects;

import annotations.Injector;
import pageObjects.PostPO;

public class PostBO extends BaseBO {

    @Injector
    private PostPO postPO;

    public PostBO act_createPostWithText(String text) {
        postPO.act_typePostText(text)
                .act_clickPostButton()
                .ver_makePostLabel();
        return this;
    }
}
