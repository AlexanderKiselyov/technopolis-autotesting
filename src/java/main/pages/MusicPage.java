package pages;

import utils.LocatorData;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MusicPage extends BasePage {

    private static final ElementsCollection tracks = $$(byXpath(LocatorData.MUSIC_TRACKS));

    public MusicPage() {
        checkIfPresent();
        tracks.shouldHave(CollectionCondition.sizeGreaterThan(1).because("No tracks found!"));
    }

    public int randomTrackNum() {
        Random r = new Random();
        return r.nextInt(tracks.size());
    }

    public void playMusicTrack(int trackNum) {
        tracks.get(trackNum).shouldBe(Condition.visible.because("No track with the specified number found!")).click();
    }

    public void checkIfTrackIsPlaying(int trackNum) {
        tracks.get(trackNum).$(byXpath(LocatorData.MUSIC_TRACK_PLAYING)).should(Condition.visible.because("Music track has not been found!"));
    }

    @Override
    void checkIfPresent() {
        $(byXpath(LocatorData.MUSIC_MY_MUSIC_BUTTON)).shouldBe(Condition.visible.because("Music Page has not been loaded: no my music button found!"));
        $(byXpath(LocatorData.MUSIC_COLLECTIONS_BUTTON)).shouldBe(Condition.visible.because("Music Page has not been loaded: no music collections button found!"));
    }
}
