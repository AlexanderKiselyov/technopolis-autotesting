import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pages.MessagePage;
import utils.UserData;

public class MessageReplyTest extends BaseTest {

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
        messagePage.prepareMessage(dialogNum, message);
    }

    @Test
    public void replyMessageTest() {
        messagePage.replyLastMessage(dialogNum, message);
        messagePage.checkIfMessageReplied(dialogNum, message);
    }

    @AfterEach
    public void setDown() {
        messagePage.deleteMessage(dialogNum);
        super.setDown();
    }
}
