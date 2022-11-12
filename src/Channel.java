import java.util.List;
public class Channel {
    /**
     * This integer holds the ID of the channel
     */
    int id;

    String name;

    /**
     * This lists holds all the messages from this channel including the hidden messages
     */
    List<Message> messages;

    boolean isHidden;

    int getID() { return this.id; }
    String getName() { return this.name; }
    boolean getStatus() { return this.isHidden; }

    private void setID(int id) { this.id = id; }

    void setName(String name) { this.name = name; }

    void addMessage(Message msg)
    {
        messages.add(msg);
    }

    void hideChannel() { this.isHidden = true; }

    Channel(int id, String name)
    {
        setID(id);
        setName(name);
        this.isHidden = false;
    }
}