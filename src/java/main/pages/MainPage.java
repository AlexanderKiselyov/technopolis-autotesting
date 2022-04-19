package pages;

import utils.Toolbar;
import utils.User;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage {

    private final Toolbar toolbar;
    private static final String USER_NAME_LOCATOR = ".//a[@data-l='t,userPage']";
    private final String MAIN_PHOTO = ".//*[@class='entity-avatar']";

    public MainPage() {
        checkIfPresent();
        toolbar = new Toolbar();
    }

    public MainPage(User user) {
        checkIfUserNameCorrect(user.getUsername());
        checkIfPresent();
        toolbar = new Toolbar();
    }

    public MusicPage goToMusic() {
        toolbar
                .getMusicPage()
                .shouldBe(Condition.visible.because("No music link found!"))
                .click();
        return new MusicPage();
    }

    public MessagePage goToMessage() {
        toolbar
                .getMessagePage()
                .shouldBe(Condition.visible.because("No message link found!"))
                .click();
        return new MessagePage();
    }

    private void checkIfUserNameCorrect(String userName) {
        $(byXpath(USER_NAME_LOCATOR))
                .$(byXpath(".//*[contains(text(), '" + userName + "')]"))
                .shouldBe(Condition.visible.because("Main Page has not been loaded: no user name found!"));
    }

    @Override
    void checkIfPresent() {
        $(byXpath(MAIN_PHOTO))
                .shouldBe(Condition.visible.because("Main Page has not been loaded: no main photo found!"));
    }
}
