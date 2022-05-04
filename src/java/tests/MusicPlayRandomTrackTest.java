import pages.LoginPage;
import pages.MusicPage;
import utils.UserData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MusicPlayRandomTrackTest extends BaseTest {
    
    private static MusicPage musicPage;
    static LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        musicPage = loginPage
                .login(UserData.user1)
                .goToMusic();
    }

    @Test
    public void playRandomTrackTest() {
        int trackNum = musicPage.randomTrackNum();
        musicPage.playMusicTrack(trackNum);
        musicPage.checkIfTrackIsPlaying(trackNum);
    }

    @AfterEach
    public void setDown() {
        loginPage.logout();
    }
}
