import pages.MusicPage;
import utils.UserData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

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
        assertThat(musicPage.isTrackDeleted(trackTitle), is(true));
    }

    @AfterEach
    public void setDown() {
        musicPage.deleteAllTracks();
        super.setDown();
    }
}
