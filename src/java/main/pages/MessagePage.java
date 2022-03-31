package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage extends BasePage {

    private final String MESSAGE_NEW_DIALOG_BUTTON = ".//msg-button[@data-tsid='open_plus_button']";
    private static final String MESSAGE_DIALOGS = "msg-parsed-text";
    private final String MESSAGE_INPUT_FIELD = ".//msg-input[@data-tsid='write_msg_input']";
    private final String MESSAGE_LAST_SEND_MESSAGE = "msg-message";
    private final String MESSAGE_SETTINGS = ".//msg-button[@data-tsid='more_message']";
    private final String MESSAGE_DELETE = ".//msg-tico[@icon='delete']";
    private final String MESSAGE_TITLE = ".//*[contains(@key, 'title') and text()='Сообщения']";
    private static final ElementsCollection dialogs = $$(byCssSelector(MESSAGE_DIALOGS));

    public MessagePage() {
        checkIfPresent();
        dialogs.shouldHave(CollectionCondition.sizeGreaterThan(1).because("No dialogs found!"));
    }

    public void sendMessage(String message) {
        Random r = new Random();
        int dialogNum = r.nextInt(dialogs.size());
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        $(byXpath(MESSAGE_INPUT_FIELD))
                .setValue(message)
                .shouldBe(Condition.visible.because("No enter button found!"))
                .pressEnter();
    }

    // TODO
    public void checkIfMessageSent() {

    }

    public void deleteMessage(String message) {
        dialogs
                .get(0)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        $(byCssSelector(MESSAGE_LAST_SEND_MESSAGE))
                .shouldBe(Condition.visible.because("No messages found!"))
                .hover();
        $(byXpath(MESSAGE_SETTINGS))
                .should(Condition.visible.because("Message settings element has not been loaded!"))
                .hover();
        $(byXpath(MESSAGE_DELETE))
                .shouldBe(Condition.visible.because("No messages to delete found!"))
                .click();
    }

    // TODO
    public void checkIfMessageDeleted() {

    }

    @Override
    void checkIfPresent() {
        $(byXpath(MESSAGE_NEW_DIALOG_BUTTON))
                .shouldBe(Condition.visible.because("Message Page has not been loaded: no new dialog button found!"));
        $(byXpath(MESSAGE_TITLE))
                .shouldBe(Condition.visible.because("Message Page has not been loaded: no title found!"));
    }
}
