import pages.MessagePage;
import utils.UserData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageSendTest extends BaseTest {

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
    public void sendMessageTest() {
        messagePage.sendMessage(dialogNum, message);
        messagePage.checkIfMessageSent(dialogNum, message);
    }

    @AfterEach
    public void setDown() {
        messagePage.deleteMessage(dialogNum);
        super.setDown();
    }
}
