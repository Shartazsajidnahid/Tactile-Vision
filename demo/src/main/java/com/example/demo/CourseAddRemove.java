package com.example.demo;

import LoginSignup.CourseUserremove;
import LoginSignup.DButils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.awt.*;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CourseAddRemove implements Initializable {
    @FXML
    private TextField removeCourseField;
    @FXML
    private TextField removeUserField;
    @FXML
    private TextField addCourseField;

    @FXML
    private TextField addCourseField1;

    @FXML
    private TextField addTeacherField;
    @FXML
    private TextField addCourseField2;

    @FXML
    private TextField addStudentField;

    private DButils dButils;
    private CourseUserremove courseUserremove;

    public void removeUser(ActionEvent actionEvent) throws SQLException {

        String name = removeUserField.getText();

        if (name.isEmpty()) {
            dButils.infoBox("Fill up the fields", "" , "");
            return;
        }
//        this.addUserUtil.addOtherUsers(name,password,email,phone, this.currentusertype);
//        dButils.infoBox("User added successfully", "" , "");
        if(courseUserremove.removeUser(name)){
            dButils.infoBox("User removed successfully", "" , "");
            addCourseField.clear();
        }
//
        removeUserField.clear();
    }

    public void removeCourse(ActionEvent actionEvent) throws SQLException {
        String coursename = removeCourseField.getText();
        if (coursename.isEmpty()) {
            dButils.infoBox("Fill up the fields", "" , "");
            return;
        }
        if(courseUserremove.removeCourse(coursename)){
            dButils.infoBox("Course removed successfully", "" , "");
            removeCourseField.clear();
        }
    }

    public void addCourse(ActionEvent actionEvent) throws SQLException {
        String coursename = addCourseField.getText();
        if (coursename.isEmpty()) {
            dButils.infoBox("Fill up the fields", "" , "");
            return;
        }
        if(courseUserremove.addCourse(coursename)){
            dButils.infoBox("Course added successfully", "" , "");
            addCourseField.clear();
        }


    }
    public void addSTudentToCourse(ActionEvent actionEvent) throws SQLException {
        int courseid = Integer.parseInt(addCourseField1.getText());
        int studentid = Integer.parseInt(addStudentField.getText());

//        if (courseid) {
//            dButils.infoBox("Fill up the fields", "" , "");
//            return;
//        }
        if(courseUserremove.addStudenttoCourse(studentid, courseid)){
            dButils.infoBox("Student added to Course added successfully", "" , "");
            addStudentField.clear();
            addCourseField1.clear();
        }
    }
    public void addTeacherToCourse(ActionEvent actionEvent) {
        int courseid = Integer.parseInt(addCourseField2.getText());
        int teacherid = Integer.parseInt(addTeacherField.getText());

//        if (courseid) {
//            dButils.infoBox("Fill up the fields", "" , "");
//            return;
//        }
        if(courseUserremove.addTeachertoCourse(teacherid, courseid)){
            dButils.infoBox("Teacher added to Course added successfully", "" , "");
            addStudentField.clear();
            addCourseField1.clear();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dButils = new DButils();
        this.courseUserremove = new CourseUserremove();
    }


}
