package pages;

import utils.LocatorData;
import utils.User;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    public LoginPage() {
        open(LocatorData.LOGIN_URL);
        checkIfPresent();
    }

    private SelenideElement getLogin() {
        return $(byXpath(LocatorData.LOGIN_FIELD));
    }

    private SelenideElement getPassword() {
        return $(byXpath(LocatorData.LOGIN_PASSWORD_FIELD));
    }

    private SelenideElement getEnter() {
        return $(byXpath(LocatorData.LOGIN_ENTER_FIELD));
    }

    public MainPage login(User user) {
        getLogin().setValue(user.getLogin());
        getPassword().setValue(user.getPassword());
        getEnter().shouldBe(Condition.visible.because("No enter button found!")).click();
        return new MainPage(user);
    }

    @Override
    void checkIfPresent() {
        $(byXpath(LocatorData.LOGIN_ENTER_FIELD)).shouldBe(Condition.visible.because("Login Page has not been loaded: enter login field not found!"));
        $(byXpath(LocatorData.LOGIN_REGISTER_BUTTON)).shouldBe(Condition.visible.because("Login Page has not been loaded: login register button not found!"));
    }
}
