package pages;

import utils.ToolbarRight;

public abstract class BasePage {
    private final ToolbarRight toolbarRight = new ToolbarRight();

    public abstract boolean isPresent();

    public void logout() {//url в mainPage
        toolbarRight.exitWithCheck();
    }
}
