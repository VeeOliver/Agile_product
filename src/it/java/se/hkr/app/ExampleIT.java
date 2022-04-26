package se.hkr.app;


import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testIntegrationTestFalse() {
        int res = 1;
        assertEquals(0, res);
    }
}
