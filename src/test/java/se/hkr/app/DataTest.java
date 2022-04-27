package se.hkr.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Mock
    Connection con;

    @Test
    @DisplayName("Testing to see if the mood rating is sent to the Database")
    void insertMoodWorks() {
        int testMoodNum = 2;
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)){
            ms.when (() -> DatabaseApiInsert.createMoodEntry(con, testMoodNum, LocalDateTime.now(), "test"))
                    .thenReturn(1);
            Data.insertMood(testMoodNum, testUser);
            assertNull(DatabaseConnection.getInstance().getCon());
        }

    }

    @Test
    void insertTensionWorks() {
        int testTensionNum = 2;
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)){
            ms.when (() -> DatabaseApiInsert.createTensionEntry((con), testTensionNum, LocalDateTime.now(), "test"))
                    .thenReturn(1);
            Data.insertTension(testTensionNum, testUser);
            assertNull(DatabaseConnection.getInstance().getCon());
        }
    }
    @Test
    void insertJournalWorks() {
        String testEntry = "test";
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)){
            ms.when (() -> DatabaseApiInsert.createJournalEntry((con), testEntry, LocalDateTime.now(), "test"))
                    .thenReturn(1);
            Data.insertJournal(testEntry, testUser);
            assertNull(DatabaseConnection.getInstance().getCon());
        }
    }

    @Test
    void submissionCompleteNoteWorks() {
    }

    @Test
    void journalSubmittedNoteWorks() {
    }

    @Test
    void clearOutJournalEntryWorks() {
    }
}