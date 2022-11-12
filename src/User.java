public class User {
    private int id;

    String username;
    String password;

    int getID() { return this.id; }
    String getUsername() { return this.username; }
    String getPassword() { return this.password; }

    private void setID(int id) { this.id = id; }
    void setUsername(String username) { this.username = username; }
    void setPassword(String password) { this.password = password; }

    User(int id, String username, String password)
    {
        setID(id);
        setUsername(username);
        setPassword(password);
    }

}