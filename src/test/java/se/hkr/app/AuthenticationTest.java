package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import se.hkr.app.Authentication;

public class AuthenticationTest {
    Authentication auth = new Authentication();

    @Test
    @DisplayName("Test the checkLoginCredential method. With incorrect input")
    public void testIncorrectCheckLoginCredentials() throws SQLException {
        String email = "incorectemail@gmail.com";
        String password = "incorrectPassword";
        Boolean res = auth.checkLoginCredentials(email, password);
        assertTrue(!res);
    }

    @Test
    @DisplayName("Test the checkLoginCredential method. With correct input")
    public void testCheckLoginCredentials() throws SQLException {
        String email = "enzotiberghien28@gmail.com";
        String password = "alabama";
        Boolean res = auth.checkLoginCredentials(email, password);
        assertTrue(res);
    }

    @Test
    @DisplayName("Test the logError method")
    public void testLogError() throws SQLException {
        
    }


}
