package test.tests;

import main.pages.LoginPage;
import main.pages.MusicPage;
import main.utils.UserData;

import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.closeWindow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicTest {
    private static LoginPage loginPage;
    private static MusicPage musicPage;

    @BeforeAll
    public static void Start() throws Exception {
        loginPage = new LoginPage();
    }

    @Test
    public void PlayRandomTrackTest() throws Exception {
        musicPage = loginPage.login(UserData.user1).goToMusic();
        boolean isTrackPlaying = musicPage.playMusicTrack();
        assertTrue(isTrackPlaying);
    }

    @AfterAll
    public static void Stop() {
        musicPage.logout();
        closeWindow();
        WebDriverRunner.closeWebDriver();
    }
}
