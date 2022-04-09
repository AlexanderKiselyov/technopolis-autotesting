import pages.MessagePage;
import utils.UserData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.codeborne.selenide.ElementsCollection;

public class MessageSendTest extends BaseTest {

    private static MessagePage messagePage;
    private String message;
    private int dialogNum;
    private int countMessagesBefore;

    @BeforeEach
    public void setUp() {
        messagePage = loginPage
                .login(UserData.user1)
                .goToMessage();
        dialogNum = messagePage.generateDialogNum();
        message = messagePage.generateMessage();
        countMessagesBefore = messagePage.getAllMessages(dialogNum).size();
    }

    @Test
    public void sendMessageTest() {
        messagePage.sendMessage(dialogNum, message);
        messagePage.checkIfMessageSent(dialogNum, message);
        ElementsCollection messages = messagePage.getAllMessages(dialogNum);

        assertThat(messages, is(not(empty())));
        assertThat(messages, hasSize(countMessagesBefore + 1));
    }

    @Nested
    @DisplayName("Test \"Unread\" function")
    public class MessageUnreadTest {

        @BeforeEach
        public void setUp() {
            messagePage.sendMessage(dialogNum, message);
        }

        @Test
        public void markAsNewMessageTest() {
            messagePage.markMessageAsNew(dialogNum, message);
            messagePage.checkIfMessageMarkedAsNew(dialogNum, message);
        }
    }

    @AfterEach
    public void setDown() {
        messagePage.deleteMessage(dialogNum);
        super.setDown();
    }
}
