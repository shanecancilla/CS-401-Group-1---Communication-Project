import static org.junit.Assert.*;

import org.junit.Test;

public class ChannelTester
{
    @Test
    void test()
    {
        int id = 1;
        int userID = 1;
        String name = "channel";
        Message msg = new Message(id, userID, "Hello, world!");
        Channel channel = new Channel(id, name);
        channel.addMessage(msg);
        
        assertEquals(channel.getID(), 1);
        assert(channel.name().equals("channel"));
    }
}