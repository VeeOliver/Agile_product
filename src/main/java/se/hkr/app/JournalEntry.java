package se.hkr.app;


import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import se.hkr.app.DatabaseApiSelect.RetrieveMode;


public class JournalEntry extends Data {
    /**
     * String: Journal entry from database.
     */
    private final String entry;

    /**
     * Contructor for JournalEntry objects.
     * @param date
     * @param daytime
     * @param newEntry
     */
    public JournalEntry(final LocalDate date, final String daytime,
            final String newEntry) {
        super(date, daytime);
        this.entry = newEntry;
    }

    /**
     * Getter for field entry.
     * @return Value of field entry
     */
    public String getEntry() {
        return this.entry;
    }

    /**
     * Retrieve formatted journal entries of given day from database.
     * @param con
     * @param date
     * @param personnummer
     * @return Formatted journal entries of current user on given day
     * @throws SQLException
     */
    public static String retrieveJournalEntry(final Connection con,
            final LocalDate date, final String personnummer)
            throws SQLException {
        String returnValue = "";
        RetrieveMode mode = RetrieveMode.JOURNAL_ENTRY;
        ArrayList<Data> listOfEntries = DatabaseApiSelect.getData(con, mode,
            date, personnummer);
        if (listOfEntries.isEmpty()) {
            returnValue = "No journal entries on this day";
        } else {
            for (Data dataEntry : listOfEntries) {
                JournalEntry entry = (JournalEntry) dataEntry;
                returnValue += entry.getEntry();
                returnValue += "\n";
            }
        }
        return returnValue;
    }
}
