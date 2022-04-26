package se.hkr.app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@ExtendWith(MockitoExtension.class)
class DatabaseConnectionTest {
    @Mock
    Connection con;
    
    @BeforeEach
    public void init() {
        DatabaseConnection.instance = null;
    }

    @Test
    @DisplayName("Test if getInstance() without argument creates a new object if none exists.")
    void createInstanceWithoutArgumentCreatesDatabaseConnection() {
        var instance = DatabaseConnection.getInstance();
        assertNotNull(instance);
        assertInstanceOf(DatabaseConnection.class, instance);
    }

    @Test
    @DisplayName("Test if getInstance() with argument creates a new object if none exists.")
    void createInstanceWithArgumentCreatesDatabaseConnection() {
        var instance = DatabaseConnection.getInstance("example_host");
        assertNotNull(instance);
        assertInstanceOf(DatabaseConnection.class, instance);
    }

    @Test
    @DisplayName("Testing if singleton instantiation creates a single objet.")
    void testSingletonInstantiationWithoutParameter() {
        var instance = DatabaseConnection.getInstance();
        var exp = DatabaseConnection.getInstance();
        assertEquals(exp, instance);
    }

    @Test
    @DisplayName("Test whether instantiation with hostname creates a single object.")
    void testSingletonInstantiationWithHostParameter() {
        var exp = DatabaseConnection.getInstance("example_host");
        var instance = DatabaseConnection.getInstance();
        assertEquals(exp, instance);
    }

    @Test
    @DisplayName("Test if dsn of DatabaseConnection instance is correct with default host.")
    void testInstanceDsnWithoutArgument() {
        var instance = DatabaseConnection.getInstance();
        assertEquals("jdbc:mariadb://project-myrmidon.duckdns.org/myrmidon?user=myrmidon_admin" +
                "&password=myr_ADM123", instance.getDsn());
    }

    @Test
    @DisplayName("Test if dsn of DatabaseConnection instance is correct with specified host.")
    void testInstanceDsnWithArgument() {
        var instance = DatabaseConnection.getInstance("example_host");
        String expected = "jdbc:mariadb://example_host/myrmidon?user=myrmidon_admin" +
        "&password=myr_ADM123";
        String actual = instance.getDsn();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Test if connect() returns existing connection.")
    void testConnectWithExistingConnection() {
        var instance = DatabaseConnection.getInstance();
        instance.setCon(con);
        var actual = instance.connect();
        assertInstanceOf(Connection.class, actual);
        assertEquals(con, actual);
    }

    @Test
    @DisplayName("Test if connect() establishes new connection when none exists.")
    void testConnectWithoutExistingConnection() throws SQLException {
        var instance = DatabaseConnection.getInstance();
        try (MockedStatic<DriverManager> dm = Mockito.mockStatic(DriverManager.class)) {
            dm.when(() -> DriverManager.getConnection(anyString()))
                .thenReturn(con);
        var actual = instance.connect();
        assertEquals(con, actual);
        }
    }

    @Test
    @DisplayName("Test if connect returns null when an exception is thrown.")
    void testConnectThrowsExceptionReturnNull() {
        var instance = DatabaseConnection.getInstance();
        try (MockedStatic<DriverManager> dm = Mockito.mockStatic(DriverManager.class)) {
            dm.when(() -> DriverManager.getConnection(anyString()))
            .thenThrow(SQLException.class);
            var actual = instance.connect();
            assertNull(actual);
        }
    }

    @Test
    @DisplayName("Test if disconnect() closes existing connection and resets instance connection.")
    void testDisconnectWithConnectionClosesAndResetsConnection() throws SQLException {
        var instance = DatabaseConnection.getInstance();
        instance.setCon(con);
        instance.disconnect();
        Mockito.verify(con).close();
        var actual = instance.getCon();
        assertNull(actual);
    }

    @Test
    @DisplayName("Test if diconnect does nothing when connection is null.")
    void testDisconnectWithoutConnectionDoesNothing() {
        var instance = DatabaseConnection.getInstance();
        instance.setCon(con);
        instance.setCon(null);
        instance.disconnect();
        Mockito.verifyNoMoreInteractions(con);
        var actual = instance.getCon();
        assertNull(actual);

    }
}
