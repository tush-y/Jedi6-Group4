package com.flipkart.business;

import com.flipkart.constant.Grade;

public interface ProfessorOperationInterface {

    public void addGrades(String studentId , Grade value);
    public void chooseCourse(String courseCode);
    public void viewEnrolledStudent(String courseCode);
    public void viewCourses();
}
