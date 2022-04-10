import pages.LoginPage;
import utils.User;
import utils.UserData;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.closeWindow;
import static java.util.Arrays.asList;

@RunWith(DataProviderRunner.class)
public class LoginTest {

    private static final String LOGIN_URL = "https://ok.ru/";
    static LoginPage loginPage;

    @BeforeClass
    public static void start() {
        loginPage = new LoginPage();
    }

    @Test
    @UseDataProvider("loadUsers")
    public void checkLogin(User user) {
        loginPage.login(user);
    }

    @After
    public void setDown() {
        open(LOGIN_URL);
        loginPage.logout();
    }

    @AfterClass
    public static void stop() {
        closeWindow();
        closeWebDriver();
    }

    @DataProvider
    public static List<User> loadUsers() {
        return asList(UserData.user1, UserData.user2);
    }
}
