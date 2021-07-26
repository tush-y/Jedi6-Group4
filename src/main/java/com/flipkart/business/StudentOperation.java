package com.flipkart.business;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.StudentGrade;
import com.flipkart.dao.CatalogDaoInterface;
import com.flipkart.dao.CatalogDaoOperation;
import com.flipkart.dao.ProfessorDaoOperation;
import com.flipkart.dao.StudentDaoOperation;
import com.flipkart.exceptions.RegistrationNotCompleteException;
import com.flipkart.input.Helper;
import org.apache.log4j.Logger;
import sun.jvm.hotspot.memory.HeapBlock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentOperation implements StudentOperationInterface {
    Student student;
    private static Logger logger = Logger.getLogger(StudentOperation.class);

    public StudentOperation(Student student){
        this.student = student;
    }

    @Override
    public void viewEnrolledCourses() {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.viewEnrolledCourses(student.getId());
    }

    @Override
    public void addCourse(String courseCode) {
        StudentDaoOperation operation=new StudentDaoOperation();
        try {
            operation.addCourse(student.getId() , courseCode);
        } catch (SQLException ex){
            ex.printStackTrace();
        }catch (RegistrationNotCompleteException ex){
            logger.warn(ex.getMessage());
        }
    }

    @Override
    public void register() {

        final int minCourses = 4;
        CatalogDaoOperation operation = new CatalogDaoOperation();
        ArrayList<String> courses = new ArrayList<>();
        for(int i = 0 ; i < 4 ; ++i){
            String courseId = Helper.scanString("Course " + Integer.toString(i));
            courses.add(courseId);
        }
        StudentDaoOperation ops = new StudentDaoOperation();
        for(String course : courses){
            ops.addSingleCourse(this.student.getId() , course);
        }
        logger.info("Registration for courses successful");
    }

    @Override
    public void dropCourse(String courseCode) {
        StudentDaoOperation operation=new StudentDaoOperation();

        try {
            operation.dropCourse(student.getId() , courseCode);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void viewGradeCard() {
        StudentDaoOperation operation=new StudentDaoOperation();
        List<StudentGrade> gradeCard = new ArrayList<>();

        try {
            gradeCard = operation.viewGradeCard(student.getId());
        } catch (SQLException ex){
            ex.printStackTrace();
        }

        if(gradeCard.isEmpty())
        {
            return;
        }
        logger.info(String.format("%-10s %-10s","COURSE CODE", "GRADE"));

        for(StudentGrade obj : gradeCard)
        {
            logger.info(String.format("%-10s %-10s",obj.getCourseCode(),obj.getGrade()));
        }
    }

    @Override
    public void payFees() {
        StudentDaoOperation operation=new StudentDaoOperation();
        operation.payFees(student.getId());
    }
}
