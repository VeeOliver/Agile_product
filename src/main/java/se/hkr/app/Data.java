package se.hkr.app;


import java.time.LocalDateTime;
import java.util.ArrayList;


public class Data {

    static public ArrayList<User> users = new ArrayList<>();


    public static void insertMood(String moodRating,  User user) {
        int moodNum = Integer.parseInt(moodRating);
        String userId = user.email;
        DatabaseApiInsert.createMoodEntry(DatabaseConnection.getInstance().getCon(), moodNum, LocalDateTime.now(), userId);

    }

    public static void insertTension(String tensionRating, User user) {
        int tensionNum = Integer.parseInt(tensionRating);
        String userId = user.email;
        DatabaseApiInsert.createTensionEntry(DatabaseConnection.getInstance().getCon(), tensionNum, LocalDateTime.now(), userId);

    }

    public static void insertJournal(String journalText, User user) {
        String userId = user.email;
        DatabaseApiInsert.createJournalEntry(DatabaseConnection.getInstance().getCon(), journalText, LocalDateTime.now(), userId);

    }

} 

