package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AuthenticationIT {
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

    @Test
    public void validInputRegisterUserCreatesUserInDB() throws SQLException {
        //Prepare test arguments and call the method
        String personnummer = "999999-9999";
        String name = "Test register";
        String email = "testRegister@myrmidon.com";
        String password = "11111";
        String passwordHashed = "7b21848ac9af35be0ddb2d6b9fc3851934db8420";
        auth.registerUser(personnummer,name, email, password);

        //Check if insert was done correctly
        String personnummerInserted = "";
        String nameInserted = "";
        String emailInserted = "";
        String passwordInserted = ""; 
        String sql = """
                SELECT *
                FROM User
                ORDER BY personnummer DESC
                LIMIT 1;
                """;
        PreparedStatement stmt = DatabaseConnection.getInstance().connect().prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            personnummerInserted = rs.getString(1);
            nameInserted = rs.getString(2);
            emailInserted = rs.getString(3);
            passwordInserted = rs.getString(4);
        }
        DatabaseConnection.getInstance().disconnect();
        assertEquals(null, DatabaseConnection.getInstance().getCon());
        assertEquals(personnummer, personnummerInserted);
        assertEquals(name, nameInserted);
        assertEquals(email, emailInserted);
        assertEquals(passwordHashed, passwordInserted);
    }
}
