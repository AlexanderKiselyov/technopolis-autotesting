package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage extends BasePage {

    private final String MESSAGE_NEW_DIALOG_BUTTON = ".//msg-button[@data-tsid='open_plus_button']";
    private static final String MESSAGE_DIALOGS = "msg-parsed-text";
    private final String MESSAGE_INPUT_FIELD = ".//msg-input[@data-tsid='write_msg_input']";
    private final String MESSAGE_SETTINGS = ".//msg-button[@data-tsid='more_message']";
    private final String MESSAGE_DELETE = ".//msg-tico[@data-tsid='remove_msg_button']";
    private final String MESSAGE_TITLE = ".//*[contains(@key, 'title') and text()='Сообщения']";
    private static final ElementsCollection dialogs = $$(byCssSelector(MESSAGE_DIALOGS));

    public MessagePage() {
        checkIfPresent();
        dialogs.shouldHave(CollectionCondition.sizeGreaterThan(1).because("No dialogs found!"));
    }

    private SelenideElement lastMessage(String message) {
        return $(byXpath(".//*[text()='" + message + "']"));
    }

    public String generateMessage() {
        return String.valueOf(System.currentTimeMillis());
    }

    public int generateDialogNum() {
        Random r = new Random();
        return r.nextInt(dialogs.size());
    }

    public void sendMessage(int dialogNum, String message) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        $(byXpath(MESSAGE_INPUT_FIELD))
                .setValue(message)
                .shouldBe(Condition.visible.because("No enter button found!"))
                .pressEnter();
    }

    public void checkIfMessageSent(int dialogNum, String message) {
        dialogs
                .get(dialogNum).shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        lastMessage(message)
                .shouldBe(Condition.visible.because("No messages found!"));
    }

    // TODO
    public void deleteMessage(int dialogNum, String message) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .hover();

        lastMessage(message)
                .shouldBe(Condition.visible.because("No messages found!"))
                .hover();
        $(byXpath(MESSAGE_SETTINGS))
                .should(Condition.visible.because("Message settings element has not been loaded!"))
                .hover();
        $(byXpath(MESSAGE_DELETE))
                .shouldBe(Condition.visible.because("No messages to delete found!"))
                .click();
    }

    public void prepareMessageForDeleting(int dialogNum, String message) {
        dialogs.get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        $(byXpath(MESSAGE_INPUT_FIELD))
                .setValue(message)
                .shouldBe(Condition.visible.because("No enter button found!"))
                .pressEnter();
    }

    public void checkIfMessageDeleted(int dialogNum, String message) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        lastMessage(message)
                .shouldNotBe(Condition.visible.because("Message hasn't been deleted!"));
    }

    @Override
    void checkIfPresent() {
        $(byXpath(MESSAGE_NEW_DIALOG_BUTTON))
                .shouldBe(Condition.visible.because("Message Page has not been loaded: no new dialog button found!"));
        $(byXpath(MESSAGE_TITLE))
                .shouldBe(Condition.visible.because("Message Page has not been loaded: no title found!"));
    }
}
