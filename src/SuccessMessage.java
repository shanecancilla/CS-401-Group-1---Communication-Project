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
    }

    SuccessMessage(String str)
    {
        setNetworkMessageType();
        setMessage(str);
    }

    SuccessMessage(NetworkMessageType type) 
    {
        setNetworkMessageType(type);
    }

    SuccessMessage(String str, NetworkMessageType type) 
    {
        setNetworkMessageType(type);
        setMessage(str);
    }
}
