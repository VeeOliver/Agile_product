package se.hkr.app;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;


public abstract class DatabaseApiInsert {
    /**
     * Insert user entry into database.
     * @param con
     * @param personnummer
     * @param name
     * @param email
     * @param password
     * @throws SQLException
     */
    public static void createUserEntry(final Connection con,
            final String personnummer, final String name,
            final String email, final String password) throws SQLException {
        String newUserSql = """
                INSERT INTO User
                VALUES (
                    ? , -- Personnummer
                    ? , -- Name
                    ? , -- Email
                    SHA1( ? ) -- Password in hashed form
                );""";

        PreparedStatement stmt = con.prepareStatement(newUserSql);
        stmt.setString(1, personnummer);
        stmt.setString(2, name);
        stmt.setString(3, email);
        stmt.setString(4, password);
        stmt.executeUpdate();
    }

    /**
     * Insert mood entry into database.
     * @param con
     * @param rating
     * @param dateTime
     * @param personnummer
     * @throws SQLException
     */
    public static void createMoodEntry(final Connection con,
            final int rating, final LocalDateTime dateTime,
            final String personnummer) throws SQLException {
        String newMoodSql = """
                INSERT INTO Mood (
                    rating,
                    date,
                    personnummer
                )
                VALUES (
                    ? , -- Rating
                    ? , -- Date and time at insertion
                    ? -- Personnummer of current user
                );""";

        PreparedStatement stmt = con.prepareStatement(newMoodSql);
        stmt.setInt(1, rating);
        stmt.setString(2, dateTime.toString());
        stmt.setString(3, personnummer);
        stmt.executeUpdate();
    }

    /**
     * Insert tension entry into database.
     * @param con
     * @param rating
     * @param dateTime
     * @param personnummer
     * @throws SQLException
     */
    public static void createTensionEntry(final Connection con,
            final int rating, final LocalDateTime dateTime,
            final String personnummer) throws SQLException {
        String newTensionSql = """
                INSERT INTO Tension (
                    rating,
                    date,
                    personnummer
                )
                VALUES (
                    ? , -- Rating
                    ? , -- Date and time at insertion
                    ? -- Personnummer of current user
                );""";

        PreparedStatement stmt = con.prepareStatement(newTensionSql);
        stmt.setInt(1, rating);
        stmt.setString(2, dateTime.toString());
        stmt.setString(3, personnummer);
        stmt.executeUpdate();
    }

    /**
     * Insert journal entry into database.
     * @param con
     * @param text
     * @param dateTime
     * @param personnummer
     * @throws SQLException
     */
    public static void createJournalEntry(final Connection con,
            final String text, final LocalDateTime dateTime,
            final String personnummer) throws SQLException {
        String newJournalEntrySql = """
                INSERT INTO Journal_entry (
                    journal_entry,
                    date,
                    personnummer
                )
                VALUES (
                    ? , -- Text entry
                    ? , -- Date and time at insertion
                    ? -- Personnummer of current user
                );""";

        PreparedStatement stmt = con.prepareStatement(newJournalEntrySql);
        stmt.setString(1, text);
        stmt.setString(2, dateTime.toString());
        stmt.setString(3, personnummer);
        stmt.executeUpdate();
    }
}
