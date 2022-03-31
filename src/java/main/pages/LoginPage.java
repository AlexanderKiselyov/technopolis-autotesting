package pages;

import utils.User;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    private final String LOGIN_URL = "https://ok.ru/";
    private final String LOGIN_FIELD = ".//input[@id='field_email']";
    private final String LOGIN_PASSWORD_FIELD = ".//input[@id='field_password']";
    private final String LOGIN_ENTER_FIELD = ".//input[@data-l='t,sign_in']";
    private final String LOGIN_REGISTER_BUTTON = ".//*[contains(@class,'external-oauth-login')]//a[text()='Зарегистрироваться']";

    public LoginPage() {
        open(LOGIN_URL);
        checkIfPresent();
    }

    private SelenideElement getLogin() {
        return $(byXpath(LOGIN_FIELD));
    }

    private SelenideElement getPassword() {
        return $(byXpath(LOGIN_PASSWORD_FIELD));
    }

    private SelenideElement getEnter() {
        return $(byXpath(LOGIN_ENTER_FIELD));
    }

    public MainPage login(User user) {
        getLogin().setValue(user.getLogin());
        getPassword().setValue(user.getPassword());
        getEnter()
                .shouldBe(Condition.visible.because("No enter button found!"))
                .click();
        return new MainPage(user);
    }

    @Override
    void checkIfPresent() {
        $(byXpath(LOGIN_ENTER_FIELD))
                .shouldBe(Condition.visible.because("Login Page has not been loaded: enter login field not found!"));
        $(byXpath(LOGIN_REGISTER_BUTTON))
                .shouldBe(Condition.visible.because("Login Page has not been loaded: login register button not found!"));
    }
}
