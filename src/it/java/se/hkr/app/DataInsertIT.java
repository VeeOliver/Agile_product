package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataInsertIT {
    @BeforeEach
    public void init() {
        DatabaseConnection.getInstance("127.0.0.1:5000");
        DatabaseConnection.getInstance().disconnect();
        User.getInstance("111111-1111", "Test User 1", "test1@myrmidon.com");
    }

    @Test
    public void testInsertMoodFeatureCreatesMoodEntry() throws SQLException {
        double moodRatingDbl = 4.0;
        int moodRatingInt = (int) moodRatingDbl;
        User user = User.getInstance();
        String personnummer = user.getPersonnummer();
        Data.insertMood(moodRatingDbl, user);
        Connection conAfterTest = DatabaseConnection.getInstance().getCon();
        Connection con = DatabaseConnection.getInstance().connect();
        String sql = """
                SELECT *
                FROM Mood
                ORDER BY mood_id DESC
                LIMIT 1;
                """;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        int ratingInserted = 0;
        String personnummerInserted = "";
        while (rs.next()) {
            ratingInserted = rs.getInt(2);
            personnummerInserted = rs.getString(4);
        }
        DatabaseConnection.getInstance().disconnect();
        assertEquals(null, DatabaseConnection.getInstance().getCon());
        assertEquals(moodRatingInt, ratingInserted);
        assertEquals(personnummer, personnummerInserted);
        assertEquals(null, conAfterTest);
    }

    @Test
    public void testInsertTensionFeatureCreatesTensionEntry() throws SQLException {
        double tensionRatingDbl = 7.0;
        int tensionRatingInt = (int) tensionRatingDbl;
        User user = User.getInstance();
        String personnummer = user.getPersonnummer();
        Data.insertTension(tensionRatingDbl, user);
        Connection conAfterTest = DatabaseConnection.getInstance().getCon();
        Connection con = DatabaseConnection.getInstance().connect();
        String sql = """
                SELECT *
                FROM Tension
                ORDER BY tension_id DESC
                LIMIT 1;
                """;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        int ratingInserted = 0;
        String personnummerInserted = "";
        while (rs.next()) {
            ratingInserted = rs.getInt(2);
            personnummerInserted = rs.getString(4);
        }
        DatabaseConnection.getInstance().disconnect();
        assertEquals(null, DatabaseConnection.getInstance().getCon());
        assertEquals(tensionRatingInt, ratingInserted);
        assertEquals(personnummer, personnummerInserted);
        assertEquals(null, conAfterTest);
    }

    @Test
    public void testInsertJournalEntryFeatureCreatesJournalEntry() throws SQLException {
        String journalEntry = "This is a test!";
        User user = User.getInstance();
        String personnummer = user.getPersonnummer();
        Data.insertJournal(journalEntry, user);
        Connection conAfterTest = DatabaseConnection.getInstance().getCon();
        Connection con = DatabaseConnection.getInstance().connect();
        String sql = """
                SELECT *
                FROM Journal_entry
                ORDER BY journal_entry_id DESC
                LIMIT 1;
                """;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        String journalEntryInserted = "";
        String personnummerInserted = "";
        while (rs.next()) {
            journalEntryInserted = rs.getString(2);
            personnummerInserted = rs.getString(4);
        }
        DatabaseConnection.getInstance().disconnect();
        assertEquals(null, DatabaseConnection.getInstance().getCon());
        assertEquals(journalEntry, journalEntryInserted);
        assertEquals(personnummer, personnummerInserted);
        assertEquals(null, conAfterTest);
    }
}
