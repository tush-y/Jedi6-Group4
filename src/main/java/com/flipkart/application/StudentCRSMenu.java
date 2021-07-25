package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.StudentOperation;
import com.flipkart.business.StudentOperationInterface;
import com.flipkart.input.Helper;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StudentCRSMenu {

    private StudentOperation studentOperation;
    private Student student;

    public StudentCRSMenu(Student student){
        this.student = student;
        studentOperation = new StudentOperation(student);
    }

    public void menu() {

        Logger logger = Logger.getLogger(StudentCRSMenu.class);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("View Enrolled Course" , "Add Course" , "Drop Course" , "Register" ,"View Grade Card","Pay Fees","Log Out"));
        int count = 1;
        System.out.println("============= Select from the below ===============");
        for(String value : list){
            System.out.println("*************  " + count + ". " + value + "  ****************");
            ++count;
        }

        Integer value = Helper.scanInt();

        if(value==null || value > 7){
            logger.info("Invalid Option");
            menu();
        }
        else if(value == 1){
            studentOperation.viewEnrolledCourses();
        }
        else if(value==2){
            String  courseCode = Helper.scanString("courseCode");
            studentOperation.addCourse(courseCode);
        }
        else if(value==3){
            String  courseCode = Helper.scanString("courseCode");
            studentOperation.dropCourse(courseCode);
        }
        else if(value==4){
            System.out.println("Register Courses");
        }

        else if(value==5){

            studentOperation.viewGradeCard();
        }
        else if(value==6){
            studentOperation.payFees();
        }
        else if(value==7){
            logger.info("Logged Out Successfully");
            return;
        }
        menu();
    }

    public static void signUpMenu(ArrayList<User> list) throws SQLException {
        Student student = new Student();
        student.setName(Helper.scanString("Name"));
        student.setId(Helper.scanString("id"));
        student.setPassword(Helper.scanString("Password"));
        student.setBranch(Helper.scanString("Branch"));
        System.out.println("Student Registered successfully");
        list.add(student);
        StudentCRSMenu student1=new StudentCRSMenu(student);
        student1.menu();
    }
}
