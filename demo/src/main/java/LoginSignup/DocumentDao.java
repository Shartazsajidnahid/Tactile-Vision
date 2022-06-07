package LoginSignup;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DocumentDao {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tactilevision";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "KakashiSharingan9658";
    private static final String INSERT_QUERY = "INSERT INTO document(courseID,studentID,output) VALUES(?,?,?)";
    private static final String GET_DOC = "SELECT * FROM document where courseID = ?";

    private static final String Add_mark = "UPDATE document SET mark = ? WHERE ID = ?";
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

    public void addMark(int id, int mark) throws SQLException {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(Add_mark)) {

            preparedStatement.setInt(1, mark);
            preparedStatement.setInt(2, id);

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

    public HashMap<Integer, String> getCourses(int id, String usertype, String table) {
        HashMap<Integer, String> doclist = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("select * from "  +  table +" where "+usertype+"ID = ?")) {
//
            preparedStatement.setInt(1, id);
//

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int courseid = resultSet.getInt(2);
                String coursename =  getfromcourse(courseid);
                doclist.put(courseid,coursename);
            }
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return doclist;
    }

    public String getfromcourse(int courseid) {
        HashMap<Integer, String> doclist = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement("select * from Courses where CourseID = ?")) {

            preparedStatement.setInt(1, courseid);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
//                System.out.println(resultSet.getString(2));
//                 doclist.put(resultSet.getInt(1), resultSet.getString(2));
                return resultSet.getString(2);
            }
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return "";
    }
}

//    select * from "  +  table +" where "+table+"ID = ?
