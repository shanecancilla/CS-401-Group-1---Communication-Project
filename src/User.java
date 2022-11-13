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
     * @return User ID
     */
    int getID() { return this.id; }

    /**
     * @return Username
     */
    String getUsername() { return this.username; }

    /**
     * @return Password
     */
    String getPassword() { return this.password; }

    /**
     * @param id
     */
    private void setID(int id) { this.id = id; }

    /**
     * @param username
     */
    void setUsername(String username) { this.username = username; }

    /**
     * @param password
     */
    void setPassword(String password) { this.password = password; }

    /**
     * @param id
     * @param username
     * @param password
     */
    User(int id, String username, String password)
    {
        setID(id);
        setUsername(username);
        setPassword(password);
    }

}