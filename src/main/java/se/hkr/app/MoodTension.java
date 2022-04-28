package se.hkr.app;

import java.time.LocalDate;

public class MoodTension extends Data {
    private int mood;
    private int tension;

    public MoodTension(LocalDate date, String daytime, int mood, int tension) {
        super(date, daytime);
        this.mood = mood;
        this.tension = tension;
    }

    public int getMood() {
        return this.mood;
    }

    public int getTension() {
        return this.tension;
    }
}
