package main.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import main.utils.LocatorData;

import java.time.Duration;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$$;

public class MusicPage extends BasePage {

    private static ElementsCollection tracks;

    public MusicPage() throws Exception {
        if (!isPresent()) {
            throw new Exception("ERROR MUSIC PAGE");
        }
        tracks = $$(byXpath(LocatorData.MUSIC_TRACKS));
    }

    public boolean playMusicTrack() {
        Random r = new Random();
        int trackNum = r.nextInt(tracks.shouldHave(CollectionCondition.sizeGreaterThan(1)).size());
        tracks.get(trackNum).click();
        try {
            tracks.get(trackNum).$(byXpath(LocatorData.MUSIC_TRACK_PLAYING)).should(Condition.exist, Duration.ofMillis(1000));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPresent() {
        return true;
    }
}
