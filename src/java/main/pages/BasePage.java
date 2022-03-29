package pages;

import utils.ToolbarRight;

public abstract class BasePage {
    private final ToolbarRight toolbarRight = new ToolbarRight();

    abstract void checkIfPresent();

    public void logout() {//url в mainPage
        toolbarRight.exitWithCheck();
    }
}
