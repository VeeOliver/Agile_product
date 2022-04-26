package se.hkr.app;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

public class ExampleIT {
    @Test
    public void testIntegrationTest() {
        int res = 0;
        assertEquals(0, res);
    }
    @Test
    public void testIntegrationTest2() {
        int res = 0;
        assertEquals(0, res);
    }

    @Test
    public void tryConnectionToDocker() {
        Connection con = DatabaseConnection.getInstance("0.0.0.0:10000").connect();
        
    }
}
