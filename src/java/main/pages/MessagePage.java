package pages;

import com.codeborne.selenide.ex.ElementNotFound;
import utils.Toolbar;
import utils.ToolbarRight;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.Random;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage extends BasePage {

    private static final By MESSAGE_NEW_DIALOG_BUTTON = byXpath(".//msg-button[@data-tsid='open_plus_button']");
    private static final By MESSAGE_DIALOGS = byCssSelector("msg-chats-list-item");
    private static final By MESSAGE_INPUT_FIELD = byXpath(".//msg-input[@data-tsid='write_msg_input']");
    private static final By MESSAGE_SETTINGS = byXpath(".//msg-button[@data-tsid='more_message']");
    private static final By MESSAGE_REPLY_BUTTON = byXpath(".//msg-button[@data-tsid='reply_message']");
    private static final By REPLIED_MESSAGE_TEXT = byXpath(".//msg-parsed-text[@data-tsid='replied_text']");
    private static final By MESSAGE_TEXT = byXpath(".//msg-parsed-text[@data-tsid='message_text']");
    private static final By MESSAGE_DELETE = byXpath(".//msg-tico[@data-tsid='remove_msg_button']");
    private static final By MESSAGE_UNREAD = byXpath(".//msg-tico[@data-tsid='unread_msg_button']");
    private static final By UNREAD_MESSAGE_DELIMITER = byXpath(".//*[@class='unread']");
    private static final By NEW_MESSAGES_NOTIFICATION = byXpath(".//msg-notification-bubble[@data-tsid='new_msg_bubble']");
    private static final By MESSAGE_TITLE = byXpath(".//*[contains(@key, 'title') and text()='Сообщения']");
    private static final By MESSAGE_CONFIRM_DELETION_BUTTON = byXpath(".//msg-button[@data-tsid='confirm-primary']");
    private static final By MESSAGES_LIST = byCssSelector("msg-message");
    private final ElementsCollection dialogs = $$(MESSAGE_DIALOGS);
    private final ToolbarRight toolbarRight = new ToolbarRight();
    private final Logger logger = LoggerFactory.getLogger(MessagePage.class);

    public MessagePage() {
        dialogs
                .shouldHave(CollectionCondition.sizeGreaterThanOrEqual(1).because("No dialogs found!"));
    }

    @Override
    protected void load() {
        $(Toolbar.MESSAGE_BUTTON)
                .shouldBe(visible.because("Message button is not visible!"));
    }

    @Override
    protected void isLoaded() throws Error {
        $(MESSAGE_NEW_DIALOG_BUTTON)
                .shouldBe(visible.because("Message Page has not been loaded: no new dialog button found!"));
        $(MESSAGE_TITLE)
                .shouldBe(visible.because("Message Page has not been loaded: no title found!"));
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

    public void getDialog(int dialogNum) {
        dialogs
                .get(dialogNum)
                .shouldBe(visible.because("No dialog with the specified number found!"))
                .click();
    }

    public void sendMessageInDialog(int dialogNum, String message) {
        getDialog(dialogNum);
        $(MESSAGE_INPUT_FIELD)
                .setValue(message)
                .shouldBe(visible.because("No enter button found!"))
                .pressEnter();
    }

    public void checkIfMessageSent(int dialogNum, String message) {
        getDialog(dialogNum);
        lastMessage(message)
                .shouldBe(visible.because("No messages found!"));
    }

    public void deleteLastMessageInDialog(int dialogNum) {
        getDialog(dialogNum);
        getLastMessageSettings();
        $(MESSAGE_DELETE)
                .shouldBe(visible.because("No messages to delete found!"))
                .click();
        $(MESSAGE_CONFIRM_DELETION_BUTTON)
                .shouldBe(visible.because("Confirmation form has not been loaded!"))
                .click();
    }

    public void checkIfMessageDeleted(int dialogNum, String message) {
        getDialog(dialogNum);
        lastMessage(message)
                .shouldNotBe(visible.because("Message hasn't been deleted!"));
    }

    public void replyLastMessageInDialog(int dialogNum, String message) {
        getDialog(dialogNum);
        $$(MESSAGES_LIST)
                .last()
                .shouldBe(visible.because("No messages found!"))
                .hover()
                .$(MESSAGE_REPLY_BUTTON)
                .shouldBe(visible.because("Reply button has not been loaded!"))
                .click();

        $(MESSAGE_INPUT_FIELD)
                .setValue(message)
                .shouldBe(visible.because("No enter button found!"))
                .pressEnter();
    }

    public void checkIfMessageReplied(int dialogNum, String message) {
        getDialog(dialogNum);
        $$(MESSAGES_LIST)
                .last()
                .$(REPLIED_MESSAGE_TEXT)
                .shouldBe(matchText(message));
        $$(MESSAGES_LIST)
                .last()
                .$(MESSAGE_TEXT)
                .shouldBe(matchText(message));
    }

    public void markLastMessageAsUnreadInDialog(int dialogNum) {
        getDialog(dialogNum);
        $$(MESSAGES_LIST)
                .last()
                .shouldBe(visible.because("No messages found!"))
                .hover()
                .$(MESSAGE_SETTINGS)
                .shouldBe(visible.because("Message settings element has not been loaded!"))
                .hover();
        $(MESSAGE_UNREAD)
                .shouldBe(visible.because("No messages were marked as new!"))
                .click();
    }

    public void checkIfMessageMarkedAsNew(int dialogNum) {
        getDialog(dialogNum);
        $(UNREAD_MESSAGE_DELIMITER)
                .shouldBe(visible.because("Message hasn't been marked as new!"));
        dialogs
                .get(dialogNum)
                .$(NEW_MESSAGES_NOTIFICATION)
                .shouldBe(visible.because("Message hasn't been marked as new!"));
    }

    public ElementsCollection getAllMessagesFromDialog(int dialogNum) {
        getDialog(dialogNum);
        return $$(MESSAGES_LIST).shouldHave(CollectionCondition.sizeGreaterThanOrEqual(0));
    }

    private void getLastMessageSettings() {
        $$(MESSAGES_LIST)
                .last()
                .shouldBe(visible.because("No messages found!"))
                .hover()
                .$(MESSAGE_SETTINGS)
                .shouldBe(visible.because("Message settings element has not been loaded!"))
                .hover();
    }

    public void logout() {
        try {
            toolbarRight.exitWithCheck();
        } catch (ElementNotFound e) {
            logger.error("Cannot logout." , e);
        }
    }
}
