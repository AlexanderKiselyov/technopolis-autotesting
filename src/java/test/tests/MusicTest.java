package test.tests;

import com.codeborne.selenide.WebDriverRunner;
import main.pages.LoginPage;
import main.utils.UserData;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;

public class MusicTest {
    private static LoginPage loginPage;

    @BeforeAll
    public static void Start() throws Exception {
        System.setProperty("webdriver.chrome.driver", "d:\\programes\\chromedriver.exe");
        loginPage = new LoginPage();
    }

    @Test
    public void MusicPlayTest() throws Exception {
        boolean isTrackPlaying = loginPage.login(UserData.user1).goToMusic().playMusicTrack();
        Assertions.assertTrue(isTrackPlaying);
    }

    @AfterAll
    public static void Stop() {
        closeWindow();
        WebDriverRunner.closeWebDriver();
    }
}
