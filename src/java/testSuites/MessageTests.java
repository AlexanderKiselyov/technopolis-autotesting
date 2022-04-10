import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ MessageDeleteTest.class, MessageReplyTest.class, MessageSendTest.class })
public class MessageTests {
}
