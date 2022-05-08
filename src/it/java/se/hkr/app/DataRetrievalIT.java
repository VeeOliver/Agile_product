package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import se.hkr.app.DatabaseApiSelect.RetrieveMode;

public class DataRetrievalIT {
    @BeforeEach
    public void init() {
        DatabaseConnection.getInstance("127.0.0.1:5000");
        DatabaseConnection.getInstance().disconnect();
        User.resetInstance();
    }

    @Test
    public void testUser1MoodTensionFirstDayForGetDataReturnsCorrectData() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        RetrieveMode mode = RetrieveMode.MOOD_TENSION;
        LocalDate date = LocalDate.parse("2022-03-01");
        String personnummer = "111111-1111";
        var res = DatabaseApiSelect.getData(con, mode, date, personnummer);
        dbCon.disconnect();
        assertEquals(null, dbCon.getCon());
        assertEquals(4, res.size());

        MoodTension entry1 = (MoodTension) res.get(0);
        MoodTension entry2 = (MoodTension) res.get(1);
        MoodTension entry3 = (MoodTension) res.get(2);
        MoodTension entry4 = (MoodTension) res.get(3);
        assertInstanceOf(MoodTension.class, entry1);
        assertEquals(date, entry1.getDate());
        assertEquals(3, entry1.getTension());
        assertEquals(5, entry1.getMood());
        assertEquals("1-Morning", entry1.getDaytime());
        assertEquals(date, entry2.getDate());
        assertEquals(4, entry2.getTension());
        assertEquals(7, entry2.getMood());
        assertEquals("2-Noon", entry2.getDaytime());
        assertEquals(date, entry3.getDate());
        assertEquals(6, entry3.getTension());
        assertEquals(6, entry3.getMood());
        assertEquals("3-Afternoon", entry3.getDaytime());
        assertEquals(date, entry4.getDate());
        assertEquals(8, entry4.getTension());
        assertEquals(6, entry4.getMood());
        assertEquals("4-Evening", entry4.getDaytime());
    }

    @Test
    public void testUser1MoodTensionMarch03ForGetDataReturnsNoData() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        RetrieveMode mode = RetrieveMode.MOOD_TENSION;
        LocalDate date = LocalDate.parse("2022-03-03");
        String personnummer = "111111-1111";
        var res = DatabaseApiSelect.getData(con, mode, date, personnummer);
        dbCon.disconnect();        
        assertEquals(null, dbCon.getCon());
        assertInstanceOf(ArrayList.class, res);
        assertEquals(0, res.size());
    }

    @Disabled("Bug in testes function.")
    @Test
    public void testUser1MoodTensionFirstFiveDaysForGetDataReturnsCorrectData() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        RetrieveMode mode = RetrieveMode.MOOD_TENSION;
        LocalDate startDate = LocalDate.parse("2022-03-01");
        LocalDate secondDay = LocalDate.parse("2022-03-02");
        LocalDate endDate = LocalDate.parse("2022-03-05");
        String personnummer = "111111-1111";
        var res = DatabaseApiSelect.getData(con, mode, startDate, endDate, personnummer);
        dbCon.disconnect();
        assertEquals(null, dbCon.getCon());
        assertEquals(10, res.size());

        MoodTension entry1 = (MoodTension) res.get(0);
        MoodTension entry2 = (MoodTension) res.get(1);
        MoodTension entry3 = (MoodTension) res.get(2);
        MoodTension entry4 = (MoodTension) res.get(3);
        MoodTension entry5 = (MoodTension) res.get(4);
        MoodTension entry6 = (MoodTension) res.get(5);
        MoodTension entry7 = (MoodTension) res.get(6);
        MoodTension entry8 = (MoodTension) res.get(7);
        MoodTension entry9 = (MoodTension) res.get(8);
        MoodTension entry10 = (MoodTension) res.get(9);

        assertInstanceOf(MoodTension.class, entry1);
        assertEquals(startDate, entry1.getDate());
        assertEquals(3, entry1.getTension());
        assertEquals(5, entry1.getMood());
        assertEquals("1-Morning", entry1.getDaytime());
        assertEquals(startDate, entry2.getDate());
        assertEquals(4, entry2.getTension());
        assertEquals(7, entry2.getMood());
        assertEquals("2-Noon", entry2.getDaytime());
        assertEquals(startDate, entry3.getDate());
        assertEquals(6, entry3.getTension());
        assertEquals(6, entry3.getMood());
        assertEquals("3-Afternoon", entry3.getDaytime());
        assertEquals(startDate, entry4.getDate());
        assertEquals(8, entry4.getTension());
        assertEquals(6, entry4.getMood());
        assertEquals("4-Evening", entry4.getDaytime());

        assertEquals(secondDay, entry5.getDate());
        assertEquals(7, entry5.getTension());
        assertEquals(1, entry5.getMood());
        assertEquals("1-Morning", entry5.getDaytime());
        assertEquals(secondDay, entry6.getDate());
        assertEquals(5, entry6.getTension());
        assertEquals(3, entry6.getMood());
        assertEquals("2-Noon", entry6.getDaytime());
        assertEquals(secondDay, entry7.getDate());
        assertEquals(4, entry7.getTension());
        assertEquals(2, entry7.getMood());
        assertEquals("3-Afternoon", entry7.getDaytime());

        assertEquals(endDate, entry8.getDate());
        assertEquals(4, entry8.getTension());
        assertEquals(8, entry8.getMood());
        assertEquals("1-Morning", entry8.getDaytime());
        assertEquals(endDate, entry9.getDate());
        assertEquals(2, entry9.getTension());
        assertEquals(7, entry9.getMood());
        assertEquals("2-Noon", entry9.getDaytime());
        assertEquals(endDate, entry10.getDate());
        assertEquals(1, entry10.getTension());
        assertEquals(9, entry10.getMood());
        assertEquals("4-Evening", entry10.getDaytime());
    }

    @Test
    public void testUser1JournalEntryFirstDayForGetDataReturnsCorrectData() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        RetrieveMode mode = RetrieveMode.JOURNAL_ENTRY;
        LocalDate date = LocalDate.parse("2022-03-01");
        String personnummer = "111111-1111";
        var res = DatabaseApiSelect.getData(con, mode, date, personnummer);
        dbCon.disconnect();
        assertEquals(null, dbCon.getCon());
        assertEquals(2, res.size());

        JournalEntry entry1 = (JournalEntry) res.get(0);
        JournalEntry entry2 = (JournalEntry) res.get(1);
        assertInstanceOf(JournalEntry.class, entry1);
        assertEquals(date, entry1.getDate());
        assertEquals("User 1 on 2022-03-01 Afternoon", entry1.getEntry());
        assertEquals("3-Afternoon", entry1.getDaytime());
        assertEquals(date, entry2.getDate());
        assertEquals("User 1 on 2022-03-01 Afternoon", entry2.getEntry());
        assertEquals("3-Afternoon", entry2.getDaytime());
    }

    @Test
    public void testUser1JournalEntryMarch03ForGetDataReturnsNoData() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        RetrieveMode mode = RetrieveMode.JOURNAL_ENTRY;
        LocalDate date = LocalDate.parse("2022-03-03");
        String personnummer = "111111-1111";
        var res = DatabaseApiSelect.getData(con, mode, date, personnummer);
        dbCon.disconnect();        
        assertEquals(null, dbCon.getCon());
        assertInstanceOf(ArrayList.class, res);
        assertEquals(0, res.size());
    }

    @Test
    public void testUser1JournalEntriesFirstFiveDaysForGetDataReturnsCorrectData() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        RetrieveMode mode = RetrieveMode.JOURNAL_ENTRY;
        LocalDate startDate = LocalDate.parse("2022-03-01");
        LocalDate secondDay = LocalDate.parse("2022-03-02");
        LocalDate endDate = LocalDate.parse("2022-03-05");
        String personnummer = "111111-1111";
        var res = DatabaseApiSelect.getData(con, mode, startDate, endDate, personnummer);
        dbCon.disconnect();
        assertEquals(null, dbCon.getCon());
        assertEquals(5, res.size());

        JournalEntry entry1 = (JournalEntry) res.get(0);
        JournalEntry entry2 = (JournalEntry) res.get(1);
        JournalEntry entry3 = (JournalEntry) res.get(2);
        JournalEntry entry4 = (JournalEntry) res.get(3);
        JournalEntry entry5 = (JournalEntry) res.get(4);

        assertInstanceOf(JournalEntry.class, entry1);
        assertEquals(startDate, entry1.getDate());
        assertEquals("User 1 on 2022-03-01 Afternoon", entry1.getEntry());
        assertEquals("3-Afternoon", entry1.getDaytime());
        assertEquals(startDate, entry2.getDate());
        assertEquals("User 1 on 2022-03-01 Afternoon", entry2.getEntry());
        assertEquals("3-Afternoon", entry2.getDaytime());
        assertEquals(secondDay, entry3.getDate());
        assertEquals("User 1 on 2022-03-02 Noon", entry3.getEntry());
        assertEquals("2-Noon", entry3.getDaytime());
        assertEquals(endDate, entry4.getDate());
        assertEquals("User 1 on 2022-03-05 Noon", entry4.getEntry());
        assertEquals("2-Noon", entry4.getDaytime());
        assertEquals(endDate, entry5.getDate());
        assertEquals("User 1 on 2022-03-05 Evening", entry5.getEntry());
        assertEquals("4-Evening", entry5.getDaytime());
    }

    @Test
    public void testUser1JournalEntriesMoodRating6to9ForGetJournalEntriesByMoodRangeReturnsCorrectData() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        String personnummer = "111111-1111";
        int moodLower = 6;
        int moodUpper = 9;
        var res = DatabaseApiSelect.getJournalEntriesByMoodRange(con, moodLower, moodUpper, personnummer);
        dbCon.disconnect();

        assertEquals(null, dbCon.getCon());
        assertEquals(5, res.size());

        JournalEntry entry1 = (JournalEntry) res.get(0);
        JournalEntry entry2 = (JournalEntry) res.get(1);
        JournalEntry entry3 = (JournalEntry) res.get(2);
        JournalEntry entry4 = (JournalEntry) res.get(3);
        JournalEntry entry5 = (JournalEntry) res.get(4);

        assertInstanceOf(JournalEntry.class, entry1);
        assertEquals(LocalDate.parse("2022-03-01"), entry1.getDate());
        assertEquals("User 1 on 2022-03-01 Afternoon", entry1.getEntry());
        assertEquals("3-Afternoon", entry1.getDaytime());
        assertEquals(LocalDate.parse("2022-03-05"), entry2.getDate());
        assertEquals("User 1 on 2022-03-05 Noon", entry2.getEntry());
        assertEquals("2-Noon", entry2.getDaytime());
        assertEquals(LocalDate.parse("2022-03-05"), entry3.getDate());
        assertEquals("User 1 on 2022-03-05 Evening", entry3.getEntry());
        assertEquals("4-Evening", entry3.getDaytime());
        assertEquals(LocalDate.parse("2022-03-08"), entry4.getDate());
        assertEquals("User 1 on 2022-03-08 Noon", entry4.getEntry());
        assertEquals("2-Noon", entry4.getDaytime());
        assertEquals(LocalDate.parse("2022-03-08"), entry5.getDate());
        assertEquals("User 1 on 2022-03-08 Afternoon", entry5.getEntry());
        assertEquals("3-Afternoon", entry5.getDaytime());
    }

    // Tests for journal entry retrieval
    @Test
    public void testUser1JournalEntryRetrievalForMarch01ReturnsTwoEntries() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        String exp = "User 1 on 2022-03-01 Afternoon\nUser 1 on 2022-03-01 Afternoon\n";
        LocalDate date = LocalDate.parse("2022-03-01");
        String personnummer = "111111-1111";
        var res = JournalEntry.retrieveJournalEntry(con, date, personnummer);
        dbCon.disconnect();

        assertEquals(null, dbCon.getCon());

        assertEquals(exp, res);
    }

    @Test
    public void testUser1JournalEntryRetrievalForMarch02ReturnsOneEntry() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        String exp = "User 1 on 2022-03-02 Noon\n";
        LocalDate date = LocalDate.parse("2022-03-02");
        String personnummer = "111111-1111";
        var res = JournalEntry.retrieveJournalEntry(con, date, personnummer);
        dbCon.disconnect();

        assertEquals(null, dbCon.getCon());

        assertEquals(exp, res);
    }

    @Test
    public void testUser1JournalEntryRetrievalForMarch03ReturnsNoEntries() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        String exp = "No journal entries on this day";
        LocalDate date = LocalDate.parse("2022-03-03");
        String personnummer = "111111-1111";
        var res = JournalEntry.retrieveJournalEntry(con, date, personnummer);
        dbCon.disconnect();

        assertEquals(null, dbCon.getCon());

        assertEquals(exp, res);
    }
}
