package se.hkr.app;


import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Data {
    /**
     * LocalDate: Date when data was entered to database.
     */
    private LocalDate date;

    /**
     * String: Daytime when entry was made in database.
     */
    private String daytime;

    /**
     * Create a data object with date and daytime.
     * @param newDate
     * @param newDaytime
     */
    public Data(final LocalDate newDate, final String newDaytime) {
        this.date = newDate;
        this.daytime = newDaytime;
    }

    /**
     * Getter for field date.
     * @return LocalDate: Date when data was entered to database
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Getter for field daytime.
     * @return String: Daytime when entry was made in database
     */
    public String getDaytime() {
        return this.daytime;
    }

    /**
     * Enter a mood entry to the remote database.
     * @param moodRating
     * @param user
     * @throws SQLException
     */
    // connects GUI value for Mood to the Database
    public static void insertMood(final double moodRating,
            final User user) throws SQLException {
        int moodNum = (int) moodRating;
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createMoodEntry(DatabaseConnection.getInstance()
            .connect(), moodNum, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    /**
     * Enter a tension entry to the remote database.
     * @param tensionRating
     * @param user
     * @throws SQLException
     */
    // connects GUI value for Tension to the Database
    public static void insertTension(final double tensionRating,
            final User user) throws SQLException {
        int tensionNum = (int) tensionRating;
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createTensionEntry(DatabaseConnection.getInstance()
            .connect(), tensionNum, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    /**
     * Enter a journal entry to the remote database.
     * @param journalText
     * @param user
     * @throws SQLException
     */
    // connects GUI Journal Entry to the Database
    public static void insertJournal(final String journalText,
            final User user) throws SQLException {
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createJournalEntry(DatabaseConnection.getInstance()
            .connect(), journalText, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    /**
     * Display message after successfull mood and tension entry submission.
     */
    // Sends a pop-up message for submission of Mood and Tension
    public static void submissionCompleteNote() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submitted");
        alert.setHeaderText("Mood and Tension results have been submitted");
        alert.setContentText("See you next time!");
        alert.showAndWait();

    }

    /**
     * Display message after successfull journal entry submission.
     */
    // Sends a pop-up message for submission of Journal Entry
    public static void journalSubmittedNote() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submitted");
        alert.setHeaderText("Your journal has been saved!");
        alert.setContentText("You're a wonderful author!");
        alert.showAndWait();
    }
}
