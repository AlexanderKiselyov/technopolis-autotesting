package main.utils;

public class LocatorData {
    public static final String TOOLBAR = ".//ul[@class='toolbar_nav']";

    public static final String LOGIN_URL = "https://ok.ru/";
    public static final String LOGIN_FIELD = ".//input[@id='field_email']";
    public static final String LOGIN_PASSWORD_FIELD = ".//input[@id='field_password']";
    public static final String LOGIN_ENTER_FIELD = ".//input[@data-l='t,sign_in']";

    public static final String MUSIC_BUTTON = ".//*[@id='music_toolbar_button']";
    public static final String MUSIC_TRACKS = ".//wm-track[@data-tsid='track']";
    public static final String MUSIC_TRACK_PLAYING = ".//wm-play-control[@playing]";

    public static final String MESSAGE_BUTTON = ".//*[@id='msg_toolbar_button']";
}
