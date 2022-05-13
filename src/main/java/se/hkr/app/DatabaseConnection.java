package se.hkr.app;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public final class DatabaseConnection {
    /**
     * String: Username for database access.
     */
    private final String userName = "myrmidon_admin";
    /**
     * String: Password for database access.
     */
    private final String password = "myr_ADM123";
    /**
     * String: Database name for database access.
     */
    private final String databaseName = "myrmidon";
    /**
     * String: Hostname for database access.
     */
    private String host;
    /**
     * Connection: Singleton instance for connection to database.
     */
    private Connection con;
    /**
     * DatabaseConnection: Singleton class instance.
     */
    private static DatabaseConnection instance;

    /**
     * Instance contructor with default host.
     */
    private DatabaseConnection() {
        this.host = "project-myrmidon.duckdns.org";
    }

    /**
     * Instance contructor with custom host.
     * @param customHost
     */
    private DatabaseConnection(final String customHost) {
        this.host = customHost;
    }

    /**
     * Instantiate singleton instance with default host if necessary.
     * @return Singleton instance with default host
     */
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    /**
     * Instantiate singleton instance with custom host if necessary.
     * @param host
     * @return Singleton instance with custom host
     */
    public static DatabaseConnection getInstance(final String host) {
        if (instance == null) {
            instance = new DatabaseConnection(host);
        }
        return instance;
    }

    /**
     * Connect to remote database with default settings.
     * @return Connection to remote database
     * @throws SQLException
     */
    public Connection connect() throws SQLException {
        getInstance();
        if (instance.getCon() == null) {
            instance.setCon(DriverManager.getConnection(instance.getDsn()));
        }
        return instance.getCon();
    }

    /**
     * Close database connection and reset filed con to null.
     * @throws SQLException
     */
    public void disconnect() throws SQLException {
        getInstance();
        if (instance.getCon() != null) {
            getInstance().con.close();
            getInstance().setCon(null);
        }
    }

    /**
     * Getter for field con.
     * @return Connection con
     */
    public Connection getCon() {
        return getInstance().con;
    }

    /**
     * Setter for field con.
     * @param newCon
     */
    public void setCon(final Connection newCon) {
        this.con = newCon;
    }

    /**
     * Create a dsn from connection details.
     * @return String: Dsn for database connection
     */
    public String getDsn() {
        DatabaseConnection.getInstance();
        String dsn = "jdbc:mariadb://"
            + instance.host
            + "/"
            + instance.databaseName
            + "?user=" + instance.userName
            + "&password=" + instance.password;
        return dsn;
    }

    /**
     * Reset the singleton class instance.
     */
    public static void resetInstance() {
        DatabaseConnection.instance = null;
    }
}
