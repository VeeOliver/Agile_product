package se.hkr.app;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Labeled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;

import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DataTest {

    @Mock
    Connection con;

    @Mock
    Alert alert;

    @Test
    @DisplayName("Testing to see if the mood rating is sent to the Database")
    void insertMoodWorks() {
        int testMoodNum = 2;
        short returnValue = 1;
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)){
            ms.when (() -> DatabaseApiInsert.createMoodEntry(con, testMoodNum, LocalDateTime.now(), "test"))
                    .thenReturn(returnValue);
            Data.insertMood(testMoodNum, testUser);
            assertNull(DatabaseConnection.getInstance().getCon());
        }

    }

    @Test
    void insertTensionWorks() {
        int testTensionNum = 2;
        short returnValue = 1;
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)){
            ms.when (() -> DatabaseApiInsert.createTensionEntry((con), testTensionNum, LocalDateTime.now(), "test"))
                    .thenReturn(returnValue);
            Data.insertTension(testTensionNum, testUser);
            assertNull(DatabaseConnection.getInstance().getCon());
        }
    }
    @Test
    void insertJournalWorks() {
        String testEntry = "test";
        short returnValue = 1;
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)){
            ms.when (() -> DatabaseApiInsert.createJournalEntry((con), testEntry, LocalDateTime.now(), "test"))
                    .thenReturn(returnValue);
            Data.insertJournal(testEntry, testUser);
            assertNull(DatabaseConnection.getInstance().getCon());
        }
    }

    @Test
    void submissionCompleteNoteWorks() {
        try (MockedStatic<Data> msAlert = Mockito.mockStatic(Data.class)) {
            msAlert.when(() -> Data.submissionCompleteNote()).equals(alert);
        }

    }

    @Test
    void journalSubmittedNoteWorks() {
        try (MockedStatic<Data> msAlert = Mockito.mockStatic(Data.class)) {
            msAlert.when(() -> Data.journalSubmittedNote()).equals(alert);
        }
    }

    @Test
    void clearOutJournalEntryWorks() {
        
    }
}