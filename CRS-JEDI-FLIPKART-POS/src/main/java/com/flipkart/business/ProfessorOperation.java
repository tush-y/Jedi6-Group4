package com.flipkart.business;

import com.flipkart.bean.Course;
import com.flipkart.bean.CourseCatalog;
import com.flipkart.bean.Professor;
import com.flipkart.constant.Grade;
import com.flipkart.dao.CatalogDaoOperation;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.exceptions.CourseAlreadyRegisteredException;
import com.flipkart.exceptions.CourseNotTaughtException;
import com.flipkart.exceptions.GradesAlreadyGivenException;
import com.mysql.cj.log.Log;
import org.apache.log4j.Logger;
import sun.security.util.math.intpoly.P256OrderField;

import java.util.ArrayList;

/**
 * @author JEDI-06-group-4
 *Class for Professor Operations
 *
 */

public class ProfessorOperation implements ProfessorOperationInterface {

    Professor professor;
    private Logger logger = Logger.getLogger(ProfessorOperation.class);
    public ProfessorOperation(Professor professor){
        this.professor = professor;
    }

    /**
     * Method to Grade a student 
     * @param: studentId: student id
     * @param: courseCode: course code for the corresponding
     * @param grade: grade given to student in that course
     * @return: void
     */
    @Override
    public void addGrades(String studentId, String courseCode , int grade) {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        try {
            operation.addGrades(studentId , courseCode , grade);
        }catch (GradesAlreadyGivenException ex){
            logger.error(ex.getMessage());
        }
    }

    /**
     * Method to choose course which professor wants to teach 
     * @param: courseCode: course code for the corresponding
     * @return: void
     * @throws CourseAlreadyRegisteredException
     */
    @Override
    public void chooseCourse(String courseCode) throws CourseAlreadyRegisteredException {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        operation.chooseCourse(professor.getId() , courseCode);
    }

    /**
     * Method to view list of enrolled Students 
     * @param: courseCode: course code of the professor
     * @return: void
     * @throws CourseNotTaughtException
     */
    @Override
    public void viewEnrolledStudent(String courseCode) {

        ProfessorDaoOperation operation = new ProfessorDaoOperation();
        try {
            ArrayList<ArrayList<String>> data = operation.getEnrolledStudents(professor.getId() , courseCode);
            logger.trace("============================================================");
            data.forEach(info -> logger.info(info));
            logger.trace("============================================================");
        }
        catch (CourseNotTaughtException ex){
            logger.error(ex.getMessage());
        }
    }

    /**
     * Method to get Courses by Professor Id 
     * @return void
     */
    @Override
    public void viewCourses() {

        ArrayList<Course> enrolledCourses = new ProfessorDaoOperation().getCourseByProf(professor.getId());
        logger.trace("============================================================");
        for(Course course : enrolledCourses){
            logger.info(course.toString());
        }
        logger.trace("============================================================");

    }

    /**
     * Method to get all available courses 
     * @return void
     */
    public void showAllCourses(){

        ArrayList<Course> allCourses = new CatalogDaoOperation().getAllCourses();
        logger.trace("============================================================");
        allCourses.forEach(course -> logger.info(course.toString()));

        logger.trace("============================================================");
    }
}
