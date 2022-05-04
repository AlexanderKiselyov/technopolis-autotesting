import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.LoginPage;
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
    static LoginPage loginPage;
    private final Logger logger = LoggerFactory.getLogger(MessageReplyTest.class);

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        messagePage = loginPage
                .login(UserData.user1)
                .goToMessage();
        dialogNum = messagePage.generateDialogNum();
        message = messagePage.generateMessage();
        messagePage.sendMessageInDialog(dialogNum, message);
        countMessagesBefore = messagePage.getAllMessagesFromDialog(dialogNum).size();
    }

    @Test
    public void replyMessageTest() {
        messagePage.replyLastMessageInDialog(dialogNum, message);
        messagePage.checkIfMessageReplied(dialogNum, message);

        ElementsCollection messages = messagePage.getAllMessagesFromDialog(dialogNum);

        assertAll("messages",
                () -> assertThat(messages, is(not(empty()))),
                () -> assertThat(messages, hasSize(countMessagesBefore + 1))
        );
    }

    @AfterEach
    public void setDown() {
        logger.info("удаляем 2 последних сообщения: и отправленное в BeforeEach сообщение, и сообщение-ответ");
        messagePage.deleteLastMessageInDialog(dialogNum);
        Selenide.refresh();
        messagePage.deleteLastMessageInDialog(dialogNum);
        loginPage.logout();
    }
}
