import pages.MessagePage;
import utils.UserData;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;

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
        messagePage.sendMessage(dialogNum, message);
        countMessagesBefore = messagePage.getAllMessagesFromDialog(dialogNum).size();
    }

    @Test
    public void replyMessageTest() {
        messagePage.replyLastMessage(dialogNum, message);
        messagePage.checkIfMessageReplied(dialogNum, message);

        ElementsCollection messages = messagePage.getAllMessagesFromDialog(dialogNum);

        assertAll("messages",
                () -> assertThat(messages, is(not(empty()))),
                () -> assertThat(messages, hasSize(countMessagesBefore + 1))
        );
    }

    @AfterEach
    public void setDown() {
        // удаляем 2 последних сообщения: и отправленное в BeforeEach сообщение, и сообщение-ответ
        messagePage.deleteLastMessage(dialogNum);
        Selenide.refresh();
        messagePage.deleteLastMessage(dialogNum);
        super.setDown();
    }
}
