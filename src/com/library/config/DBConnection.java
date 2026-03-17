package com.library.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection
 * -------------
 * Handles connection to MySQL database.
 * Implemented using Singleton pattern.
 */
public class DBConnection {

    // Database URL
    private static final String URL = "jdbc:mysql://localhost:3306/library_db";

    // Database credentials
    private static final String USER = "root";
    private static final String PASSWORD = "root123";

    // Singleton instance
    private static DBConnection instance;

    // JDBC Connection
    private Connection connection;

    /**
     * Private constructor to prevent direct instantiation
     */
    private DBConnection() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Database Connected Successfully!");

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database Connection Failed.");
            e.printStackTrace();
        }
    }

    /**
     * Get Singleton Instance
     */
    public static DBConnection getInstance() {

        if (instance == null) {
            instance = new DBConnection();
        }

        return instance;
    }

    /**
     * Get Database Connection
     */
    public Connection getConnection() {
        return connection;
    }
}
