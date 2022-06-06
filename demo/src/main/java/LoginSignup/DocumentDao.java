package LoginSignup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocumentDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tactilevision";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "KakashiSharingan9658";
    private static final String INSERT_QUERY = "INSERT INTO document(courseID,studentID,output) VALUES(?,?,?)";
    private DButils dbutils;

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
}
