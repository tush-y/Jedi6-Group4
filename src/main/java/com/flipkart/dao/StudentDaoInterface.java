package com.flipkart.dao;

import com.flipkart.bean.StudentGrade;
import com.flipkart.exceptions.RegistrationNotCompleteException;

import java.sql.SQLException;
import java.util.List;

public interface StudentDaoInterface {

    void viewEnrolledCourses(String studentId);
    void addCourse(String studentId, String courseCode) throws SQLException , RegistrationNotCompleteException;
    List<StudentGrade> viewGradeCard(String studentId) throws SQLException;
    void dropCourse(String studentId, String courseCode) throws SQLException;
    void payFees(String studentId);

}
