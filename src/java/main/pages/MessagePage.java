package pages;

import java.time.Duration;
import java.util.Random;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;

import utils.LocatorData;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage extends BasePage {

    private static final ElementsCollection dialogs = $$(byCssSelector(LocatorData.MESSAGE_DIALOGS));

    public MessagePage() throws Exception {
        if (!isPresent()) {
            throw new Exception("ERROR MESSAGE PAGE");
        }
    }

    public boolean sendMessage(String message) {
        Random r = new Random();
        int dialogNum = r.nextInt(dialogs.shouldHave(CollectionCondition.sizeGreaterThan(1)).size());
        dialogs.get(dialogNum).click();
        try {
            $(byXpath(LocatorData.MESSAGE_INPUT_FIELD)).setValue(message).pressEnter();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteMessage(String message) {
        dialogs.get(0).click();
        try {
            $(byCssSelector(LocatorData.MESSAGE_LAST_SEND_MESSAGE)).hover();
            $(byXpath(LocatorData.MESSAGE_SETTINGS)).should(Condition.visible, Duration.ofMillis(2000)).hover();
            $(byXpath(LocatorData.MESSAGE_DELETE)).click();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isPresent() {
        return true;
    }
}
