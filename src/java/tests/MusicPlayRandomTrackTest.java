import pages.MusicPage;
import utils.UserData;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;

@Timeout(value = 10, unit = SECONDS)
public class MusicPlayRandomTrackTest extends BaseTest {
    
    private static MusicPage musicPage;

    @BeforeEach
    public void setUp() {
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
}
