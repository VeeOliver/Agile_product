package se.hkr.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JournalEntryTest {
    @Test
    @DisplayName("Test instantiation and getters of JournalEntry object.")
    public void testJournalEntryInstantiationAndGetters() {
        LocalDate date = LocalDate.of(2022, 01, 01);
        String daytime = "Morning";
        String entry = "This is a journal entry";
        JournalEntry testJournalEntry = new JournalEntry(date, daytime, entry);
        assertInstanceOf(JournalEntry.class, testJournalEntry);
        assertEquals(date, testJournalEntry.getDate());
        assertEquals(daytime, testJournalEntry.getDaytime());
        assertEquals(entry, testJournalEntry.getEntry());
    }
    
}
