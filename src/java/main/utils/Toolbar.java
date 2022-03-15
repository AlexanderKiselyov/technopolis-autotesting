package main.utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Toolbar {

    private final SelenideElement toolbar;

    public Toolbar() {
        toolbar = $(byXpath(LocatorData.TOOLBAR));
    }

    public SelenideElement getMusicElement() {
        return toolbar.$(byXpath(LocatorData.MUSIC_BUTTON));
    }

    public SelenideElement getMessagePage() {
        return toolbar.$(byXpath(LocatorData.MESSAGE_BUTTON));
    }
}
