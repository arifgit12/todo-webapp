package edu.aam.app.model.enums;

public enum LogType implements ValueEnum<Integer> {

    /**
     * Blog initialization
     */
    BLOG_INITIALIZED(0),

    /**
     * Post published
     */
    POST_PUBLISHED(5),

    /**
     * Post edited
     */
    POST_EDITED(15),

    /**
     * Post deleted
     */
    POST_DELETED(20),

    /**
     * Logged in
     */
    LOGGED_IN(25),

    /**
     * Logged out
     */
    LOGGED_OUT(30),

    /**
     * Logged failed.
     */
    LOGIN_FAILED(35),

    /**
     * Updated the blogger password
     */
    PASSWORD_UPDATED(40),

    /**
     * Updated the blogger profile
     */
    PROFILE_UPDATED(45),

    /**
     * Logged pre check
     */
    LOGGED_PRE_CHECK(70),

    SEND_GRID_MAIL_FAILED(80);

    private final Integer value;

    LogType(Integer value) {
        this.value = value;
    }


    @Override
    public Integer getValue() {
        return value;
    }
}
