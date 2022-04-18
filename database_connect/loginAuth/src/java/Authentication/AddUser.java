package com.example.javafxloginformjdbctutorial.Authentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddUser {

    DButils dbutils;
    public AddUser(){
        this.dbutils = new DButils();
    }

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tactilevision";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Kakashi";
    private static final String INSERT_QUERY = "INSERT INTO students(studentID,name,email,password) VALUES (?, ?, ?,?)";
    private static final String RETRIEVE_QUERY = "select * from students";
    private static final String SELECT_QUERY = "SELECT * FROM students WHERE name = ? and password = ?";

    public void addStudent(){

    }
    public void addUser(){

    }

    public void insertRecord(String fullName, String emailId, String password) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, 11);
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, emailId);
            preparedStatement.setString(4, password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
    }
}
