import pages.MessagePage;
import utils.UserData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest extends BaseTest {

    private static MessagePage messagePage;
    private String message;
    private int dialogNum;

    @BeforeEach
    public void setUp() {
        messagePage = loginPage
                .login(UserData.user1)
                .goToMessage();
        dialogNum = messagePage.generateDialogNum();
        message = messagePage.generateMessage();
    }

    @Test
    public void newMessageTest() {
        messagePage.sendMessage(dialogNum, message);
        messagePage.checkIfMessageSent(dialogNum, message);
    }

    @Test
    public void deleteMessageTest() {
        messagePage.prepareMessage(dialogNum, message);
        messagePage.deleteMessage(dialogNum);
        messagePage.checkIfMessageDeleted(dialogNum, message);
    }

    @Test
    public void replyMessageTest() {
        messagePage.prepareMessage(dialogNum, message);
        messagePage.replyLastMessage(dialogNum, message);
        messagePage.checkIfMessageReplied(dialogNum, message);
    }
}
