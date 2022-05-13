package se.hkr.app;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import java.sql.Date;


public abstract class DatabaseApiSelect {
    /**
     * Modes for data retrieval controlling SQL statement.
     */
    public enum RetrieveMode { JOURNAL_ENTRY, MOOD_TENSION }

    /**
     * Parse data from ResultSet into ArrayList with data objects.
     * @param rs
     * @return ArrayList with Data objects (JournalEntry | MoodTension)
     * @throws SQLException
     */
    public static ArrayList<Data> parseToDataList(final ResultSet rs)
            throws SQLException {
        ArrayList<Data> returnList = new ArrayList<>();
        while (rs.next()) {
            switch (rs.getMetaData().getColumnCount()) {
                case 3:
                    String daytimeJE = rs.getString(1);
                    String entryJE = rs.getString(2);
                    LocalDate dateJE = LocalDate.parse(rs.getString(3));
                    Data journalEntry = new JournalEntry(dateJE, daytimeJE,
                        entryJE);
                    returnList.add(journalEntry);
                    break;
                case 4:
                    String daytimeMT = rs.getString(1);
                    int moodMT = (int) rs.getDouble(2);
                    int tensionMT = (int) rs.getDouble(3);
                    LocalDate dateMT = LocalDate.parse(rs.getString(4));
                    Data moodTensionEntry = new MoodTension(dateMT, daytimeMT,
                        moodMT, tensionMT);
                    returnList.add(moodTensionEntry);
                    break;
                default:
                    break;
            }
        }
        return returnList;
    }

    /**
     * Retrieve data on given day (Journal entry | Mood & Tension) from db.
     * @param con
     * @param mode
     * @param date
     * @param personnummer
     * @return ArrayList with Data objects (JournalEntry | MoodTension)
     * @throws SQLException
     */
    public static ArrayList<Data> getData(final Connection con,
            final RetrieveMode mode, final LocalDate date,
            final String personnummer) throws SQLException {
        String sql;
        switch (mode) {
            case JOURNAL_ENTRY:
                sql = """
                SELECT
                    determineTimeOfDay(Journal_entry.date) AS timeOfDay,
                    Journal_entry.journal_entry,
                    DATE(Journal_entry.date) AS date
                FROM Journal_entry
                WHERE DATE(Journal_entry.date) LIKE ?
                    AND Journal_entry.personnummer LIKE ?
                ORDER BY timeOfDay;
                """;
                break;
            case MOOD_TENSION:
                sql = """
                SELECT
                    determineTimeOfDay(Tension.date) AS timeOfDay,
                    AVG(Mood.rating) AS mood,
                    AVG(Tension.rating) AS tension,
                    MAX(DATE(Mood.date)) AS date
                FROM Mood
                JOIN Tension ON Mood.date = Tension.date
                WHERE DATE(Tension.date) = ? AND Mood.personnummer LIKE ?
                GROUP BY timeOfDay
                ORDER BY timeOfDay;
                """;
                break;
            default:
                sql = "";
        }
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, date.toString());
        stmt.setString(2, personnummer);
        ResultSet rs = stmt.executeQuery();
        return parseToDataList(rs);
    }

    /**
     * Check if given personnummer exists in database.
     * @param con
     * @param personnummer
     * @return ResultSet with email for given personnummer
     * @throws SQLException
     */
    public static ResultSet checkPersonnummer(final Connection con,
            final String personnummer) throws SQLException {
        String getPersonnummer = """
                SELECT email
                FROM User
                WHERE personnummer = ?;
            """;
        PreparedStatement stmt = con.prepareStatement(getPersonnummer);
        stmt.setString(1, personnummer);

        return stmt.executeQuery();
    }

    /**
     * Check if given email exists in database.
     * @param con
     * @param email
     * @return ResultSet with personnummer for given email
     * @throws SQLException
     */
    public static ResultSet checkEmail(final Connection con,
            final String email) throws SQLException {
        String getEmail = """
                SELECT personnummer
                FROM User
                WHERE email = ?;
            """;
        PreparedStatement stmt = con.prepareStatement(getEmail);
        stmt.setString(1, email);

        return stmt.executeQuery();
    }

    /**
     * Find a date <input> days before current date.
     * @param days
     * @return Date <input> days earlier than now
     */
    public static Date getDateDaysAgo(final int days) {
        long dayInMs = 1000 * 60 * 60 * 24;
        return new Date(System.currentTimeMillis() - (days * dayInMs));
    };

    /**
     * Retrieve data points for last-week-chart.
     * @param con
     * @param personnummer
     * @return ArrayList with mood and tension data points for last week
     * @throws SQLException
     */
    public static ArrayList<ArrayList> getMTDataChart(final Connection con,
            final String personnummer) throws SQLException {
        ArrayList<Double> moodList = new ArrayList<>();
        ArrayList<Double> tensionList = new ArrayList<>();
        ArrayList<java.util.Date> datesList = new ArrayList<>();
        ArrayList<ArrayList> dataList = new ArrayList<>();
        String weekDate = getDateDaysAgo(7).toString();
        String sql;

                sql = """
                        SELECT
                            Mood.rating, Tension.rating, Mood.Date
                        FROM Mood
                        JOIN Tension ON Mood.date = Tension.date
                        WHERE Mood.personnummer = ? AND Mood.date >= ?;
                      """;


        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, personnummer);
        stmt.setString(2, weekDate);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Double mood = rs.getDouble(1);
            Double tension = rs.getDouble(2);
            java.util.Date date = rs.getDate(3);


            moodList.add(mood);
            tensionList.add(tension);
            datesList.add(date);
        }
        dataList.add(moodList);
        dataList.add(tensionList);
        dataList.add(datesList);

        return dataList;
    }

    /**
     * Retrieve average data for last-week-chart.
     * @param con
     * @param personnummer
     * @return ArrayList with average mood and tension data for last week
     * @throws SQLException
     */
    public static ArrayList<ArrayList> getAvgMTDataChart(final Connection con,
            final String personnummer) throws SQLException {
        ArrayList<Double> moodList = new ArrayList<>();
        ArrayList<Double> tensionList = new ArrayList<>();
        ArrayList<java.util.Date> datesList = new ArrayList<>();
        ArrayList<ArrayList> dataList = new ArrayList<>();
        String weekDate = getDateDaysAgo(7).toString();
        String sql;

        sql = """
                SELECT
                    AVG(Mood.rating), AVG(Tension.rating), DATE(Mood.Date)
                FROM Mood
                JOIN Tension ON Mood.date = Tension.date
                WHERE Mood.personnummer = ? AND Mood.date >= ?
                GROUP BY DATE(Mood.Date);
              """;


        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, personnummer);
        stmt.setString(2, weekDate);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Double mood = rs.getDouble(1);
            Double tension = rs.getDouble(2);
            java.util.Date date = rs.getDate(3);


            moodList.add(mood);
            tensionList.add(tension);
            datesList.add(date);
        }
        dataList.add(moodList);
        dataList.add(tensionList);
        dataList.add(datesList);

        return dataList;
    }
}
