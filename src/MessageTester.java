import static org.junit.Assert.*;

import org.junit.Test;

public class MessageTester {
    @Test
    public void testMsg()
    {
        String msgContent = "Hello world!";
        int id = 1;
        int userID = 1;

        Message msg = new Message(id, userID, msgContent);

        assert(msg.getID() == 1);
        assert(msg.getUserID() == 1);
        assert(msg.getStatus() == false);
        assert(msg.msgContent().equals("Hello world!"));
    }

    @Test
    public void testHiddenMsg()
    {
        Message msg = new Message(0, 0, null);

        assert(msg.getStatus() == false);
        msg.hideMessage();
        assert(msg.getStatus() == true);
    }
}
