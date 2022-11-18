public class PrivateMessage extends Channel {
    int senderID;
    int recepientID;

    void addMessage(Message message)
    {
        super.addMessage(message);
    }

    PrivateMessage(int id, String name)
    {
        super(id, name);
    }
}
