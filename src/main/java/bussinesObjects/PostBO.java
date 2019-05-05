package bussinesObjects;

import annotations.Injector;
import pageObjects.PostPO;

public class PostBO extends BaseBO {

    @Injector
    private PostPO postPO;

    public PostBO act_createPostWithText(String text) {
        postPO.act_typePostText(text)
                .act_clickPostButton();
        log.info("Creating new post with following text: " + text);
        return this;
    }

    public PostBO act_likePostWithText(String text) {
        postPO.act_likePostWithText(text);
        log.info("Liking a post with following text: " + text);
        return this;
    }

    public PostBO ver_postLikedByUser(String userFullName) {
        postPO.ver_postLiked(userFullName);
        log.info("Verified liking a post by user : " + userFullName);
        return this;
    }
}
