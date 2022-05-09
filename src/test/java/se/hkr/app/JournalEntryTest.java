package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import se.hkr.app.DatabaseApiSelect.RetrieveMode;

@ExtendWith(MockitoExtension.class)
public class JournalEntryTest {
    @Mock
    DatabaseConnection dbCon;

    @Mock
    Connection con;

    @Test
    @DisplayName("Test instantiation and getters of JournalEntry object.")
    public void testJournalEntryInstantiationAndGetters() {
        LocalDate date = LocalDate.of(2022, 01, 01);
        String daytime = "Morning";
        String entry = "This is a journal entry";
        JournalEntry testJournalEntry = new JournalEntry(date, daytime, entry);
        assertInstanceOf(JournalEntry.class, testJournalEntry);
        assertEquals(date, testJournalEntry.getDate());
        assertEquals(daytime, testJournalEntry.getDaytime());
        assertEquals(entry, testJournalEntry.getEntry());
    }

    @Test
    @DisplayName("Test if retrieveJournalEntry returns correct String for one entry.")
    public void whenOneEntryForRetrieveJournalEntrythenReturnCorrectString() throws SQLException {
        String exp = "First line\n";
        try (MockedStatic<DatabaseApiSelect> ms = Mockito.mockStatic(DatabaseApiSelect.class)) {
            ArrayList<Data> returnList = new ArrayList<>();
            returnList.add(new JournalEntry(LocalDate.now(), "Morning", "First line"));
            ms.when(() -> DatabaseApiSelect.getData(con, RetrieveMode.JOURNAL_ENTRY,
                    LocalDate.now(), "test"))
                .thenReturn(returnList);
            var res = JournalEntry.retrieveJournalEntry(con, LocalDate.now(), "test");
            assertEquals(exp, res);
        }
    }
    

    @Test
    @DisplayName("Test if retrieveJournalEntry returns correct String for several entries.")
    public void whenSeveralEntriesForRetrieveJournalEntrythenReturnCorrectString() throws SQLException {
        String exp = "First line\nSecond line\nThird line\n";
        try (MockedStatic<DatabaseApiSelect> ms = Mockito.mockStatic(DatabaseApiSelect.class)) {
            ArrayList<Data> returnList = new ArrayList<>();
            returnList.add(new JournalEntry(LocalDate.now(), "Morning", "First line"));
            returnList.add(new JournalEntry(LocalDate.now(), "Morning", "Second line"));
            returnList.add(new JournalEntry(LocalDate.now(), "Morning", "Third line"));
            ms.when(() -> DatabaseApiSelect.getData(con, RetrieveMode.JOURNAL_ENTRY,
                    LocalDate.now(), "test"))
                .thenReturn(returnList);
            var res = JournalEntry.retrieveJournalEntry(con, LocalDate.now(), "test");
            assertEquals(exp, res);
        }
    }

    @Test
    @DisplayName("Test if retrieveJournalEntry returns correct String for no entry.")
    public void whenNoEntriesForRetrieveJournalEntrythenReturnCorrectString() throws SQLException {
        String exp = "No journal entries on this day";
        try (MockedStatic<DatabaseApiSelect> ms = Mockito.mockStatic(DatabaseApiSelect.class)) {
            ArrayList<Data> returnList = new ArrayList<>();
            ms.when(() -> DatabaseApiSelect.getData(con, RetrieveMode.JOURNAL_ENTRY,
                    LocalDate.now(), "test"))
                .thenReturn(returnList);
            var res = JournalEntry.retrieveJournalEntry(con, LocalDate.now(), "test");
            assertEquals(exp, res);
        }
    }
}
