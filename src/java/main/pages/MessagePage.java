package pages;

import utils.LocatorData;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import java.util.Random;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage extends BasePage {

    private static final ElementsCollection dialogs = $$(byCssSelector(LocatorData.MESSAGE_DIALOGS));

    public MessagePage() {
        checkIfPresent();
        dialogs.shouldHave(CollectionCondition.sizeGreaterThan(1));
    }

    public void sendMessage(String message) {
        Random r = new Random();
        int dialogNum = r.nextInt(dialogs.size());
        dialogs.get(dialogNum).click();
        $(byXpath(LocatorData.MESSAGE_INPUT_FIELD)).setValue(message).pressEnter();
    }

    // TODO
    public void checkIfMessageSent() {

    }

    public void deleteMessage(String message) {
        dialogs.get(0).click();
        $(byCssSelector(LocatorData.MESSAGE_LAST_SEND_MESSAGE)).hover();
        $(byXpath(LocatorData.MESSAGE_SETTINGS)).should(Condition.visible).hover();
        $(byXpath(LocatorData.MESSAGE_DELETE)).click();
    }

    // TODO
    public void checkIfMessageDeleted() {

    }

    @Override
    void checkIfPresent() {
        $(byXpath(LocatorData.MESSAGE_NEW_DIALOG_BUTTON)).shouldBe(Condition.visible);
    }
}
