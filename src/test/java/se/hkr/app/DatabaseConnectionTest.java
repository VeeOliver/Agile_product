package se.hkr.app;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    @Test
    @DisplayName("Testing if singleton instantiation creates a single objet.")
    void testSingletonInstantiationCreatesSingleObject() {
        var instance = DatabaseConnection.getInstance();
        var exp = DatabaseConnection.getInstance();
        assertEquals(exp, instance);
    }

    @Test
    @DisplayName("Testing if the dsn of DatabaseConnection is correct.")
    void testInstanceDsn() {
        var instance = DatabaseConnection.getInstance();
        assertEquals("jdbc:mysql://localhost/myrmidon?user=myrmidon_admin" +
                "&password=myr_ADM123", instance.getDsn());
    }

    @Disabled("Disabled until database is integrated.")
    @Test
    @DisplayName("Test successful database connection and disconnecting.")
    void testConnectAndDisconnect() {
        assertInstanceOf(Connection.class, DatabaseConnection.getInstance().connect());
        assertNotNull(DatabaseConnection.getInstance().getCon());
        DatabaseConnection.getInstance().disconnect();
        assertNull(DatabaseConnection.getInstance().getCon());
    }
}
