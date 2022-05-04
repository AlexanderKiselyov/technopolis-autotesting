import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.closeWindow;
import static java.util.concurrent.TimeUnit.SECONDS;

@Timeout(value = 30, unit = SECONDS)
public abstract class BaseTest {
    private static final String LOGIN_URL = "https://ok.ru";

    @BeforeAll
    static void start() {
        Configuration.baseUrl = LOGIN_URL;
    }

    @AfterAll
    static void stop() {
        closeWindow();
        closeWebDriver();
    }
}
