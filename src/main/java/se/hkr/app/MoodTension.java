package se.hkr.app;


import java.time.LocalDate;


public class MoodTension extends Data {
    /**
     * int: Mood entry from database.
     */
    private final int mood;
    /**
     * int: Tension entry from database.
     */
    private final int tension;

    /**
     * Constructor for MoodTension objects.
     * @param date
     * @param daytime
     * @param newMood
     * @param newTension
     */
    public MoodTension(final LocalDate date, final String daytime,
            final int newMood, final int newTension) {
        super(date, daytime);
        this.mood = newMood;
        this.tension = newTension;
    }

    /**
     * Getter for field Mood.
     * @return int: Mood value of data point
     */
    public int getMood() {
        return this.mood;
    }

    /**
     * Getter for field Tension.
     * @return int: Tension value of data point
     */
    public int getTension() {
        return this.tension;
    }
}
