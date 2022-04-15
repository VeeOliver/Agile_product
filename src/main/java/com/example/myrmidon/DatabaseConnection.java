package com.example.myrmidon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private final String userName = "myrmidon_admin";
    private final String password = "myr_ADM123";
    private final String databaseName = "myrmidon";
    private final String dsn = "jdbc:mysql://localhost/" +
            databaseName +
            "?user=" + userName +
            "&password=" + password;
    private Connection con;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection connect() {
        DatabaseConnection instance = getInstance();
        try {
            instance.con = DriverManager.getConnection(dsn);
        } catch (SQLException e) {
            System.out.println("An error occurred when connecting to the database.");
        }
        return instance.con;
    }

    public void disconnect() {
        try {
            getInstance().con.close();
        } catch (SQLException e) {
            System.out.println("An error occurred when disconnecting from the database");
        }
        getInstance().con = null;
    }

    public Connection getCon() {
        return getInstance().con;
    }

    public String getDsn() {
        return getInstance().dsn;
    }
}
