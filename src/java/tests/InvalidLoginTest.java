import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pages.LoginPage;
import utils.User;

import com.codeborne.selenide.ex.ElementShouldNot;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.fail;

public class InvalidLoginTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(ValidLoginTest.class);
    static LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        loginPage = new LoginPage();
    }

    @ParameterizedTest
    @MethodSource("loadUsers")
    public void checkInvalidLogin(User user) {
        try {
            loginPage.login(user);
            fail();
        }
        catch (ElementShouldNot e) {
            logger.error("Invalid login or/and password.", e);
        }
        catch (AssertionError assertionError) {
            logger.error("Test with invalid userdata passed.", assertionError);
        }
    }

    private static List<User> loadUsers() {
        return asList(new User("", "", ""),
                new User("", "123456", "abcdef", "123456"),
                new User("123456789", "", "abcdef", "123456"),
                new User("123456789", "123456", "abcdef", "123456"));
    }
}
