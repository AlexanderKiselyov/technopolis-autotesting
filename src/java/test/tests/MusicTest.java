package tests;

import pages.LoginPage;
import pages.MusicPage;
import utils.UserData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicTest extends BaseTest {
    private static LoginPage loginPage;
    private static MusicPage musicPage;

    @BeforeAll
    public static void Start() throws Exception {
        loginPage = new LoginPage();
    }

    @BeforeEach
    public void setup() {
        musicPage = null;
    }

    @Test
    public void PlayRandomTrackTest() throws Exception {
        musicPage = loginPage.login(UserData.user1).goToMusic();
        boolean isTrackPlaying = musicPage.playMusicTrack();
        assertTrue(isTrackPlaying);
    }

    @AfterEach
    public void setDown() {
        musicPage.logout();
    }
}
