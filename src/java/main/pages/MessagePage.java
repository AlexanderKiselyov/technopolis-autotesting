package pages;

public class MessagePage extends BasePage {

    public MessagePage() throws Exception {
        if (!isPresent()) {
            throw new Exception("ERROR MESSAGE PAGE");
        }
    }

    public boolean isPresent() {
        return true;
    }
}
