package se.hkr.app;

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
    @DisplayName("Test if createUserEntry successfully executes all steps.")
    void testCreateUserEntrySuccessfulReturns1() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        DatabaseApiInsert.createUserEntry(con, "personnummer", "name", "email", "password");
        Mockito.verify(con).prepareStatement(any(String.class));
        Mockito.verify(stmt).executeUpdate();
    }

    @Test
    @DisplayName("Test if createMoodEntry successfully executes all steps.")
    void testCreateMoodEntrySuccessfulReturns1() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        DatabaseApiInsert.createMoodEntry(con, 1, LocalDateTime.now(), "personnummer");
        Mockito.verify(con).prepareStatement(any(String.class));
        Mockito.verify(stmt).executeUpdate();
    }

    @Test
    @DisplayName("Test if createTensionEntry successfully executes all steps.")
    void testCreateTensionEntrySuccessfulReturns1() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        DatabaseApiInsert.createTensionEntry(con, 1, LocalDateTime.now(), "personnummer");
        Mockito.verify(con).prepareStatement(any(String.class));
        Mockito.verify(stmt).executeUpdate();
    }

    @Test
    @DisplayName("Test if createJournalEntry returns 1 when successfully executing all steps.")
    void testCreateJournalEntrySuccessfulReturns1() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        DatabaseApiInsert.createJournalEntry(con, "Journal entry", LocalDateTime.now(), "personnummer");
        Mockito.verify(con).prepareStatement(any(String.class));
        Mockito.verify(stmt).executeUpdate();
    }
}
