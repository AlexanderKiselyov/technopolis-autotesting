import pages.MessagePage;
import utils.UserData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MessageTest extends BaseTest {

    private static MessagePage messagePage;

    @BeforeEach
    public void setUp() {
        messagePage = loginPage
                .login(UserData.user1)
                .goToMessage();
    }

    // TODO
    @Test
    public void newMessageTest() {
        messagePage.sendMessage("msg");
        messagePage.checkIfMessageSent();
    }

    // TODO
    @Deprecated //WIP
    public void deleteMessageTest() {
        messagePage.deleteMessage("msg");
        messagePage.checkIfMessageDeleted();
    }
}
