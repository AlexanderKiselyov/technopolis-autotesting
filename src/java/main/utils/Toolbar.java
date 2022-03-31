package utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Toolbar {

    public final String TOOLBAR = ".//ul[@class='toolbar_nav']";
    public final String MUSIC_BUTTON = ".//*[@id='music_toolbar_button']";
    public final String MESSAGE_BUTTON = ".//*[@id='msg_toolbar_button']";
    private final SelenideElement toolbar;

    public Toolbar() {
        toolbar = $(byXpath(TOOLBAR));
    }

    public SelenideElement getMusicPage() {
        return toolbar.$(byXpath(MUSIC_BUTTON));
    }

    public SelenideElement getMessagePage() {
        return toolbar.$(byXpath(MESSAGE_BUTTON));
    }
}
