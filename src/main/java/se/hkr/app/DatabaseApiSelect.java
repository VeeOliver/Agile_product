package se.hkr.app;


import org.javatuples.Triplet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseApiSelect {
    // Convert ResultSet to List of Triplets
    public static ArrayList<Triplet<String, Object, LocalDate>> parseDataToTuples (ResultSet rs) {
        ArrayList<Triplet<String, Object, LocalDate>> returnList = new ArrayList<>();
        try {
            while (rs.next()) {
                Triplet<String, Object, LocalDate> entry = new Triplet<>(rs.getString(1),
                        rs.getObject(2), LocalDate.parse(rs.getString(3)));
                returnList.add(entry);
            }
        } catch (SQLException e) {
            return null;
        }
        return returnList;
    }


    // Retrieve data by day
    public static ResultSet getDataByDay(Connection con, String sql, LocalDate date, String personnummer) {
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, date.toString());
            stmt.setString(2, personnummer);
            return stmt.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }

    // Retrieve average data for time period
    public static ResultSet getDataByTimePeriod(Connection con, String sql, LocalDate startDate,
                                                LocalDate endDate, String personnummer) {
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, startDate.toString());
            stmt.setString(2, endDate.toString());
            stmt.setString(3, personnummer);
            return stmt.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }

    // Retrieve by rating range
    public static ResultSet getDataByRatingRange (Connection con, String sql, int ratingLower, int ratingUpper,
                                                  String personnummer) {
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, ratingLower);
            stmt.setInt(2, ratingUpper);
            stmt.setString(3, personnummer);
            return stmt.executeQuery();
        } catch (Exception e) {
            return null;
        }
    }

    // SQL statements for mood retrieval
    public static String moodOneDay = """
            SELECT
                determineTimeOfDay(Mood.date) AS timeOfDay,
                AVG(Mood.rating) AS mood,
                MAX(DATE(Mood.date)) AS date
            FROM Mood
            WHERE DATE(Mood.date) LIKE ? AND Mood.personnummer LIKE ?
            GROUP BY timeOfDay;""";

    public static String averageMoodTimePeriod = """
            SELECT
                determineTimeOfDay(Mood.date) AS timeOfDay,
                AVG(Mood.rating) AS mood,
                MAX(DATE(Mood.date)) AS endOfPeriod
            FROM Mood
            WHERE DATE(Mood.date) >= ? AND DATE(Mood.date) < ? AND Mood.personnummer LIKE ?
            GROUP BY timeOfDay;""";

    public static String timesWithMoodRange = """
            SELECT DISTINCT
                determineTimeOfDay(Mood.date) AS timeOfDay,
                Mood.rating AS mood,
                DATE(Mood.date) AS date
            FROM Mood
            WHERE Mood.rating >= ? AND Mood.rating < ? AND Mood.personnummer LIKE ? ;
            """;

    // SQL statements for tension retrieval
    public static String tensionOneDay = """
            SELECT
                determineTimeOfDay(Tension.date) AS timeOfDay,
                AVG(Tension.rating) AS tension,
                MAX(DATE(Tension.date)) as date
            FROM Tension
            WHERE DATE(Tension.date) LIKE ? AND Tension.personnummer LIKE ?
            GROUP BY timeOfDay;""";

    public static String averageTensionTimePeriod = """
            SELECT
                determineTimeOfDay(Tension.date) AS timeOfDay,
                AVG(Tension.rating) AS tension,
                MAX(DATE(Mood.date)) AS endOfPeriod
            FROM Tension
            WHERE DATE(Tension.date) >= ? AND DATE(Tension.date) < ? AND Tension.personnummer LIKE ?
            GROUP BY timeOfDay;""";

    public static String timesWithTensionRange = """
            SELECT DISTINCT
                determineTimeOfDay(Tension.date) AS timeOfDay,
                Tension.rating AS tension,
                DATE(Tension.date) AS date
            FROM Tension
            WHERE Tension.rating >= ? AND Tension.rating < ? AND Tension.personnummer LIKE ? ;""";

    // SQL statements for journal entry retrieval
    public static String journalEntriesDay = """
            SELECT
                determineTimeOfDay(Journal_entry.date) AS timeOfDay,
                Journal_entry.journal_entry,
                Journal_entry.date AS date
            FROM Journal_entry
            WHERE DATE(Journal_entry.date) LIKE ? AND Journal_entry.personnummer LIKE ?
            ORDER BY Journal_entry.date;""";

    public static String journalEntriesByMoodRange = """
            SELECT
                determineTimeOfDay(Journal_entry.date) AS timeOfDay,
                Journal_entry.journal_entry,
                DATE(Journal_entry.date) AS date
            FROM Journal_entry
            NATURAL JOIN Mood ON personnummer
            WHERE Mood.rating >= ? AND Mood.rating < ? AND Journal_entry.personnummer LIKE ?
            ORDER BY Journal_entry.date;""";

    public static String getJournalEntriesByTensionRange = """
            SELECT
                determineTimeOfDay(Journal_entry.date) AS timeOfDay,
                Journal_entry.journal_entry,
                DATE(Journal_entry.date) AS date
            FROM Journal_entry
            NATURAL JOIN Tension ON personnummer
            WHERE Tension.rating >= ? AND Tension.rating < ? AND Journal_entry.personnummer LIKE ?
            ORDER BY Journal_entry.date;""";

    // Check login
    public static String getLogin = """
            SELECT
                email, personnummer, name
            FROM User
            WHERE email= ? and password = SHA1( ? );
            """;
    public static String getPersonnummer = """
            SELECT email FROM User WHERE  personnummer = ?""";

    public static String getEmail = """
            SELECT personnummer FROM User WHERE  email = ?""";
}
