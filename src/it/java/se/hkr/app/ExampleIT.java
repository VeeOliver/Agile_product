package se.hkr.app;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class ExampleIT {
    /*@Test
    public void testIntegrationTest() {
        int res = 0;
        assertEquals(0, res);
    }
    @Test
    public void testIntegrationTest2() {
        int res = 0;
        assertEquals(0, res);
    }*/

    @Test
    public void tryConnectionToDocker() {
        //DatabaseConnection dbCon = DatabaseConnection.getInstance("127.0.0.1:5000");

        //assertEquals("jdbc:mariadb://localhost:5000/myrmidon?user=myrmidon_admin&password=myr_ADM123", dbCon.getDsn());
        Connection con = null;
        try {
            String url = "jdbc:mariadb://127.0.0.1:5000/myrmidon?user=myrmidon_admin&password=myr_ADM123";
            con = DriverManager.getConnection(url);
            System.out.println("success");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        assertNotNull(con);
    }
}
