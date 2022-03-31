package pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MusicPage extends BasePage {

    private final String MUSIC_RECOMMENDATIONS_BUTTON = ".//a[@data-l='t,showcase']";
    private final String MUSIC_MY_MUSIC_BUTTON = ".//a[@data-l='t,library']//*[text()='Моя музыка']//..";
    private final String MUSIC_COLLECTIONS_BUTTON = ".//*[text()='Сборники']";
    private static final String MUSIC_TRACKS = ".//wm-track[@data-tsid='track']";
    private final String MUSIC_TRACK_PLAYING = ".//wm-play-control[@playing]";
    private static final String MUSIC_MY_MUSIC_TRACK_LIST = ".//wm-portlet[@data-l='t,tracks']//wm-tracks-list//wm-track[@data-tsid='track']";
    private final String MUSIC_DELETE_TRACK = ".//wm-icon[@data-tsid='remove_track']";
    private final String MUSIC_ADD_TRACK = ".//wm-track-add-button[@data-tsid='track_add']";
    private final String MUSIC_TRACK_TITLE = ".//a[@data-l='t,title']";
    private static final ElementsCollection tracks = $$(byXpath(MUSIC_TRACKS));
    private static final ElementsCollection myTracks = $$(byXpath(MUSIC_MY_MUSIC_TRACK_LIST));

    public MusicPage() {
        checkIfPresent();
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
                .shouldBe(Condition.visible.because("No track with the specified number found!"))
                .click();
    }

    public void checkIfTrackIsPlaying(int trackNum) {
        tracks
                .get(trackNum)
                .shouldBe(Condition.visible.because("No track with the specified number found!"))
                .$(byXpath(MUSIC_TRACK_PLAYING))
                .should(Condition.visible.because("Music track has not been found!"));
    }

    public void deleteAllTracks() {
        goToMyMusic();
        for (SelenideElement myTrack : myTracks) {
            myTrack
                    .shouldBe(Condition.visible.because("Track is unavailable!"))
                    .hover()
                    .$(byXpath(MUSIC_DELETE_TRACK))
                    .shouldBe(Condition.visible.because("Cannot delete track!"))
                    .click();
        }
    }

    public String addMusicTrack(int trackNum) {
        goToRecommendations();
        SelenideElement track = tracks.get(trackNum);
        String trackTitle = track.$(byXpath(MUSIC_TRACK_TITLE)).text();
        track
                .shouldBe(Condition.visible.because("Track is unavailable!"))
                .hover()
                .$(byXpath(MUSIC_ADD_TRACK))
                .shouldBe(Condition.visible.because("Cannot add track!"))
                .click();
        return trackTitle;
    }

    public String getAddedTrackName() {
        goToMyMusic();
        return myTracks
                .shouldHave(CollectionCondition.size(1).because("Collection size is not 1!"))
                .get(0)
                .$(byXpath(MUSIC_TRACK_TITLE))
                .text();
    }

    public void deleteTrack(String trackTitle) {
        goToMyMusic();
        myTracks.shouldHave(CollectionCondition.size(1).because("Collection size is not 1!"));
        for (SelenideElement myTrack : myTracks) {
            if (myTrack.$(byXpath(MUSIC_TRACK_TITLE)).text().equals(trackTitle)) {
                myTrack
                        .shouldBe(Condition.visible.because("Track is unavailable!"))
                        .hover()
                        .$(byXpath(MUSIC_DELETE_TRACK))
                        .shouldBe(Condition.visible.because("Cannot delete track!"))
                        .click();
            }
        }
    }

    public boolean isTrackDeleted(String trackTitle) {
        goToRecommendations();
        goToMyMusic();
        for (SelenideElement myTrack : myTracks) {
            if (myTrack.$(byXpath(MUSIC_TRACK_TITLE)).text().equals(trackTitle)) {
                return false;
            }
        }
        return true;
    }

    private void goToRecommendations() {
        $(byXpath(MUSIC_RECOMMENDATIONS_BUTTON))
                .shouldBe(Condition.visible.because("No recommendation music button found!"))
                .click();
    }

    private void goToMyMusic() {
        $(byXpath(MUSIC_MY_MUSIC_BUTTON))
                .shouldBe(Condition.visible.because("No my music button found!"))
                .click();
    }

    @Override
    void checkIfPresent() {
        $(byXpath(MUSIC_MY_MUSIC_BUTTON))
                .shouldBe(Condition.visible.because("Music Page has not been loaded: no my music button found!"));
        $(byXpath(MUSIC_COLLECTIONS_BUTTON))
                .shouldBe(Condition.visible.because("Music Page has not been loaded: no music collections button found!"));
    }
}
