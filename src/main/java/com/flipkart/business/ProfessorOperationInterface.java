package com.flipkart.business;

import com.flipkart.constant.Grade;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;

public interface ProfessorOperationInterface {

    public void addGrades(String studentId , String courseCode , int grade);
    public void chooseCourse(String courseCode) throws CourseAlreadyRegisteredException;
    public void viewEnrolledStudent(String courseCode);
    public void viewCourses();
}
