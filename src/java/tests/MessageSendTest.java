import pages.LoginPage;
import pages.MessagePage;
import utils.UserData;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MessageSendTest extends BaseTest {

    private static MessagePage messagePage;
    private String message;
    private int dialogNum;
    private int countMessagesBefore;
    static LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        messagePage = loginPage
                .login(UserData.user1)
                .goToMessage();
        dialogNum = messagePage.generateDialogNum();
        message = messagePage.generateMessage();
        countMessagesBefore = messagePage.getAllMessagesFromDialog(dialogNum).size();
    }

    @Test
    public void sendMessageTest() {
        messagePage.sendMessageInDialog(dialogNum, message);
        messagePage.checkIfMessageSent(dialogNum, message);
        ElementsCollection messages = messagePage.getAllMessagesFromDialog(dialogNum);

        assertAll("messages",
                () -> assertThat(messages, is(not(empty()))),
                () -> assertThat(messages, hasSize(countMessagesBefore + 1))
        );
    }

    @Nested
    @DisplayName("Test \"Unread\" function")
    public class MessageUnreadTest {

        @BeforeEach
        public void setUp() {
            messagePage.sendMessageInDialog(dialogNum, message);
        }

        @Test
        public void markAsNewMessageTest() {
            messagePage.markLastMessageAsUnreadInDialog(dialogNum);
            messagePage.checkIfMessageMarkedAsNew(dialogNum);
        }
    }

    @AfterEach
    public void setDown() {
        messagePage.deleteLastMessageInDialog(dialogNum);
        messagePage.logout();
    }
}
