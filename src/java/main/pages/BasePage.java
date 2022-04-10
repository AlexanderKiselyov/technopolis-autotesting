package pages;

import utils.ToolbarRight;

public abstract class BasePage {

    private final ToolbarRight toolbarRight = new ToolbarRight();

    abstract void checkIfPresent();

    public void logout() {
        toolbarRight.exitWithCheck();
    }
}
