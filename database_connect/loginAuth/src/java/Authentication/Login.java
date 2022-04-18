package com.example.javafxloginformjdbctutorial.Authentication;

import java.sql.*;
import java.util.HashMap;

import static java.sql.DriverManager.getConnection;

public class Login{

    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tactilevision";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "Kakashi";
    private static final String RETRIEVE_QUERY = "select * from students";
    private static final String SELECT_QUERY1 = "SELECT * FROM ";
    private static final String SELECT_QUERY12= " WHERE ID = ? and password = ?";

    private HashMap<String, String> tableMap;
    public Login(){
        tableMap= new HashMap<>();
        tableMap.put("std","student");
        tableMap.put("adm","admin");
        tableMap.put("tea","teacher");
        tableMap.put("stf","staff");
    }

    private String parseID(String unparsedID){
        return new String(unparsedID.substring(4,unparsedID.length()));
    }

    private String parseTable (String unparsedID){
            return tableMap.get(new String(unparsedID.substring(0,3)));
    }

    public String logIN(String id, String password) throws SQLException {
        String parsedID = parseID(id);
        String parsedTable = parseTable(id);
        System.out.println("ID: " + parsedID + "    Table: " + parsedTable);

        if(parsedTable == null) return "invalid";

        if(validate(parsedID,parsedTable, password)){
            return parsedTable;
        }
        return "invalid";
    }


    private boolean validate(String id, String tablename, String password) throws SQLException {


        try (Connection connection = getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);


             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY1 + tablename + SELECT_QUERY12)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("hi");
            if (resultSet.next()) {
                System.out.println(resultSet.getString(4));
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