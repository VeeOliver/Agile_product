package se.hkr.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private final String userName = "myrmidon_admin";
    private final String password = "myr_ADM123";
    private final String databaseName = "myrmidon";
    private String host;
    private Connection con;
    public static DatabaseConnection instance;

    private DatabaseConnection() {
        this.host = "project-myrmidon.duckdns.org";
    }

    private DatabaseConnection(String host) {
        this.host = host;
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public static DatabaseConnection getInstance(String host) {
        if (instance == null) {
            instance = new DatabaseConnection(host);
        }
        return instance;
    }

    public Connection connect() {
        DatabaseConnection instance = getInstance();
        if (instance.getCon() == null) {
            try {
                instance.setCon(DriverManager.getConnection(instance.getDsn()));
            } catch (SQLException e) {
                System.out.println("An error occurred when connecting to the database.");
                return null;
            }
        }
        return instance.getCon();
    }

    public void disconnect() {
        DatabaseConnection instance = getInstance();
        if (instance.getCon() != null) {
            try {
                getInstance().con.close();               
                getInstance().setCon(null);
            } catch (SQLException e) {
                System.out.println("An error occurred when disconnecting from the database");
            }
        }
    }

    public Connection getCon() {
        return getInstance().con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public String getDsn() {
        DatabaseConnection instance = getInstance();
        String dsn = "jdbc:mariadb://" +
            instance.host +
            "/" +
            instance.databaseName +
            "?user=" + instance.userName +
            "&password=" + instance.password;
        return dsn;
    }
}
