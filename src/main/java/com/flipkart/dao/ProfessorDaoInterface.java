package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.CourseNotTaughtException;
import com.flipkart.exceptions.GradesAlreadyGivenException;

import java.util.ArrayList;

public interface ProfessorDaoInterface {

    ArrayList<Course> getCourseByProf(String profId);
    ArrayList<ArrayList<String>> getEnrolledStudents(String profId , String courseCode) throws CourseNotTaughtException;
    void addGrades(String studentId , String courseCode , int grade) throws GradesAlreadyGivenException;
    void updateGrades(String studentId , String courseCode , String grade);
    void chooseCourse(String profId , String courseCode) throws CourseAlreadyRegisteredException;
}

/*

registerForCourse
 */
