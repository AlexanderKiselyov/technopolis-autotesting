package pages;

import java.util.Random;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;

import utils.LocatorData;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessagePage extends BasePage {

    private static ElementsCollection dialogs;

    public MessagePage() throws Exception {
        if (!isPresent()) {
            throw new Exception("ERROR MESSAGE PAGE");
        }
        dialogs = $$(byCssSelector(LocatorData.MESSAGE_DIALOGS));
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

    public boolean isPresent() {
        return true;
    }
}
