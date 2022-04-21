import pages.LoginPage;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.closeWindow;
import static java.util.concurrent.TimeUnit.SECONDS;

@Timeout(value = 30, unit = SECONDS)
public abstract class BaseTest {

    private static final String LOGIN_URL = "https://ok.ru";
    static LoginPage loginPage;

    @BeforeAll
    static void start() {
        Configuration.baseUrl = LOGIN_URL;
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
