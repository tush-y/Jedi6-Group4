package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.input.Helper;

import java.util.ArrayList;
import java.util.Arrays;

public class ProfessorCRSMenu {

    private static ProfessorOperation professorOperation = new ProfessorOperation();
    public static void menu(){

        ArrayList<String> list = new ArrayList<>(Arrays.asList("Add Grades" , "Choose Course" , "View Enrolled Student" , "View Courses"  , "List All courses" , "Exit"));

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
            System.out.println("Add Grades called");
        }
        else if(value==2){
            String  courseCode = Helper.scanString("courseCode");
            professorOperation.chooseCourse(courseCode);
        }
        else if(value==3){
            System.out.println("View Enrolled Student Called");
        }
        else if(value==4){
            professorOperation.viewCourses();
        }

        else if(value==5){
            professorOperation.showAllCourses();
        }
        else if(value==6){
            System.out.println("Exiting");
            System.exit(0);
        }
        menu();
    }
    public static void login(){

        String id = Helper.scanString("id");
        String password = Helper.scanString("Password");
        System.out.println("Login Success");
        menu();
    }
}
