package utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Toolbar {

    public static final By TOOLBAR = byXpath(".//ul[@class='toolbar_nav']");
    public static final By MUSIC_BUTTON = byXpath(".//*[@id='music_toolbar_button']");
    public static final By MESSAGE_BUTTON = byXpath(".//*[@id='msg_toolbar_button']");
    private final SelenideElement toolbar;

    public Toolbar() {
        toolbar = $(TOOLBAR);
    }

    public SelenideElement getMusicPage() {
        return toolbar.$(MUSIC_BUTTON);
    }

    public SelenideElement getMessagePage() {
        return toolbar.$(MESSAGE_BUTTON);
    }
}
