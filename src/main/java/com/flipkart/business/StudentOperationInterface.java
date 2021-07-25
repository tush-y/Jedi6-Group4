package com.flipkart.business;

import com.flipkart.bean.Student;

import java.sql.SQLException;

public interface StudentOperationInterface {

    void viewEnrolledCourses();
    void addCourse(String courseCode) throws SQLException;
    void register();
    void dropCourse(String courseCode) throws SQLException;
    void viewGradeCard() throws SQLException;
    void payFees();
}
