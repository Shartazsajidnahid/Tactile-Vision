package LoginSignup;


import java.sql.*;
import java.util.HashMap;

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


    public String logIN(String id, String password) throws SQLException {
        String parsedID = this.dbutils.parseID(id);
        String parsedTable = this.dbutils.parseTable(id);
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
            this.dbutils.printSQLException(e);
        }
        return false;
    }

}