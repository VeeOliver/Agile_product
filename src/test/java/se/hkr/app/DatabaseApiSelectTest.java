package se.hkr.app;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseApiSelectTest {

    @Mock
    private Connection con;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    @Mock
    ResultSetMetaData rsmd;

    /*@Test
    @DisplayName("Test if getDataByDay returns a ResultSet after successful db access.")
    void getDataByDaySuccessfulReturnsResultSet() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        ResultSet res = DatabaseApiSelect.getDataByDay(con, "sql", LocalDate.now(), "12345678?1234");
        assertInstanceOf(ResultSet.class, res);
    }

    @Test
    @DisplayName("Test if getDataByDay returns null after failed database access")
    void getDataByDayFailsReturnsNull() {
        ResultSet res = DatabaseApiSelect.getDataByDay(null,"sql", LocalDate.now(), "12345678?1234");
        assertNull(res);
    }

    @Test
    @DisplayName("Test if getDataByTimePeriod returns a ResultSet after successful db access.")
    void getDataByTimePeriodSuccessfulReturnsResultSet() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        ResultSet res = DatabaseApiSelect.getDataByTimePeriod(con, "sql", LocalDate.now(), LocalDate.now(),
                "12345678?1234");
        assertInstanceOf(ResultSet.class, res);
    }

    @Test
    @DisplayName("Test if getDataByTimePeriod returns null after failed database access")
    void getDataByTimePeriodFailsReturnsNull() {
        ResultSet res = DatabaseApiSelect.getDataByTimePeriod(null,"sql", LocalDate.now(), LocalDate.now(),
                "12345678?1234");
        assertNull(res);
    }

    @Test
    @DisplayName("Test if getDataByRatingRange returns a ResultSet after successful db access.")
    void getDataByRatingRangeSuccessfulReturnsResultSet() throws SQLException {
        when(con.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);
        ResultSet res = DatabaseApiSelect.getDataByRatingRange(con, "sql", 2, 4,
                "12345678?1234");
        assertInstanceOf(ResultSet.class, res);
    }

    @Test
    @DisplayName("Test if getDataByRatingRange returns null after failed database access.")
    void getDataByRatingRangeFailsReturnsNull() {
        ResultSet res = DatabaseApiSelect.getDataByRatingRange(null,"sql", 2, 4,
                "12345678?1234");
        assertNull(res);
    }*/

    @Test
    @DisplayName("Test if parseToDataList returns an empty ArrayList in case of empty ResultSet.")
    void parseparseToDataListEmptyResultSet() throws SQLException {
        when(rs.next()).thenReturn(false);
        var res = DatabaseApiSelect.parseToDataList(rs);
        assertInstanceOf(ArrayList.class, res);
        assertNotNull(res);
        assertEquals(0, res.size());
    }

    @Test
    @DisplayName("Test if parseToDataList returns null in case of a SQLException.")
    void parseToDataListClosedResultSet() throws SQLException {
        when(rs.next()).thenThrow(SQLException.class);
        rs.close();
        var res = DatabaseApiSelect.parseToDataList(rs);
        assertNull(res);
    }

    @Test
    @DisplayName("Test if parseToDataList returns an ArrayList with two correct Journal entries.")
    void parseToDataListReturnsJournalEntryList() throws SQLException {
        LocalDate date1 = LocalDate.of(2022, 01, 01);
        String daytime1 = "Morning";
        String entry1 = "Entry 1";
        LocalDate date2 = LocalDate.of(2022, 01, 02);
        String daytime2 = "Noon";
        String entry2 = "Entry 2";
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getMetaData()).thenReturn(rsmd);
        when(rsmd.getColumnCount()).thenReturn(3);
        when(rs.getString(1)).thenReturn(daytime1, daytime2);
        when(rs.getString(2)).thenReturn(entry1, entry2);
        when(rs.getString(3)).thenReturn("2022-01-01", "2022-01-02");
        var res = DatabaseApiSelect.parseToDataList(rs);
        assertInstanceOf(ArrayList.class, res);
        assertEquals(2, res.size());
        assertInstanceOf(JournalEntry.class, res.get(0));
        JournalEntry journalEntry1 = (JournalEntry) res.get(0);
        JournalEntry journalEntry2 = (JournalEntry) res.get(1);
        assertEquals(daytime1, journalEntry1.getDaytime());
        assertEquals(date1, journalEntry1.getDate());
        assertEquals(entry1, journalEntry1.getEntry());
        assertEquals(daytime2, journalEntry2.getDaytime());
        assertEquals(date2, journalEntry2.getDate());
        assertEquals(entry2, journalEntry2.getEntry());
    }

    @Test
    @DisplayName("Test if parseToDataList returns an ArrayList with two correct MoodTension entries.")
    void parseToDataListReturnsMoodTensionList() throws SQLException {
        LocalDate date1 = LocalDate.of(2022, 01, 01);
        String daytime1 = "Morning";
        int mood1 = 5;
        int tension1 = 6;
        LocalDate date2 = LocalDate.of(2022, 01, 02);
        String daytime2 = "Noon";
        int mood2 = 2;
        int tension2 = 8;
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getMetaData()).thenReturn(rsmd);
        when(rsmd.getColumnCount()).thenReturn(4);
        when(rs.getString(1)).thenReturn("Morning", "Noon");
        when(rs.getDouble(2)).thenReturn(5.0, 2.0);
        when(rs.getDouble(3)).thenReturn(6.0, 8.0);
        when(rs.getString(4)).thenReturn("2022-01-01", "2022-01-02");
        var res = DatabaseApiSelect.parseToDataList(rs);
        assertInstanceOf(ArrayList.class, res);
        assertEquals(2, res.size());
        assertInstanceOf(MoodTension.class, res.get(0));
        MoodTension moodTension1 = (MoodTension) res.get(0);
        MoodTension moodTension2 = (MoodTension) res.get(1);
        assertEquals(date1, moodTension1.getDate());
        assertEquals(daytime1, moodTension1.getDaytime());
        assertEquals(mood1, moodTension1.getMood());
        assertEquals(tension1, moodTension1.getTension());
        assertEquals(date2, moodTension2.getDate());
        assertEquals(daytime2, moodTension2.getDaytime());
        assertEquals(mood2, moodTension2.getMood());
        assertEquals(tension2, moodTension2.getTension());
    }
}
