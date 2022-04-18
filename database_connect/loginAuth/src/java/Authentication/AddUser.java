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
    private static final String INSERT_QUERY_OTHERS_1 = "INSERT INTO ";
    private static final String INSERT_QUERY_OTHERS_2 = "(ID,fullname,password,email,phone) VALUES (?, ?, ?,?,?)";
    private static final String INSERT_QUERY_STUDENT= "INSERT INTO student(ID,fullname,password,email,phone,roll,batch) VALUES (?, ?, ?,?,?,?,?)";
    private static final String RETRIEVE_QUERY = "select * from students";
    private static final String SELECT_QUERY = "SELECT * FROM students WHERE name = ? and password = ?";

    public void addStudent(String name, String password, String email, String phone, String roll, String batch) throws SQLException {
        insertRecord(name,email,password,phone,roll,batch,"student");
    }
    public void addOtherUsers(String name, String password, String email, String phone, String type) throws SQLException {
        insertRecord(name,email,password,phone,"","",type);
    }

    public void insertRecord(String fullName, String emailId, String password,String phone,String roll, String batch,String type) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        String INSERT_QUERY;
        if(type=="student"){
            INSERT_QUERY = INSERT_QUERY_STUDENT;
        }
        else{
            INSERT_QUERY= INSERT_QUERY_OTHERS_1 + type + INSERT_QUERY_OTHERS_2;
        }
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, "1");
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, emailId);
            preparedStatement.setString(5, phone);

            if(type == "student"){
                preparedStatement.setString(6,roll);
                preparedStatement.setString(7,batch);
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
    }



}
