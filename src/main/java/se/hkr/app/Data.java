package se.hkr.app;


import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Data {

    private LocalDate date;
    private String daytime;

    public Data(LocalDate date, String daytime) {
        this.date = date;
        this.daytime = daytime;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getDaytime() {
        return this.daytime;
    }

    // connects GUI value for Mood to the Database
    public static void insertMood(double moodRating, User user) throws SQLException {
        int moodNum = (int) moodRating;
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createMoodEntry(DatabaseConnection.getInstance().connect(), moodNum, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    // connects GUI value for Tension to the Database
    public static void insertTension(double tensionRating, User user) throws SQLException {
        int tensionNum = (int) tensionRating;
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createTensionEntry(DatabaseConnection.getInstance().connect(), tensionNum, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    // connects GUI Journal Entry to the Database
    public static void insertJournal(String journalText, User user) throws SQLException {
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createJournalEntry(DatabaseConnection.getInstance().connect(), journalText, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    // Sends a pop-up message for submission of Mood and Tension
    public static void submissionCompleteNote() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submitted");
        alert.setHeaderText("Mood and Tension results have been submitted");
        alert.setContentText("See you next time!");
        alert.showAndWait();

    }

    // Sends a pop-up message for submission of Journal Entry
    public static void journalSubmittedNote() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submitted");
        alert.setHeaderText("Your journal has been saved!");
        alert.setContentText("You're a wonderful author!");
        alert.showAndWait();
    }
}
