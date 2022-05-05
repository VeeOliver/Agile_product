package se.hkr.app;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import se.hkr.app.DatabaseApiSelect.RetrieveMode;

public class JournalEntry extends Data {
    private String entry;

    public JournalEntry(LocalDate date, String daytime, String entry) {
        super(date, daytime);
        this.entry = entry;
    }

    public String getEntry() {
        return this.entry;
    }

    public static String retrieveJournalEntry(LocalDate date, String personnummer) throws SQLException {
        String returnValue = "";
        DatabaseConnection dbCon = DatabaseConnection.getInstance();
        Connection con = dbCon.connect();
        RetrieveMode mode = RetrieveMode.JOURNAL_ENTRY;
        ArrayList<Data> listOfEntries = DatabaseApiSelect.getData(con, mode, date, personnummer);
        dbCon.disconnect();
        if (listOfEntries.isEmpty()) {
            returnValue = "No journal entries on this day";
        } else {
            for (Data dataEntry : listOfEntries) {
                JournalEntry entry = (JournalEntry) dataEntry;
                returnValue += entry.getEntry();
                returnValue += "\n";
            };
        }
        return returnValue;
    }
}
