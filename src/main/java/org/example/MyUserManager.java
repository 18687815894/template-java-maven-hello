package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyUserManager{
    private static final String DB_URL = "jdbc:sqlite:users.db";
    public String getDB(){return DB_URL;}

    public boolean registerUser(String username,String password){
        try(Connection connection = DriverManager.getConnection(DB_URL);
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Users (username,password) VALUES(?,?)")){
            statement.setString(1,username);
            statement.setString(2,password);
            statement.executeUpdate();
            System.out.println("User registered successfully!");

            return true;
        } catch (SQLException e){
            System.out.println("Failed to register user:"+e.getMessage());
        }
        return false;
    }

    public boolean login(String username,String password){
        try(Connection connection = DriverManager.getConnection(DB_URL);
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users WHERE username = ?")) {
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();

             if(resultSet.next()){
                String storedPassword = resultSet.getString("password");
                if(password.equals(storedPassword)){
                    return true;
                }else
                System.out.println("INcorrect password.");
             }
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Failed to login:"+e.getMessage());
        }

        return false;
    }
}