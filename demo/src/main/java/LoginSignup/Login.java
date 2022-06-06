package LoginSignup;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.sql.DriverManager.getConnection;

public class Login {

    DButils dbutils;
    public Login(){
        this.dbutils = new DButils();
    }
    // Replace below database url, username and password with your actual database credentials
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tactilevision";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "KakashiSharingan9658";
    private static final String RETRIEVE_QUERY = "select * from admin";
    private static final String SELECT_QUERY1 = "SELECT * FROM ";
    private static final String SELECT_QUERY12= " WHERE ID = ? and password = ?";

    private HashMap<String, String> tableMap;


    public List<String> logIN(String id, String password) throws SQLException {
        String parsedID = this.dbutils.parseID(id);
        String parsedTable = this.dbutils.parseTable(id);
        List<String> userDetails = new ArrayList<>();
        System.out.println("ID: " + parsedID + "    Table: " + parsedTable);

        if(parsedTable == null) return userDetails;
        userDetails.add(parsedTable);
        if(validate(parsedID,parsedTable, password)){
            List<String> userinfo = getInfo(parsedID,parsedTable,password);
            System.out.println("userDetails");
            for(String x : userinfo){
                userDetails.add(x);
            }
        }
        return userDetails;
    }

    private boolean validate(String id, String tablename, String password) throws SQLException {

        try (Connection connection = getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY1 + tablename + SELECT_QUERY12)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
//            System.out.println("hi");
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            this.dbutils.printSQLException(e);
        }
        return false;
    }

    private List<String> getInfo(String id, String tablename, String password) throws SQLException {
        List<String> userinfo = new ArrayList<>();
        try (Connection connection = getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QUERY1 + tablename + SELECT_QUERY12)) {
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                userinfo.add(resultSet.getString(2));
//                userinfo.add(resultSet.getString(3));
                userinfo.add(resultSet.getString(4));
                userinfo.add(resultSet.getString(5));
                return userinfo;
            }
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return userinfo;
    }

}