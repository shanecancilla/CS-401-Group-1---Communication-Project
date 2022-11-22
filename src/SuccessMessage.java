public class SuccessMessage implements NetworkMessage{
    NetworkMessageType type;
    String message;
    
    public void setNetworkMessageType()
    {
        this.type = NetworkMessageType.Success;
    }

    public void setNetworkMessageType(NetworkMessageType type)
    {
        this.type = type;
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
        return this.message;
    }

    SuccessMessage()
    {
        setNetworkMessageType();
        setMessage("success");
    }

    SuccessMessage(String str)
    {
        setNetworkMessageType();
        setMessage(str);
    }

    SuccessMessage(String str, NetworkMessageType)
    {
        setNetworkMessageType();
        setMessage(str);
    }
}
