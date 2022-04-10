import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ MusicAddTrackTest.class, MusicDeleteTrackTest.class, MusicPlayRandomTrackTest.class })
public class MusicTests {
}
