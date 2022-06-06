package LoginSignup;

import java.sql.*;
import java.util.HashMap;

public class DocumentDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tactilevision";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "KakashiSharingan9658";
    private static final String INSERT_QUERY = "INSERT INTO document(courseID,studentID,output) VALUES(?,?,?)";
    private static final String GET_DOC = "SELECT * FROM document where courseID = ?";
    private DButils dbutils;
    public DocumentDao(){
        dbutils = new DButils();
    }

    public void addDocument(int course, int student, String address) throws SQLException {
        
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {

            preparedStatement.setInt(1, course);
            preparedStatement.setInt(2, student);
            preparedStatement.setString(3, address);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
    }

    public HashMap<Integer,String> getDoclist(int courseid) {
        HashMap<Integer, String> doclist = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(GET_DOC)) {
//
            preparedStatement.setInt(1, courseid);
//

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                doclist.put(resultSet.getInt(1), resultSet.getString(4));
            }
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return doclist;
    }

}
