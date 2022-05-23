import pages.LoginPage;
import pages.MusicPage;
import utils.UserData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MusicAddTrackTest extends BaseTest {

    private static MusicPage musicPage;
    static LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
        musicPage = loginPage
                .login(UserData.user1)
                .goToMusic();
        musicPage.deleteAllTracks();
    }

    @Test
    public void newTrackAddTest() {
        musicPage.goToRecommendations();
        int trackNum = musicPage.randomTrackNum();
        String trackTitle = musicPage.addMusicTrack(trackNum);
        String currentTrackTitle = musicPage.getAddedTrackName();
        assertThat(trackTitle, equalTo(currentTrackTitle));
    }

    @AfterEach
    public void setDown() {
        musicPage.deleteAllTracks();
        musicPage.logout();
    }
}
