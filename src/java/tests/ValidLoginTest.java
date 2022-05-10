import org.junit.jupiter.api.Test;

import pages.LoginPage;
import utils.UserData;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ValidLoginTest extends BaseTest {

    static LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    public void checkValidLogin() {
        loginPage.login(UserData.user1);
    }

    @AfterEach
    public void setDown() {
        loginPage.logout();
    }
}
