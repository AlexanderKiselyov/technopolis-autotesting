package utils;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ToolbarRight {

    private final SelenideElement toolbarRight;
    private final SelenideElement exitButton;
    private final SelenideElement exitConfirmButton;

    public ToolbarRight() {
        toolbarRight = $(byXpath(LocatorData.TOOLBAR_RIGHT));
        exitButton = $(byXpath(LocatorData.TOOLBAR_RIGHT_EXIT_BUTTON));
        exitConfirmButton = $(byXpath(LocatorData.TOOLBAR_RIGHT_EXIT_CONFIRM_BUTTON));
    }

    public void exit() {
        toolbarRight.click();
        exitButton.click();
    }

    public void exitWithCheck() {
        toolbarRight.click();
        exitButton.click();
        exitConfirmButton.click();
    }

}
