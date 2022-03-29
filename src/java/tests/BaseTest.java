import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.LoginPage;
import utils.LocatorData;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.closeWindow;

public abstract class BaseTest {

    static LoginPage loginPage;

    @BeforeAll
    static void start() {
        loginPage = new LoginPage();
    }

    @AfterEach
    public void setDown() {
        open(LocatorData.LOGIN_URL);
        loginPage.logout();
    }

    @AfterAll
    static void stop() {
        closeWindow();
        closeWebDriver();
    }
}
