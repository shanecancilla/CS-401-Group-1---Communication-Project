public class Message {
    int id;
    int userID;
    
    boolean isHidden;
    String msgContent;

    /**
     * This function returns the ID of the user
     * @return user ID
     */
    int getID() { return this.id; }
    
    /**
     * This function returns the ID of the user
     * @return user ID
     */
    int getUserID() { return this.userID; }

    boolean getStatus() { return this.isHidden; }

    /**
     * This function returns the string content of the message class
     * @return messages
     */
    String getMessageContent() { return this.msgContent; }

    private void setID(int id) {
        this.id = id;
    }

    private void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Hides the message from regular users but not the IT user
     */
    void hideMessage()
    {
        this.isHidden = true;
    }

    void setMessageContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * 
     * @return
     * 
     */
    String to_string()
    {
        return null;
    }

    /**
     * Create a message based on the message content
     * 
     * @param   ID message ID
     * @param   userID user ID
     * 
     */
    Message(int id, int userID, String msgContent)
    {
        setID(id);
        setUserID(userID);
        this.isHidden = false;
        this.msgContent = msgContent;
    }
}