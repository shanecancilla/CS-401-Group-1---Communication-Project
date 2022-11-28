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
        assertEquals(testUser.getID(), 1);
        assert(testUser.getUsername().equals("admin"));
        assert(testUser.getPassword().equals("admin"));
    }
}
