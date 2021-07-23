package com.flipkart.application;

import com.flipkart.bean.Course;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.business.StudentOperation;
import com.flipkart.input.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StudentCRSMenu {

    public static void menu(User student){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("View Enrolled Course" , "Add Course" , "Drop Course" , "Register" ,"View Grade Card"));
        int count = 1;
        StudentOperation studentOperation = new StudentOperation();
        for(String value : list){
            System.out.println(count + ". " + value);
            ++count;
        }

        Integer value = Helper.scanInt();

        if(value==null || value > 4){
            System.out.println("Invalid Option");
            menu(student);
        }
        else if(value == 1){
                studentOperation.viewEnrolledCourses(student.getId());
        }
        else if(value==2){
            String courseCode = Helper.scanString("Course Code");
            studentOperation.addCourse(student.getId(),courseCode);
        }
        else if(value==3){
            String courseCode = Helper.scanString("Course Code");
            studentOperation.dropCourse(courseCode);
        }
        else if(value==4){
            studentOperation.register(student.getId());
        }
        else if(value==5){
            studentOperation.viewGradeCard(student.getId());
        }
    }

    public static void signUpMenu(ArrayList<User> list){
        Student student = new Student(null);
        student.setName(Helper.scanString("Name"));
        student.setId(Helper.scanString("id"));
        student.setPassword(Helper.scanString("Password"));
        student.setBranch(Helper.scanString("Branch"));
        System.out.println("Student Registered successfully");
        list.add(student);
        menu(student);
    }
}
