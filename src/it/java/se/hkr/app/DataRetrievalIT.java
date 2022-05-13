package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.hkr.app.DatabaseApiSelect.RetrieveMode;

public class DataRetrievalIT {
    @BeforeEach
    public void init() throws SQLException {
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
