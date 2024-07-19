package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

public class DatabaseInitializer {
    private static final String DB_URL = "jdbc:sqlite:users.db";
    public void initializeDatabase(){
        try (Connection connection = DriverManager.getConnection(DB_URL);
        Statement statement = connection.createStatement())
        {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS Users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT,password TEXT,leve INT)";
            statement.executeUpdate(createTableQuery);
            String creategoodsTable = "CREATE TABLE IF NOT EXISTS Goods (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT,num INT,price INT)";
            statement.executeUpdate(creategoodsTable);
            System.out.println("Database initialized successfully!");
        } catch (SQLException e){
            System.out.println("Failed to initialize database:"+e.getMessage());
        }
    }
}