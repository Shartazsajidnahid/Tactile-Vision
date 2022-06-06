package LoginSignup;

import java.sql.*;

import static java.lang.Integer.parseInt;
import static java.sql.DriverManager.getConnection;

public class CourseUserremove {
    DButils dbutils;
    public CourseUserremove(){
        this.dbutils = new DButils();
    }

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/tactilevision";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "KakashiSharingan9658";
    private static final String INSERT_QUERY_OTHERS_1 = "INSERT INTO ";
    private static final String DELETE_User_1 = "DELETE FROM ";
    private static final String DELETE_User_2 = " WHERE ID = ?";
    private static final String DELETE_Course = "DELETE FROM courses WHERE CourseName = ?";
    private static final String Add_Course = "INSERT INTO courses (CourseName) VALUES (?)";


    private static final String INSERT_QUERY_OTHERS_2 = "(ID,fullname,password,email,phone) VALUES (?, ?, ?,?,?)";
    private static final String INSERT_QUERY_STUDENT= "INSERT INTO student(ID,fullname,password,email,phone,roll,batch) VALUES (?, ?, ?,?,?,?,?)";
    private static final String RETRIEVE_QUERY = "select * from students";
    private static final String SELECT_QUERY = "SELECT * FROM students WHERE name = ? and password = ?";

    public boolean addCourse(String name) throws SQLException {
        System.out.println(name);
        String INSERT_QUERY;
        INSERT_QUERY = Add_Course;
        if(checkifAlreadyexists(name)){
            dbutils.infoBox("Course already exists", "", "");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QUERY)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return false;
    }
    public boolean addStudenttoCourse(int stdid,int courseid) throws SQLException {

        String query = "INSERT INTO takes (StudentID,CourseID) VALUES (?,?)";
        if(!checkifexistsbyID(stdid, "student" , "ID")){
            dbutils.infoBox("Student doesn't exist", "", "");
            return false;
        }
        if(!checkifexistsbyID(courseid, "courses" , "CourseID")){
            dbutils.infoBox("Course doesn't exist", "", "");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, stdid);
            preparedStatement.setInt(2, courseid);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return false;
    }
    public boolean addTeachertoCourse(int teacherid, int courseid) {
        String query = "INSERT INTO teaches (TeacherID,CourseID) VALUES (?,?)";
        if(!checkifexistsbyID(teacherid, "teacher" , "ID")){
            dbutils.infoBox("Teacher doesn't exist", "", "");
            return false;
        }
        if(!checkifexistsbyID(courseid, "courses" , "CourseID")){
            dbutils.infoBox("Course doesn't exist", "", "");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, teacherid);
            preparedStatement.setInt(2, courseid);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return false;
    }

    public boolean removeCourse(String name) throws SQLException {
        // Step 1: Establishing a Connection and
        // try-with-resource statement will auto close the connection.
        String Removequery;
        Removequery = DELETE_Course;
        if(!checkifAlreadyexists(name)){
            dbutils.infoBox("Course doesn't exist", "", "");
            return false;
        }

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(Removequery)) {
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return false;
    }
    public boolean removeUser(String name) throws SQLException {

        String parsedID = this.dbutils.parseID(name);
        int id = Integer.parseInt(parsedID);
        String parsedTable = this.dbutils.parseTable(name);
        System.out.println("ID: " + parsedID + "    Table: " + parsedTable);

        if(parsedTable == null) return false;


        String Removequery = DELETE_User_1 + parsedTable +  DELETE_User_2;

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(Removequery)) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return false;
    }


    public boolean checkifAlreadyexists(String coursename){
            String query = "select * from courses where CourseName = ?";

            try (Connection connection = getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, coursename);

                ResultSet resultSet = preparedStatement.executeQuery();
                System.out.println("hi");
                if (resultSet.next() == false) {
                    return false;
                } else return true;
            } catch (SQLException e) {
                // print SQL exception information
                this.dbutils.printSQLException(e);
            }
            return false;
        }
    public boolean checkifexistsbyID(int id,String table, String col){
        String query = "select * from " + table +  " where " + col + " = ?";

        try (Connection connection = getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next() == false) {
                return false;
            } else return true;
        } catch (SQLException e) {
            // print SQL exception information
            this.dbutils.printSQLException(e);
        }
        return false;
    }


}
