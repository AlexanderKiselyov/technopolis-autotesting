package tests;

import pages.LoginPage;
import pages.MessagePage;
import utils.UserData;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {

    private static LoginPage loginPage;
    private static MessagePage messagePage;

    @BeforeAll
    public static void Start() throws Exception {
        loginPage = new LoginPage();
        messagePage = loginPage.login(UserData.user1).goToMessage();
    }

    @Test
    public void NewMessageTest() throws Exception {
        assertTrue(messagePage.sendMessage("msg"));
    }

    @AfterAll
    public static void Stop() {
        messagePage.logout();
        closeWindow();
        WebDriverRunner.closeWebDriver();
    }
}
