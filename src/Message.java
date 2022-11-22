public class Message {
    /**
     * ID of the message
     */
    int id;
    /**
     * ID of who posted the message
     * @see User
     */
    int userID;
    
    /**
     * Is Message hidden?
     * @see hideMessage
     */
    boolean isHidden;
    /**
     * Text display to users
     * @see getMessageContent
     * @see hideMessage
     * @see to_string
     */
    String msgContent;

    /**
     * This function returns the ID of the message
     * @return ID
     * 
     * @author Aftersol
     */
    int getID() { return this.id; }
    
    /**
     * This function returns the ID of the user
     * @return userID ID of the user
     * @see User
     * 
     * @author Aftersol
     */
    int getUserID() { return this.userID; }

    /**
     * Check if the message is hidden from regular users
     * @see hideMessage
     * @return isHidden
     * 
     * @author Aftersol
     */
    boolean isHidden() { return this.isHidden; }

    /**
     * This function returns the string content of the message class
     * @return messages
     * @see hideMessage
     * 
     * @author Aftersol
     */
    String getMessageContent() { return this.msgContent; }

    /**
     * Sets the ID of the ,essage
     * @param id
     * 
     * @author Aftersol
     */
    private void setID(int id) {
        this.id = id;
    }

    /**
     * Sets the ID of who sent the message
     * @param userID ID of the user
     * @see User
     * 
     * @author Aftersol
     */
    private void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Hides the message from regular users, but not the IT user
     * @see Message
     * 
     * @author Aftersol
     */
    void hideMessage()
    {
        this.isHidden = true;
    }

    /**
     * Sets the message to be displayed to users 
     * @param msgContent
     * @see Message
     * @see hideMessage
     * 
     * @author Aftersol
     */
    void setMessageContent(String msgContent) {
        this.msgContent = msgContent;
    }

    /**
     * This function will convert the Message class into a string
     * @return Message Content
     * 
     */
    String to_string()
    {
        return this.msgContent;
    }

    /**
     * Create a message based on the message content
     * 
     * @param   ID message ID
     * @param   userID user ID
     * 
     * @see msgContent
     * 
     * @author Aftersol
     */
    Message(int id, int userID, String msgContent)
    {
        setID(id);
        setUserID(userID);
        this.isHidden = false;
        this.msgContent = msgContent;
    }
}