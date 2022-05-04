import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.LoginPage;
import utils.User;
import utils.UserData;

import com.codeborne.selenide.ex.ElementShouldNot;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static java.util.Arrays.asList;

public class LoginTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(LoginTest.class);
    static LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
    }

    @ParameterizedTest
    @MethodSource("loadUsers")
    public void checkLogin(User user) {
        try {
            loginPage.login(user);
        }
        catch (ElementShouldNot e) {
            logger.error("Invalid login or/and password.", e);
        }
    }

    @AfterEach
    public void setDown() {
        loginPage.logout();
    }

    private static List<User> loadUsers() {
        return asList(UserData.user1,
                        UserData.user2,
                        new User("", "", ""),
                        new User("123456789", "123456", "abcdef", "123456"));
    }
}
