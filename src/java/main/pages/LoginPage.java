package pages;

import utils.User;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {

    private static final By LOGIN_FIELD = byXpath(".//input[@id='field_email']");
    private static final By LOGIN_PASSWORD_FIELD = byXpath(".//input[@id='field_password']");
    private static final By LOGIN_ENTER_FIELD = byXpath(".//input[@data-l='t,sign_in']");
    private static final By LOGIN_REGISTER_BUTTON = byXpath(".//*[contains(@class,'external-oauth-login')]//a[text()='Зарегистрироваться']");
    private static final By LOGIN_ERROR = byXpath(".//*[contains(@class, 'login_error')]");

    public LoginPage() {
    }

    @Override
    protected void isLoaded() throws Error {
        $(LOGIN_ENTER_FIELD)
                .shouldBe(visible.because("Login Page has not been loaded: enter login field not found!"));
        $(LOGIN_REGISTER_BUTTON)
                .shouldBe(visible.because("Login Page has not been loaded: login register button not found!"));
    }

    public MainPage login(User user) {
        $(LOGIN_FIELD).setValue(user.getLogin());
        $(LOGIN_PASSWORD_FIELD).setValue(user.getPassword());
        $(LOGIN_ENTER_FIELD)
                .shouldBe(visible.because("No enter button found!"))
                .click();
        $(LOGIN_ERROR).shouldNotBe(visible.because("Incorrect login data."));
        return new MainPage(user);
    }
}
