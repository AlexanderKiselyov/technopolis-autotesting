import pages.LoginPage;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.closeWindow;

public abstract class BaseTest {

    private final String LOGIN_URL = "https://ok.ru/";
    static LoginPage loginPage;

    @BeforeAll
    static void start() {
        loginPage = new LoginPage();
    }

    @AfterEach
    public void setDown() {
        open(LOGIN_URL);
        loginPage.logout();
    }

    @AfterAll
    static void stop() {
        closeWindow();
        closeWebDriver();
    }
}
