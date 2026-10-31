/*
Name: Layla Jenkins
Date: 10/31/2026
Assignment: Java Lab Project: Part 4 - Week 4

This class will handle the database connecting to the application using JDBC. 
It will connect to a database or create one if it doesn't exist. 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {
    public static Connection connect(String database) {
        String url = "jdbc:sqlite:" + database;
        // Creates variable for connection and initializes it to null
        Connection conn = null;

        try {
            // If the database doesn't exist, it will be created. If it does exist, it will connect to it.
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
