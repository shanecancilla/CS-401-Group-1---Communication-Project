public class MessageTester {
    @Test
    public void testMsg()
    {
        String msgContent = "Hello world!";
        int id = 1;
        int userID = 1;

        Message msg = new Message(id, userID, msgContent);

        Assert(msg.getID() == 1);
        Assert(msg.getUserID() == 1);
        Assert(msg.getStatus() == false);
        Assert(msg.msgContent().equals("Hello world!"));
    }

    @Test
    public void testHiddenMsg()
    {
        Message msg = new Message(0, 0, null);

        Assert(msg.getStatus() == false);
        msg.hideMessage();
        Assert(msg.getStatus() == true);
    }
}
