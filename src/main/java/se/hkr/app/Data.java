package se.hkr.app;


import java.time.LocalDateTime;
import java.util.ArrayList;


public class Data {

    static public ArrayList<User> users = new ArrayList<>();

    public static void insertMood(double moodRating,  User user) {
        int moodNum = (int) moodRating;
        String userId = user.getPersonnumer();
        DatabaseApiInsert.createMoodEntry(DatabaseConnection.getInstance().connect(), moodNum, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    public static void insertTension(double tensionRating, User user) {
        int tensionNum = (int) tensionRating;
        String userId = user.getPersonnumer();
        DatabaseApiInsert.createTensionEntry(DatabaseConnection.getInstance().connect(), tensionNum, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

    public static void insertJournal(String journalText, User user) {
        String userId = user.getPersonnumer();
        DatabaseApiInsert.createJournalEntry(DatabaseConnection.getInstance().connect(), journalText, LocalDateTime.now(), userId);
        DatabaseConnection.getInstance().disconnect();
    }

} 

