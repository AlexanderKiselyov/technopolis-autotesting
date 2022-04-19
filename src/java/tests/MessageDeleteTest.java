import pages.MessagePage;
import utils.UserData;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MessageDeleteTest extends BaseTest {

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
        messagePage.sendMessage(dialogNum, message);
        countMessagesBefore = messagePage.getAllMessagesFromDialog(dialogNum).size();
    }

    @Test
    @Timeout(value = 10, unit = SECONDS)
    public void deleteMessageTest() {
        messagePage.deleteLastMessage(dialogNum);
        messagePage.checkIfMessageDeleted(dialogNum, message);
        ElementsCollection messages = messagePage.getAllMessagesFromDialog(dialogNum);

        assertAll("messages",
                () -> assertThat(messages, is(not(empty()))),
                () -> assertThat(messages, hasSize(countMessagesBefore))
        );
    }
}
