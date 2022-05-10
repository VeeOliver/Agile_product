package se.hkr.app;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DatabaseApiInsert {

    public static void createUserEntry(Connection con, String personnummer,
            String name, String email, String password) throws SQLException {
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

    public static void createMoodEntry(Connection con, int rating,
            LocalDateTime dateTime, String personnummer) throws SQLException {
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

    public static void createTensionEntry(Connection con, int rating,
            LocalDateTime dateTime, String personnummer) throws SQLException {
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
        System.out.println(personnummer);
        stmt.executeUpdate();
    }

    public static void createJournalEntry(Connection con, String text,
            LocalDateTime dateTime, String personnummer) throws SQLException {
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
