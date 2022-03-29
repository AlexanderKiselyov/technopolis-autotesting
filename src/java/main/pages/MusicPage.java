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
        tracks.shouldHave(CollectionCondition.sizeGreaterThan(1));
    }

    public int playMusicTrack() {
        Random r = new Random();
        int trackNum = r.nextInt(tracks.size());
        tracks.get(trackNum).click();
        return trackNum;
    }

    public void checkIfTrackIsPlaying(int trackNum) {
        tracks.get(trackNum).$(byXpath(LocatorData.MUSIC_TRACK_PLAYING)).should(Condition.visible);
    }

    @Override
    void checkIfPresent() {
        $(byXpath(LocatorData.MUSIC_MY_MUSIC_BUTTON)).shouldBe(Condition.visible);
    }
}
