package com.examly.springapp.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {
    private static final String URL = "jdbc:sqlite:library.db"; // SQLite DB
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            // Load the SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Establish the connection only once (optional - singleton-style)
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL);
                System.out.println("✅ Connected to SQLite database.");
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ SQLite JDBC driver not found. Please add the driver to your project.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("❌ Connection failed.");
            e.printStackTrace();
        }
        return connection;
    }
}
