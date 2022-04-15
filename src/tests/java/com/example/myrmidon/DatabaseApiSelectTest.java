package application;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DatabaseApiSelectTest {

    @Mock
    private Connection con;

    @Mock
    private PreparedStatement stmt;

    @Mock
    private ResultSet rs;

    @Test
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
    }

    @Test
    @DisplayName("Test if parseDataToTuples returns an empty ArrayList in case of empty ResultSet.")
    void parseDataToTuplesEmptyResultSet() throws SQLException {
        when(rs.next()).thenReturn(false);
        var res = DatabaseApiSelect.parseDataToTuples(rs);
        assertInstanceOf(ArrayList.class, res);
        assert res != null;
        assertEquals(0, res.size());
    }

    @Test
    @DisplayName("Test if parseDataToTuples returns null in case of a SQLException.")
    void parseDataToTuplesClosedResultSet() throws SQLException {
        when(rs.next()).thenThrow(SQLException.class);
        rs.close();
        var res = DatabaseApiSelect.parseDataToTuples(rs);
        assertNull(res);
    }

    @Test
    @DisplayName("Test if parseDataToTuples returns an ArrayList of Triplets with correct values.")
    void parseDataToTuplesWithTwoRows() throws SQLException {
        when(rs.next()).thenReturn(true, true, false);
        when(rs.getString(1)).thenReturn("Morning", "Noon");
        when(rs.getObject(2)).thenReturn(2, "Journal entry");
        when(rs.getString(3)).thenReturn("2022-01-01", "2022-01-02");
        var res = DatabaseApiSelect.parseDataToTuples(rs);
        assert res != null;
        assertEquals("Morning", res.get(0).getValue0());
        assertEquals(2,res.get(0).getValue1());
        assertEquals(LocalDate.of(2022,1,1), res.get(0).getValue2());
        assertEquals("Noon", res.get(1).getValue0());
        assertEquals("Journal entry", res.get(1).getValue1());
        assertEquals(LocalDate.of(2022, 1, 2), res.get(1).getValue2());
        assertEquals(2, res.size());
    }
}
