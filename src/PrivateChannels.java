public class PrivateChannels extends Channel {
    int senderID;
    int recepientID;

    void addMessage(Message message)
    {
        super.addMessage(message);
    }

    PrivateChannels(int id, String name)
    {
        super(id, name);
    }
}
