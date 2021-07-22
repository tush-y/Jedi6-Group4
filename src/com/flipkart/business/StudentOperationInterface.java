package com.flipkart.business;

import com.flipkart.bean.Student;

public interface StudentOperationInterface {

    void viewEnrolledCourses(String studentId);
    void addCourse(String studentId , String courseCode);
    void register(Student student);
    void login(String id , String password);
    void dropCourse(String courseCode);
    void viewGradeCard(String studentId);
}
