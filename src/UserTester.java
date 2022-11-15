import static org.junit.Assert.*;

import org.junit.Test;

public class UserTester {
    @Test
    void test()
    {
        int id = 1;
        String name = "admin";
        String passwordString = "admin";

        User testUser = new User(id,name,passwordString);
        Assert(testUser.getID() = 1);
        Assert(testUser.getUsername().equals("admin"));
        Assert(testUser.getPassword().equals("admin"));
    }
}
