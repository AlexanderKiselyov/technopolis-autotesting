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
        messagePage = loginPage.login(UserData.user1).goToMessage();
        dialogNum = messagePage.generateDialogNum();
        message = messagePage.generateMessage();
    }

    @Test
    public void newMessageTest() {
        messagePage.sendMessage(dialogNum, message);
        messagePage.checkIfMessageSent(dialogNum, message);
    }

    // TODO
    @Test
    public void deleteMessageTest() {
        messagePage.prepareMessageForDeleting(dialogNum, message);
        messagePage.deleteMessage(dialogNum, message);
        messagePage.checkIfMessageDeleted(dialogNum, message);
    }
}
