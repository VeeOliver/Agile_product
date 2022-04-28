package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class MoodTensionTest {
    @Test
    @DisplayName("Test instantiation and getters of MoodTension object.")
    public void testMoodTensionInstantiationAndGetters() {
        LocalDate date = LocalDate.of(2022, 01, 01);
        String daytime = "Morning";
        int mood = 4;
        int tension = 6;
        MoodTension testMoodTension = new MoodTension(date, daytime, mood, tension);
        assertInstanceOf(MoodTension.class, testMoodTension);
        assertEquals(date, testMoodTension.getDate());
        assertEquals(daytime, testMoodTension.getDaytime());
        assertEquals(mood, testMoodTension.getMood());
        assertEquals(tension, testMoodTension.getTension());
    }    
}
