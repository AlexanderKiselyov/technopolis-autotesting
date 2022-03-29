package pages;

import utils.Toolbar;
import utils.User;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    private final Toolbar toolbar;
    private final String userNameLocator;

    public MainPage() {
        userNameLocator = ".//a[@data-l='t,userPage']";
        checkIfPresent();
        toolbar = new Toolbar();
    }

    public MainPage(User user) {
        userNameLocator = ".//a[@data-l='t,userPage']//*[contains(text(), '" + user.getUsername() + "')]";
        checkIfPresent();
        toolbar = new Toolbar();
    }

    public MusicPage goToMusic() {
        toolbar.getMusicElement().click();
        return new MusicPage();
    }

    public MessagePage goToMessage() {
        toolbar.getMessagePage().click();
        return new MessagePage();
    }

    @Override
    void checkIfPresent() {
        $(byXpath(userNameLocator)).shouldBe(Condition.visible);
    }
}
