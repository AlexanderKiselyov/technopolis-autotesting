package utils;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ToolbarRight {

    private final SelenideElement toolbarRight;
    private final SelenideElement exitButton;
    private final SelenideElement exitConfirmButton;
    private static final By TOOLBAR_RIGHT = byXpath(".//*[contains(@id, 'ToolbarUserDropdown')]//*[contains(@class, 'toolbar_dropdown_w')]");
    private static final By TOOLBAR_RIGHT_EXIT_BUTTON = byXpath(".//a[@data-l='t,logout']");
    private static final By TOOLBAR_RIGHT_EXIT_CONFIRM_BUTTON = byXpath(".//input[@data-l='t,logout']");

    public ToolbarRight() {
        toolbarRight = $(TOOLBAR_RIGHT);
        exitButton = $(TOOLBAR_RIGHT_EXIT_BUTTON);
        exitConfirmButton = $(TOOLBAR_RIGHT_EXIT_CONFIRM_BUTTON);
    }

    public void exitWithCheck() {
        toolbarRight
                .shouldBe(visible.because("No toolbar right found!"))
                .click();
        exitButton
                .shouldBe(visible.because("No exit button found!"))
                .click();
        exitConfirmButton
                .shouldBe(visible.because("No exit confirm button found!"))
                .click();
    }

}
