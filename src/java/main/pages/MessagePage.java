package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.Random;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage extends BasePage {

    private final String MESSAGE_NEW_DIALOG_BUTTON = ".//msg-button[@data-tsid='open_plus_button']";
    private static final String MESSAGE_DIALOGS = "msg-chats-list-item";
    private final String MESSAGE_INPUT_FIELD = ".//msg-input[@data-tsid='write_msg_input']";
    private final String MESSAGE_SETTINGS = ".//msg-button[@data-tsid='more_message']";
    private final String MESSAGE_REPLY_BUTTON = ".//msg-button[@data-tsid='reply_message']";
    private final String REPLIED_MESSAGE_TEXT = ".//msg-parsed-text[@data-tsid='replied_text']";
    private final String MESSAGE_TEXT = ".//msg-parsed-text[@data-tsid='message_text']";
    private final String MESSAGE_DELETE = ".//msg-tico[@data-tsid='remove_msg_button']";
    private final String MESSAGE_UNREAD = ".//msg-tico[@data-tsid='unread_msg_button']";
    private final String UNREAD_MESSAGE_DELIMETER = ".//*[@class='unread']";
    private final String NEW_MESSAGES_NOTIFICATION = ".//msg-notification-bubble[@data-tsid='new_msg_bubble']";
    private final String MESSAGE_TITLE = ".//*[contains(@key, 'title') and text()='Сообщения']";
    private final String MESSAGE_CONFIRM_DELETION_BUTTON = ".//msg-button[@data-tsid='confirm-primary']";
    private final String MESSAGES_LIST = "msg-message";
    private static final ElementsCollection dialogs = $$(byCssSelector(MESSAGE_DIALOGS));

    public MessagePage() {
        checkIfPresent();
        dialogs.shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1).because("No dialogs found!"));
    }

    private SelenideElement lastMessage(String message) {
        return $(byXpath(".//msg-parsed-text[text()='" + message + "']"));
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
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        lastMessage(message)
                .shouldBe(Condition.visible.because("No messages found!"));
    }

    public void deleteMessage(int dialogNum) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();

        $$(byCssSelector(MESSAGES_LIST))
                .last()
                .shouldBe(Condition.visible.because("No messages found!"))
                .hover()
                .$(byXpath(MESSAGE_SETTINGS))
                .shouldBe(Condition.visible.because("Message settings element has not been loaded!"))
                .hover();
        $(byXpath(MESSAGE_DELETE))
                .shouldBe(Condition.visible.because("No messages to delete found!"))
                .click();
        $(byXpath(MESSAGE_CONFIRM_DELETION_BUTTON))
                .shouldBe(Condition.visible.because("Confirmation form has not been loaded!"))
                .click();
    }

    public void checkIfMessageDeleted(int dialogNum, String message) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        lastMessage(message)
                .shouldNotBe(Condition.visible.because("Message hasn't been deleted!"));
    }

    public void replyLastMessage(int dialogNum, String message) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();

        $$(byCssSelector(MESSAGES_LIST))
                .last()
                .shouldBe(Condition.visible.because("No messages found!"))
                .hover()
                .$(byXpath(MESSAGE_REPLY_BUTTON))
                .shouldBe(Condition.visible.because("Reply button has not been loaded!"))
                .click();

        $(byXpath(MESSAGE_INPUT_FIELD))
                .setValue(message)
                .shouldBe(Condition.visible.because("No enter button found!"))
                .pressEnter();
    }

    public void checkIfMessageReplied(int dialogNum, String message) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();

        $$(byCssSelector(MESSAGES_LIST))
                .last()
                .$(byXpath(REPLIED_MESSAGE_TEXT))
                .shouldBe(Condition.matchText(message));
        $$(byCssSelector(MESSAGES_LIST))
                .last()
                .$(byXpath(MESSAGE_TEXT))
                .shouldBe(Condition.matchText(message));
    }

    public void markMessageAsNew(int dialogNum, String message) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();

        $$(byCssSelector(MESSAGES_LIST))
                .last()
                .shouldBe(Condition.visible.because("No messages found!"))
                .hover()
                .$(byXpath(MESSAGE_SETTINGS))
                .shouldBe(Condition.visible.because("Message settings element has not been loaded!"))
                .hover();
        $(byXpath(MESSAGE_UNREAD))
                .shouldBe(Condition.visible.because("No messages were marked as new!"))
                .click();
    }

    public void checkIfMessageMarkedAsNew(int dialogNum, String message) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        $(byXpath(UNREAD_MESSAGE_DELIMETER))
                .shouldBe(Condition.visible.because("Message hasn't been marked as new!"));
        dialogs
                .get(dialogNum)
                .$(By.xpath(NEW_MESSAGES_NOTIFICATION))
                .shouldBe(Condition.visible.because("Message hasn't been marked as new!"));
    }

    public void prepareMessage(int dialogNum, String message) {
        dialogs.get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();
        $(byXpath(MESSAGE_INPUT_FIELD))
                .setValue(message)
                .shouldBe(Condition.visible.because("No enter button found!"))
                .pressEnter();
    }

    public ElementsCollection getAllMessages(int dialogNum) {
        dialogs
                .get(dialogNum)
                .shouldBe(Condition.visible.because("No dialog with the specified number found!"))
                .click();

        return $$(byCssSelector(MESSAGES_LIST)).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));
    }

    @Override
    void checkIfPresent() {
        $(byXpath(MESSAGE_NEW_DIALOG_BUTTON))
                .shouldBe(Condition.visible.because("Message Page has not been loaded: no new dialog button found!"));
        $(byXpath(MESSAGE_TITLE))
                .shouldBe(Condition.visible.because("Message Page has not been loaded: no title found!"));
    }
}
