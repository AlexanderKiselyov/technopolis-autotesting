package utils;

public class LocatorData {
    public static final String TOOLBAR = ".//ul[@class='toolbar_nav']";

    public static final String TOOLBAR_RIGHT = ".//*[contains(@id, 'ToolbarUserDropdown')]//*[contains(@class, 'toolbar_dropdown_w')]";
    public static final String TOOLBAR_RIGHT_EXIT_BUTTON = ".//a[@data-l='t,logout']";
    public static final String TOOLBAR_RIGHT_EXIT_CONFIRM_BUTTON = ".//input[@data-l='t,logout']";

    public static final String LOGIN_URL = "https://ok.ru/";
    public static final String LOGIN_FIELD = ".//input[@id='field_email']";
    public static final String LOGIN_PASSWORD_FIELD = ".//input[@id='field_password']";
    public static final String LOGIN_ENTER_FIELD = ".//input[@data-l='t,sign_in']";

    public static final String MUSIC_BUTTON = ".//*[@id='music_toolbar_button']";
    public static final String MUSIC_MY_MUSIC_BUTTON = ".//*[text()='Моя музыка']";
    public static final String MUSIC_TRACKS = ".//wm-track[@data-tsid='track']";
    public static final String MUSIC_TRACK_PLAYING = ".//wm-play-control[@playing]";

    public static final String MESSAGE_BUTTON = ".//*[@id='msg_toolbar_button']";
    public static final String MESSAGE_NEW_DIALOG_BUTTON = ".//msg-button[@data-tsid='open_plus_button']";
    public static final String MESSAGE_DIALOGS = "msg-parsed-text";
    public static final String MESSAGE_INPUT_FIELD = ".//msg-input[@data-tsid='write_msg_input']";
    public static final String MESSAGE_LAST_SEND_MESSAGE = "msg-message";
    public static final String MESSAGE_SETTINGS = ".//msg-button[@data-tsid='more_message']";
    public static final String MESSAGE_DELETE = ".//msg-tico[@icon='delete']";
}
