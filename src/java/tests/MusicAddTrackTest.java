import pages.MusicPage;
import utils.UserData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MusicAddTrackTest extends BaseTest {

    private static MusicPage musicPage;

    @BeforeEach
    public void setUp() {
        musicPage = loginPage
                .login(UserData.user1)
                .goToMusic();
        musicPage.deleteAllTracks();
    }

    @Test
    public void newTrackAddTest() {
        int trackNum = musicPage.randomTrackNum();
        String trackTitle = musicPage.addMusicTrack(trackNum);
        String currentTrackTitle = musicPage.getAddedTrackName();
        assertEquals(trackTitle, currentTrackTitle);
    }

    @AfterEach
    public void setDown() {
        musicPage.deleteAllTracks();
        super.setDown();
    }
}
