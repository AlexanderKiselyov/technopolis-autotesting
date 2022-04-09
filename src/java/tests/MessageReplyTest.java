import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.codeborne.selenide.ElementsCollection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import pages.MessagePage;
import utils.UserData;

public class MessageReplyTest extends BaseTest {

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
        messagePage.prepareMessage(dialogNum, message);
        countMessagesBefore = messagePage.getAllMessages(dialogNum).size();
    }

    @Test
    public void replyMessageTest() {
        messagePage.replyLastMessage(dialogNum, message);
        messagePage.checkIfMessageReplied(dialogNum, message);

        ElementsCollection messages = messagePage.getAllMessages(dialogNum);

        assertThat(messages, is(not(empty())));
        assertThat(messages, hasSize(countMessagesBefore + 1));
    }

    @AfterEach
    public void setDown() {
        messagePage.deleteMessage(dialogNum);
        super.setDown();
    }
}
