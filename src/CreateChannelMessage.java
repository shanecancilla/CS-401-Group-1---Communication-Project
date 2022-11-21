
public class CreateChannelMessage implements NetworkMessage {
    NetworkMessageType type;
    String message;

    public void setNetworkMessageType()
    {
        this.type = NetworkMessageType.CreateChannel;
    }

    public NetworkMessageType getNetworkMessageType()
    {
        return this.type;
    }

    public void setMessage(String newString)
    {
        this.message = newString;
    }

    public String getMessage()
    {
        return message;
    }

    CreateChannelMessage(String msg)
    {
        this.message = msg;
        setNetworkMessageType();
    }
}
