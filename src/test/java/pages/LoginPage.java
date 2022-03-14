package pages;

import org.openqa.selenium.WebElement;

import utils.LocatorData;
import utils.User;

public class LoginPage extends BasePage {
    public LoginPage() throws Exception {
        driver.get(LocatorData.LOGIN_URL);
        if (!isPresent()) {
            throw new Exception("ERROR LOGIN PAGE");
        }
    }

    private WebElement getLogin() {

    }

    private WebElement getPassword() {

    }

    private WebElement getEnter() {

    }

    public MainPage login(User user) {

    }

    public Boolean isPresent() {
        return null;
    }
}
