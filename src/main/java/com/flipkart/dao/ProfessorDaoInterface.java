package com.flipkart.dao;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.GradesAlreadyGivenException;

import java.util.ArrayList;

public interface ProfessorDaoInterface {

    ArrayList<Course> getCourseByProf(String profId);
    ArrayList<Student> getEnrolledStudents(String profId);
    void addGrades(String studentId , String courseCode , String grade) throws GradesAlreadyGivenException;
    void chooseCourse(String profId , String courseCode) throws CourseAlreadyRegisteredException;
}

/*

registerForCourse
 */
