package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.Random;
import org.openqa.selenium.By;
import utils.Toolbar;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MusicPage extends BasePage {

    private static final By MUSIC_RECOMMENDATIONS_BUTTON = byXpath(".//a[@data-l='t,showcase']");
    private static final By MUSIC_MY_MUSIC_BUTTON = byXpath(".//a[@data-l='t,library']//*[text()='Моя музыка']//..");
    private static final By MUSIC_COLLECTIONS_BUTTON = byXpath(".//*[text()='Сборники']");
    private static final By MUSIC_TRACKS = byXpath(".//wm-track[@data-tsid='track']");
    private static final By MUSIC_TRACK_PLAYING = byXpath(".//wm-play-control[@playing]");
    private static final By MUSIC_MY_MUSIC_TRACK_LIST = byXpath(".//wm-portlet[@data-l='t,tracks']//wm-tracks-list//wm-track[@data-tsid='track']");
    private static final By MUSIC_DELETE_TRACK = byXpath(".//wm-icon[@data-tsid='remove_track']");
    private static final By MUSIC_ADD_TRACK = byXpath(".//wm-track-add-button[@data-tsid='track_add']");
    private static final By MUSIC_TRACK_TITLE = byXpath(".//a[@data-l='t,title']");
    private final ElementsCollection tracks = $$(MUSIC_TRACKS);
    private final ElementsCollection myTracks = $$(MUSIC_MY_MUSIC_TRACK_LIST);

    public MusicPage() {

    }

    @Override
    protected void load() {
        $(Toolbar.MUSIC_BUTTON)
                .shouldBe(visible.because("Music button is not visible!"));
    }

    @Override
    protected void isLoaded() throws Error {
        $(MUSIC_MY_MUSIC_BUTTON)
                .shouldBe(visible.because("Music Page has not been loaded: no my music button found!"));
        $(MUSIC_COLLECTIONS_BUTTON)
                .shouldBe(visible.because("Music Page has not been loaded: no music collections button found!"));
    }

    public int randomTrackNum() {
        goToRecommendations();
        tracks.shouldHave(CollectionCondition.sizeGreaterThan(1).because("No tracks found!"));
        Random r = new Random();
        return r.nextInt(tracks.size());
    }

    public void playMusicTrack(int trackNum) {
        goToRecommendations();
        tracks
                .shouldHave(CollectionCondition.sizeGreaterThan(1).because("No tracks found!"))
                .get(trackNum)
                .shouldBe(visible.because("No track with the specified number found!"))
                .click();
    }

    public void checkIfTrackIsPlaying(int trackNum) {
        tracks
                .get(trackNum)
                .shouldBe(visible.because("No track with the specified number found!"))
                .$(MUSIC_TRACK_PLAYING)
                .should(visible.because("Music track has not been found!"));
    }

    public void deleteAllTracks() {
        goToMyMusic();
        for (SelenideElement myTrack : myTracks) {
            myTrack
                    .shouldBe(visible.because("Track is unavailable!"))
                    .hover()
                    .$(MUSIC_DELETE_TRACK)
                    .shouldBe(visible.because("Cannot delete track!"))
                    .click();
        }
    }

    public String addMusicTrack(int trackNum) {
        goToRecommendations();
        SelenideElement track = tracks.get(trackNum);
        String trackTitle = track.$(MUSIC_TRACK_TITLE).text();
        track
                .shouldBe(visible.because("Track is unavailable!"))
                .hover()
                .$(MUSIC_ADD_TRACK)
                .shouldBe(visible.because("Cannot add track!"))
                .click();
        return trackTitle;
    }

    public String getAddedTrackName() {
        goToMyMusic();
        return myTracks
                .shouldHave(CollectionCondition.size(1).because("Collection size is not 1!"))
                .get(0)
                .$(MUSIC_TRACK_TITLE)
                .text();
    }

    public void deleteTrack(String trackTitle) {
        goToMyMusic();
        myTracks.shouldHave(CollectionCondition.size(1).because("Collection size is not 1!"));
        for (SelenideElement myTrack : myTracks) {
            if (myTrack.$(MUSIC_TRACK_TITLE).text().equals(trackTitle)) {
                myTrack
                        .shouldBe(visible.because("Track is unavailable!"))
                        .hover()
                        .$(MUSIC_DELETE_TRACK)
                        .shouldBe(visible.because("Cannot delete track!"))
                        .click();
            }
        }
    }

    public boolean isTrackDeleted(String trackTitle) {
        goToRecommendations();
        goToMyMusic();
        for (SelenideElement myTrack : myTracks) {
            if (myTrack.$(MUSIC_TRACK_TITLE).text().equals(trackTitle)) {
                return false;
            }
        }
        return true;
    }

    private void goToRecommendations() {
        $(MUSIC_RECOMMENDATIONS_BUTTON)
                .shouldBe(visible.because("No recommendation music button found!"))
                .click();
    }

    private void goToMyMusic() {
        $(MUSIC_MY_MUSIC_BUTTON)
                .shouldBe(visible.because("No my music button found!"))
                .click();
    }
}
