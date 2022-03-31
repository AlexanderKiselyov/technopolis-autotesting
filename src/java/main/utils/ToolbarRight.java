package utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ToolbarRight {

    private final SelenideElement toolbarRight;
    private final SelenideElement exitButton;
    private final SelenideElement exitConfirmButton;
    private final String TOOLBAR_RIGHT = ".//*[contains(@id, 'ToolbarUserDropdown')]//*[contains(@class, 'toolbar_dropdown_w')]";
    private final String TOOLBAR_RIGHT_EXIT_BUTTON = ".//a[@data-l='t,logout']";
    private final String TOOLBAR_RIGHT_EXIT_CONFIRM_BUTTON = ".//input[@data-l='t,logout']";

    public ToolbarRight() {
        toolbarRight = $(byXpath(TOOLBAR_RIGHT));
        exitButton = $(byXpath(TOOLBAR_RIGHT_EXIT_BUTTON));
        exitConfirmButton = $(byXpath(TOOLBAR_RIGHT_EXIT_CONFIRM_BUTTON));
    }

    public void exitWithCheck() {
        toolbarRight
                .shouldBe(Condition.visible.because("No toolbar right found!"))
                .click();
        exitButton
                .shouldBe(Condition.visible.because("No exit button found!"))
                .click();
        exitConfirmButton
                .shouldBe(Condition.visible.because("No exit confirm button found!"))
                .click();
    }

}
