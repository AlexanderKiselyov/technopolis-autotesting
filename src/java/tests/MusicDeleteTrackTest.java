import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.MusicPage;
import utils.UserData;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicDeleteTrackTest extends BaseTest {
    private static MusicPage musicPage;
    private String trackTitle;

    @BeforeEach
    public void setUp() {
        musicPage = loginPage
                .login(UserData.user1)
                .goToMusic();
        musicPage.deleteAllTracks();
        int trackNum = musicPage.randomTrackNum();
        trackTitle = musicPage.addMusicTrack(trackNum);
    }

    @Test
    public void deleteTrackTest() {
        musicPage.deleteTrack(trackTitle);
        assertTrue(musicPage.isTrackDeleted(trackTitle));
    }

    @AfterEach
    public void setDown() {
        musicPage.deleteAllTracks();
        super.setDown();
    }
}
