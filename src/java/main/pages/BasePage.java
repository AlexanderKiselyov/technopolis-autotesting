package pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.ToolbarRight;

import com.codeborne.selenide.ex.ElementNotFound;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage extends LoadableComponent<BasePage> {

    private final ToolbarRight toolbarRight = new ToolbarRight();
    private final Logger logger = LoggerFactory.getLogger(BasePage.class);

    protected BasePage() {
        super.get();
    }

    public void logout() {
        try {
            toolbarRight.exitWithCheck();
        } catch (ElementNotFound e) {
            logger.error("Cannot logout." , e);
        }
    }
}
