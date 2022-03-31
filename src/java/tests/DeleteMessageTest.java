import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pages.MessagePage;
import utils.UserData;

public class DeleteMessageTest extends BaseTest {

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
    public void deleteMessageTest() {
        messagePage.deleteMessage(dialogNum);
        messagePage.checkIfMessageDeleted(dialogNum, message);
    }
}
