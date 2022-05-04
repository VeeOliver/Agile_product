package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.SQLException;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

public class AuthenticationTest {
    Authentication auth = new Authentication();

    @BeforeEach
    public void init() {
        User.resetUserInstance();
    }

    @Test
    @DisplayName("Test the checkLoginCredential method. With incorrect input")
    public void testIncorrectCheckLoginCredentials() throws SQLException {
        String email = "incorectemail@gmail.com";
        String password = "incorrectPassword";
        Boolean res = auth.checkLoginCredentials(email, password);
        assertTrue(!res);
        assertEquals(null, User.getInstance());
    }

    @Test
    @DisplayName("Test the checkLoginCredential method. With correct input")
    public void testCheckLoginCredentials() throws SQLException {
        String email = "enzotiberghien28@gmail.com";
        String password = "alabama";
        Boolean res = auth.checkLoginCredentials(email, password);
        assertTrue(res);
        assertNotNull(User.getInstance());
    }

    @Test
    @DisplayName("Test the checkAvailability method")
    public void testCheckAvailability() throws SQLException {
        Boolean[] req = auth.checkAvailability("280201-4999", "enzotiberghien28@gmail.com");
        Boolean res = Arrays.asList(req).contains(false);
        assertTrue(res);
        assertNotNull(DatabaseConnection.getInstance());
    }


}
