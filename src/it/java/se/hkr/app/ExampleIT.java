package se.hkr.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class ExampleIT {
    /*
    For integration tests a database is set up which is always in the same state at
    the start of the test run. It containes the sample data defined in 
    /sql/sample_data_insert.ddl which can be used to validate own method output.

    When doing integration tests against this containerized database, give the method
    DatabaseConnection.getInstance() the String "127.0.0.1:5000" as an argument.
    Then call the connect() function on the DatabaseConnnection object to build the
    connection to the database in the container and use this for whichever query
    you want to execute.
    
    Example code to build connection for integration tests:*/
    @Test
    public void tryConnectionToDocker() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance("127.0.0.1:5000");
        Connection con = dbCon.connect();
        String sql = """
                SELECT * 
                FROM Facts
                WHERE fact LIKE 'Cows have best friends.';
                """;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        String fact = "";
        while (rs.next()) {
            fact = rs.getString(1);
        }
        dbCon.disconnect();
        assertEquals("Cows have best friends.", fact);
        assertNotNull(con);
    }

    @Test
    public void tryConnectionToDockerUser() throws SQLException {
        DatabaseConnection dbCon = DatabaseConnection.getInstance("127.0.0.1:5000");
        Connection con = dbCon.connect();
        String sql = """
                SELECT * 
                FROM User
                WHERE personnummer LIKE '111111-1111';
                """;
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        String name = "";
        while (rs.next()) {
            name = rs.getString(2);
        }
        dbCon.disconnect();
        assertEquals("Test User 1", name);
        assertNotNull(con);
    }
}
