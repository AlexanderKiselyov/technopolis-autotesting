package tests;

import pages.LoginPage;
import pages.MessagePage;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;

public class MessageTest {

    private static LoginPage loginPage;
    private static MessagePage messagePage;

    @BeforeAll
    public static void Start() throws Exception {
        loginPage = new LoginPage();
    }

    @Test
    public void NewMessageTest() {

    }

    @AfterAll
    public static void Stop() {
        messagePage.logout();
        closeWindow();
        WebDriverRunner.closeWebDriver();
    }
}
