package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.constant.Grade;

import java.util.ArrayList;

public class ProfessorOperation implements ProfessorOperationInterface {

    Professor professor;
    public ProfessorOperation(){
        professor = new Professor();
    }
    @Override
    public void addGrades(String studentId, Grade value) {

    }

    @Override
    public void chooseCourse(String courseCode) {
        CourseCatalog catalog = CourseCatalog.getInstance();
        Course course = catalog.getCourse(courseCode);
        if (course!=null)
            professor.setCourses(course);
        else System.out.println(String.format("Course code %s not valid" , courseCode));
    }

    @Override
    public void viewEnrolledStudent(String courseCode) {

    }

    @Override
    public void viewCourses() {

        ArrayList<Course> enrolledCourses = professor.getCourses();
        System.out.println("============================================================");
        for(Course course : enrolledCourses){
            System.out.println(course.toString());
        }
        System.out.println("============================================================");
    }
    public void showAllCourses(){
        CourseCatalog catalog = CourseCatalog.getInstance();

        ArrayList<Course> allCourses = catalog.getAllCourses();
        System.out.println("============================================================");
        for(Course course : allCourses){
            System.out.println(course.toString());
        }
        System.out.println("============================================================");
    }
}
