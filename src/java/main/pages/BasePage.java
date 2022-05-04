package pages;

import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage extends LoadableComponent<BasePage> {
    protected BasePage() {
        super.get();
    }
}
