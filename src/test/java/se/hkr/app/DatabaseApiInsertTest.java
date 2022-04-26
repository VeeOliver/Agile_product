package se.hkr.app;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseApiInsertTest {
    @Mock
    Connection con;

    @Mock
    PreparedStatement stmt;

    @Test
    @DisplayName("Test if createUserEntry returns 1 when successfully executing all steps.")
    void testCreateUserEntrySuccessfulReturns1() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        var res = DatabaseApiInsert.createUserEntry(con, "personnummer", "name",
                "email", "password");
        Mockito.verify(con).prepareStatement(any(String.class));
        Mockito.verify(stmt).executeUpdate();
        assertInstanceOf(Short.class, res);
        assertEquals(1, res);
    }

    @Test
    @DisplayName("Test if createUserEntry returns 0 when exception is thrown.")
    void testCreateUserEntryExceptionReturns0() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        var res = DatabaseApiInsert.createUserEntry(con, "personnummer", "name",
                "email", "password");
        assertInstanceOf(Short.class, res);
        assertEquals(0, res);
    }

    @Test
    @DisplayName("Test if createMoodEntry returns 1 when successfully executing all steps.")
    void testCreateMoodEntrySuccessfulReturns1() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        var res = DatabaseApiInsert.createMoodEntry(con, 1, LocalDateTime.now(),
                "personnummer");
        Mockito.verify(con).prepareStatement(any(String.class));
        Mockito.verify(stmt).executeUpdate();
        assertInstanceOf(Short.class, res);
        assertEquals(1, res);
    }

    @Test
    @DisplayName("Test if createMoodEntry returns 0 when exception is thrown.")
    void testCreateMoodEntryExceptionReturns0() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        var res = DatabaseApiInsert.createMoodEntry(con, 1, LocalDateTime.now(),
                "personnummer");
        assertInstanceOf(Short.class, res);
        assertEquals(0, res);
    }

    @Test
    @DisplayName("Test if createTensionEntry returns 1 when successfully executing all steps.")
    void testCreateTensionEntrySuccessfulReturns1() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        var res = DatabaseApiInsert.createTensionEntry(con, 1, LocalDateTime.now(),
                "personnummer");
        Mockito.verify(con).prepareStatement(any(String.class));
        Mockito.verify(stmt).executeUpdate();
        assertInstanceOf(Short.class, res);
        assertEquals(1, res);
    }

    @Test
    @DisplayName("Test if createTensionEntry returns 0 when exception is thrown.")
    void testCreateTensionEntryExceptionReturns0() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        var res = DatabaseApiInsert.createTensionEntry(con, 1, LocalDateTime.now(),
                "personnummer");
        assertInstanceOf(Short.class, res);
        assertEquals(0, res);
    }

    @Test
    @DisplayName("Test if createJournalEntry returns 1 when successfully executing all steps.")
    void testCreateJournalEntrySuccessfulReturns1() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        var res = DatabaseApiInsert.createJournalEntry(con, "Journal entry", LocalDateTime.now(),
                "personnummer");
        Mockito.verify(con).prepareStatement(any(String.class));
        Mockito.verify(stmt).executeUpdate();
        assertInstanceOf(Short.class, res);
        assertEquals(1, res);
    }

    @Test
    @DisplayName("Test if createJournalEntry returns 0 when exception is thrown.")
    void testCreateJournalEntryExceptionReturns0() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenThrow(SQLException.class);
        var res = DatabaseApiInsert.createJournalEntry(con, "Journal entry", LocalDateTime.now(),
                "personnummer");
        assertInstanceOf(Short.class, res);
        assertEquals(0, res);
    }
}
