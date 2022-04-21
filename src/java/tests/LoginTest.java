import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.User;
import utils.UserData;

import com.codeborne.selenide.ex.ElementShouldNot;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static java.util.Arrays.asList;

public class LoginTest extends BaseTest {

    private final Logger logger = LoggerFactory.getLogger(LoginTest.class);

    @ParameterizedTest
    @MethodSource("loadUsers")
    public void checkLogin(User user) {
        loginPage.login(user);
    }

    @ParameterizedTest
    @MethodSource("loadIncorrectUsers")
    public void checkIncorrectLogin(User user) {
        try {
            loginPage.login(user);
        }
        catch (ElementShouldNot e) {
            logger.error("Invalid login or/and password.", e);
        }
    }

    private static List<User> loadUsers() {
        return asList(UserData.user1, UserData.user2);
    }
    
    private static List<User> loadIncorrectUsers() {
        return asList(new User("", "", ""), new User("123456789", "123456", "abcdef", "123456"));
    }
}
