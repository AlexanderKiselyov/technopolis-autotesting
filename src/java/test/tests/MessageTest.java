package test.tests;

import main.pages.LoginPage;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;

public class MessageTest {

    private static LoginPage loginPage;

    @BeforeAll
    public static void Start() throws Exception {
        System.setProperty("webdriver.chrome.driver", "d:\\programes\\chromedriver.exe");
        loginPage = new LoginPage();
    }

    @Test
    public void MessageTest() {

    }

    @AfterAll
    public static void Stop() {
        closeWindow();
        WebDriverRunner.closeWebDriver();
    }
}
