package se.hkr.app;

import java.time.LocalDate;

public class JournalEntry extends Data {
    private String entry;
    
    public JournalEntry(LocalDate date, String daytime, String entry) {
        super(date, daytime);
        this.entry = entry;
    }

    public String getEntry() {
        return this.entry;
    }
}
