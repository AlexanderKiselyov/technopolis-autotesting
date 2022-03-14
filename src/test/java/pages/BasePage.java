package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public abstract class BasePage {
    protected WebDriver driver;

    public WebDriver driverInit() {
        System.setProperty("webdriver.chrome.driver", "D:\\distrib\\chrome\\chromedriver.exe");
        return new ChromeDriver();
    }

    public abstract Boolean isPresent();

    public void driverDown() {
        this.driver.quit();
    }
}
