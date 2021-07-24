package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.business.StudentOperation;
import com.flipkart.input.Helper;

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
    public void menu(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("View Enrolled Course" , "Add Course" , "Drop Course" , "Register" ,"View Grade Card"));
        int count = 1;
        System.out.println("============= Select from the below ===============");
        for(String value : list){
            System.out.println("*************  " + count + ". " + value + "  ****************");
            ++count;
        }

        Integer value = Helper.scanInt();

        if(value==null || value > 6){
            System.out.println("Invalid Option");
            menu();
        }
        else if(value == 1){
            System.out.println("View Enrolled Course Called");
            String  studentId = Helper.scanString("studentId");
            studentOperation.viewEnrolledCourses(studentId);

        }
        else if(value==2){
            System.out.println("Add Course Called");
            String  studentId = Helper.scanString("studentId");
            String  courseCode = Helper.scanString("courseCode");
            studentOperation.addCourse(studentId,courseCode);



        }
        else if(value==3){
            System.out.println("Drop Course Called");
            String  studentId = Helper.scanString("studentId");
            String  courseCode = Helper.scanString("courseCode");
            studentOperation.dropCourse(studentId,courseCode);

        }
        else if(value==4){
            System.out.println("Register Courses");

        }

        else if(value==5){
            System.out.println("View Grade Card");
        }
        else if(value==6){
            System.out.println("Logged Out Successfully");
            return;
        }
        menu();
    }

    public static void signUpMenu(ArrayList<User> list){
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
