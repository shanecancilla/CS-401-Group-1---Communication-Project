import java.util.List;
public class Channel {
    int id;

    String name;
    List<Message> messages;

    boolean isHidden;

    int getID() { return this.id; }
    String getName() { return this.name; }
    boolean getStatus() { return isHidden; }

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