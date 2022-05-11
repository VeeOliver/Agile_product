package se.hkr.app;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

class DataTest {

    @Test
    @DisplayName("Test instantiation and getters of Data object.")
    public void testDataInstantiationAndGetters() {
        LocalDate date = LocalDate.of(2022, 1, 1);
        String daytime = "Morning";
        Data testData = new Data(date, daytime);
        assertInstanceOf(Data.class, testData);
        assertEquals(date, testData.getDate());
        assertEquals(daytime, testData.getDaytime());
    }

    @Mock
    Connection con;

    @Test
    @DisplayName("Testing to see if the mood rating is sent to the Database")
    void insertMoodWorks() throws SQLException {
        int testMoodNum = 2;
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)) {
            ms.when(() -> DatabaseApiInsert.createMoodEntry(con, testMoodNum,
                    LocalDateTime.now(), "test"))
                .thenAnswer(answer -> null);

            Data.insertMood(testMoodNum, testUser);

            ms.verify(() -> DatabaseApiInsert.createMoodEntry(any(Connection.class),
                anyInt(), any(LocalDateTime.class), anyString()), times(1));
            assertNull(DatabaseConnection.getInstance().getCon());
        }
    }

    @Test
    void insertTensionWorks() throws SQLException {
        int testTensionNum = 2;
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)) {
            ms.when(() -> DatabaseApiInsert.createTensionEntry((con), testTensionNum,
                    LocalDateTime.now(), "test"))
                .thenAnswer(answer -> null);

            Data.insertTension(testTensionNum, testUser);

            ms.verify(() -> DatabaseApiInsert.createTensionEntry(any(Connection.class),
                anyInt(), any(LocalDateTime.class), anyString()), times(1));
            assertNull(DatabaseConnection.getInstance().getCon());
        }
    }

    @Test
    void insertJournalWorks() throws SQLException {
        String testEntry = "test";
        User testUser = User.getInstance("test", "test", "test");
        try (MockedStatic<DatabaseApiInsert> ms = Mockito.mockStatic(DatabaseApiInsert.class)) {
            ms.when(() -> DatabaseApiInsert.createJournalEntry((con), testEntry,
                    LocalDateTime.now(), "test"))
                .thenAnswer(answer -> null);

            Data.insertJournal(testEntry, testUser);

            ms.verify(() -> DatabaseApiInsert.createJournalEntry(any(Connection.class),
                anyString(), any(LocalDateTime.class), anyString()), times(1));
            assertNull(DatabaseConnection.getInstance().getCon());
        }
    }

    @Test
    void submissionCompleteNoteWorks() {
        try (MockedStatic<Data> msAlert = Mockito.mockStatic(Data.class)) {
            msAlert.when(Data::submissionCompleteNote).thenAnswer((Answer<Void>) invocation -> null);
        }

    }

    @Test
    void journalSubmittedNoteWorks() {
        try (MockedStatic<Data> msAlert = Mockito.mockStatic(Data.class)) {
            msAlert.when(Data::submissionCompleteNote).thenAnswer((Answer<Void>) invocation -> null);
        }
    }
}