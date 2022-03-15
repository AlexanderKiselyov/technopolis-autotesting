package main.pages;

import main.utils.Toolbar;

public class MainPage extends BasePage {

    Toolbar toolbar;

    public MainPage() throws Exception {
        if (!isPresent()) {
            throw new Exception("ERROR MAIN PAGE");
        }
        toolbar = new Toolbar();
    }

    public MusicPage goToMusic() throws Exception {
        toolbar.getMusicElement().click();
        return new MusicPage();
    }

    public MessagePage goToMessage() throws Exception {
        toolbar.getMessagePage().click();
        return new MessagePage();
    }

    @Override
    public boolean isPresent() {
        return true;
    }
}
