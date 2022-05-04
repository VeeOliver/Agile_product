package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.SQLException;

import org.antlr.v4.runtime.Vocabulary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthenticationFeaturesIT {
    @Mock
    TextField registerPersonnummer;

    @Mock
    TextField registerName;

    @Mock
    TextField registerEmailField;

    @Mock
    PasswordField registerPasswordField;

    @Mock
    PasswordField registerRepPasswordField;

    private Authentication auth = new Authentication();
    
    @BeforeEach
    public void init() {
        DatabaseConnection.getInstance("127.0.0.1:5000");
        DatabaseConnection.getInstance().disconnect();
        User.resetInstance();
    }

    //Tests for features in Authentication.java
    @Test
    public void nonExistingUserForCheckLoginCredentialsReturnsFalse() throws SQLException {
        String email = "non-existing";
        String password = "non-existing";
        boolean res = auth.checkLoginCredentials(email, password);
        assertFalse(res);
    }

    @Test
    public void existingUserAndWrongPasswordForCheckLoginCredentialsReturnsFalse() throws SQLException {
        String email = "test1@myrmidon.com";
        String password = "non-existing";
        boolean res = auth.checkLoginCredentials(email, password);
        assertFalse(res);
    }

    @Test
    public void existingUserAndCorrectPasswordForCheckLoginCredentialsReturnsTrue() throws SQLException {
        String email = "test2@myrmidon.com";
        String password = "22222";
        boolean res = auth.checkLoginCredentials(email, password);
        assertTrue(res);
        assertEquals("Test User 2", User.getInstance().getName());
        assertEquals("test2@myrmidon.com", User.getInstance().getEmail());
        assertEquals("222222-2222", User.getInstance().getPersonnummer());
    }

    @Test
    public void existingEmailAndExistingPersonnummerForCheckAvailabilityReturnsFalseFalse() throws SQLException {
        String email = "test1@myrmidon.com";
        String personnummer = "111111-1111";
        Boolean[] res = auth.checkAvailability(personnummer, email);
        assertFalse(res[0]);
        assertFalse(res[1]);
    }

    @Test
    public void existingEmailAndNewPersonnummerForCheckAvailabilityReturnsFalseTrue() throws SQLException {
        String email = "test1@myrmidon.com";
        String personnummer = "123456-1234";
        Boolean[] res = auth.checkAvailability(personnummer, email);
        assertFalse(res[0]);
        assertTrue(res[1]);
    }

    @Test
    public void newEmailAndexistingPersonnummerForCheckAvailabilityReturnsTrueFalse() throws SQLException {
        String email = "testNew@myrmidon.com";
        String personnummer = "111111-1111";
        Boolean[] res = auth.checkAvailability(personnummer, email);
        assertTrue(res[0]);
        assertFalse(res[1]);
    }

    @Test
    public void newEmailAndNewPersonnummerForCheckAvailabilityReturnsTrueTrue() throws SQLException {
        String email = "testNew@myrmidon.com";
        String personnummer = "123456-1234";
        Boolean[] res = auth.checkAvailability(personnummer, email);
        assertTrue(res[0]);
        assertTrue(res[1]);
    }


    //Tests for features in RegisterController.java
    
    
}
