package utils;

import com.codeborne.selenide.Condition;
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

    public void exitWithCheck() {
        toolbarRight.shouldBe(Condition.visible.because("No toolbar right found!")).click();
        exitButton.shouldBe(Condition.visible.because("No exit button found!")).click();
        exitConfirmButton.shouldBe(Condition.visible.because("No exit confirm button found!")).click();
    }

}
