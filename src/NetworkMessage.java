/**
 *  This interface is used for sending a variety of messages to the server and the client
 * 
 */
public interface NetworkMessage {

    void setNetworkMessageType();

    /**
     *  This function is very useful for the server client handler
     * 
     *  @return Network Message Type
     */
    NetworkMessageType getNetworkMessageType();

    void setMessage(String newString);
    String getMessage();
}
