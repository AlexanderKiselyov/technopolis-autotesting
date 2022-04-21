package pages;

import utils.Toolbar;
import utils.User;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    private final Toolbar toolbar;
    private static final By USER_NAME_LOCATOR = byXpath(".//a[@data-l='t,userPage']");
    private static final By MAIN_PHOTO = byXpath(".//*[@class='entity-avatar']");

    public MainPage() {
        toolbar = new Toolbar();
    }

    @Override
    protected void load() {
        $(USER_NAME_LOCATOR)
                .shouldBe(visible.because("No user name found!"));
    }

    @Override
    protected void isLoaded() throws Error {
        $(MAIN_PHOTO)
                .shouldBe(visible.because("Main Page has not been loaded: no main photo found!"));
    }

    public MainPage(User user) {
        checkIfUserNameCorrect(user.getUsername());
        toolbar = new Toolbar();
    }

    public MusicPage goToMusic() {
        toolbar
                .getMusicPage()
                .shouldBe(visible.because("No music link found!"))
                .click();
        return new MusicPage();
    }

    public MessagePage goToMessage() {
        toolbar
                .getMessagePage()
                .shouldBe(visible.because("No message link found!"))
                .click();
        return new MessagePage();
    }

    private void checkIfUserNameCorrect(String userName) {
        $(USER_NAME_LOCATOR)
                .$(byXpath(".//*[contains(text(), '" + userName + "')]"))
                .shouldBe(visible.because("Main Page has not been loaded: no user name found!"));
    }
}
