package com.example.javafxloginformjdbctutorial;

import java.sql.*;

public class JdbcDao {

    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tactilevision";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Kakashi";
    private static final String INSERT_QUERY = "INSERT INTO students(studentID,name,email,password) VALUES (?, ?, ?,?)";
    private static final String RETRIEVE_QUERY = "select * from students";
    private static final String SELECT_QUERY = "SELECT * FROM students WHERE email = ? and password = ?";


    public void insertRecord(String fullName, String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager
                .getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setInt(1, getlastid());
            preparedStatement.setString(2, fullName);
            preparedStatement.setString(3, emailId);
            preparedStatement.setString(4, password);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        retrive();
    }
    private static void retrive() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            String query = "select * from students";

            Statement sta = connection.createStatement();
            ResultSet x = sta.executeQuery(query);

            while(x.next()){
                System.out.println(x.getString(1)+" " + x.getString(2)+" " +x.getString(3));
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }
    private static int getlastid(){
        int id = -1;
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

            String query = "select * from students";

            Statement sta = connection.createStatement();
            ResultSet x = sta.executeQuery(query);
            while(x.next()){
                id = x.getInt(1);
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return id+1;
    }

    private static void del() {

        try(Connection conn = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
            Statement stmt = conn.createStatement();
        ) {
            String QUERY = "select * from students";
            String sql = "DELETE FROM students " +
                    "WHERE studentID = 786";
            stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(QUERY);
            while(rs.next()){
                //Display values
                System.out.print("ID: " + rs.getInt(1));
                System.out.print(", name: " + rs.getString(2));
                System.out.println(", email: " + rs.getString(3));
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean validate(String emailId, String password) throws SQLException {

        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY)) {
            preparedStatement.setString(1, emailId);
            preparedStatement.setString(2, password);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }


        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }
        return false;
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}