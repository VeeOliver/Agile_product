package se.hkr.app;


import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import org.controlsfx.control.action.Action;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class Data {

    static public ArrayList<User> users = new ArrayList<>();

    public static void insertMood(double moodRating,  User user) {
        int moodNum = (int) moodRating;
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createMoodEntry(DatabaseConnection.getInstance().connect(), moodNum, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    public static void insertTension(double tensionRating, User user) {
        int tensionNum = (int) tensionRating;
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createTensionEntry(DatabaseConnection.getInstance().connect(), tensionNum, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    public static void insertJournal(String journalText, User user) {
        String userId = user.getPersonnummer();
        DatabaseApiInsert.createJournalEntry(DatabaseConnection.getInstance().connect(), journalText, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    public static void submissionCompleteNote(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submitted");
        alert.setHeaderText("Mood and Tension results have been submitted");
        alert.setContentText("See you next time!");
        alert.showAndWait();

    }

    public static void journalSubmittedNote(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Submitted");
        alert.setHeaderText("Your journal has been saved!");
        alert.setContentText("You're a wonderful author!");
        alert.showAndWait();
    }

    public static void clearOutJournalEntry(TextArea journalEntry){
        journalEntry.setText("");
    }

} 

