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
        
        Assert(channel.getID() == 1);
        Assert(channel.userID() == 1);
        Assert(channel.name().equals("channel"));
    }
}