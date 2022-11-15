public class User {
    /**
     *  ID of the user
     */
    private int id;

    /**
     *  Username of the User which will be used for logging in
     */
    String username;
    /**
     *  Password of the user which will be used for logging in. It will not be encrypted nor salted.
     */
    String password;

    /**
     * Returns the ID of the user
     * 
     * @return User ID
     * 
     * @author Aftersol
     */
    int getID() { return this.id; }

    /**
     * Returns the username of the user
     * 
     * @return Username
     * 
     * @author Aftersol
     */
    String getUsername() { return this.username; }

    /**
     * Returns the password of the user
     * 
     * @return Password
     * 
     * @author Aftersol
     */
    String getPassword() { return this.password; }

    /**
     * Sets the ID of the user
     * 
     * @param id
     * 
     * @author Aftersol
     */
    private void setID(int id) { this.id = id; }

    /**
     * Sets the username for the user in plain text
     * 
     * @param username
     * 
     * @author Aftersol
     */
    void setUsername(String username) { this.username = username; }

    /**
     * Sets the password for the user in plain text
     * 
     * The password will not be encrypted nor hashed and salted
     * 
     * @param password
     * 
     * @author Aftersol
     */
    void setPassword(String password) { this.password = password; }

    /**
     * @param id
     * @param username
     * @param password
     * 
     * @author Aftersol
     */
    User(int id, String username, String password)
    {
        setID(id);
        setUsername(username);
        setPassword(password);
    }

}