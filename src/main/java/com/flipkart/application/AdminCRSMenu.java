package com.flipkart.application;

import com.flipkart.business.AdminOperation;
import com.flipkart.input.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import com.flipkart.bean.Course;
import com.flipkart.bean.Professor;
import java.util.*;
public class AdminCRSMenu {

    public static void menu(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Add Course" , "Remove Course" , "Add Professor" , "Approve Student"));

        int count = 1;
        for(String value : list){
            System.out.println(count + ". " + value);
            count++;
        }

        Integer value = Helper.scanInt();

        if(value==null || value > 4){
            System.out.println("Invalid Option");
            menu();
        }
        else if(value == 1){
            System.out.println("------------ADD COURSE CALLED------------");
            Course course=new Course();
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the course code:->");
            String course_code=sc.nextLine();
            course.setCourseCode(course_code);
            System.out.println("Enter the course name:->");
            String course_name=sc.nextLine();
            course.setCourseName(course_name);
            System.out.println("Enter the course description:->");
            String description=sc.nextLine();
            course.setDescription(description);
            System.out.println("Enter the seats:->");
            int seats=sc.nextInt();
            course.setSeats(seats);

            AdminOperation operation=new AdminOperation();
            operation.addCourse(course);
        }
        else if(value==2){
            System.out.println("------------REMOVE COURSE CALLED------------");

            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the course code:->");
            String course_code=sc.nextLine();
            AdminOperation operation=new AdminOperation();
            operation.removeCourse(course_code);
        }
        else if(value==3)
        {
            System.out.println("------------ADD PROFESSOR CALLED------------");
            Professor professor=new Professor();
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the professor Id:->");
            String professor_id=sc.nextLine();
            professor.setId(professor_id);
            System.out.println("Enter the professor Name:->");
            String professor_name=sc.nextLine();
            professor.setName(professor_name);
            System.out.println("Enter the professor designation:->");
            String professor_designation=sc.nextLine();
            professor.setDesignation(professor_designation);
            System.out.println("Enter the professor department:->");
            String professor_department=sc.nextLine();
            professor.setDepartment(professor_department);
            AdminOperation operation=new AdminOperation();
            operation.addProfessor(professor);
        }
        else if(value==4){
            System.out.println("------------APPROVE STUDENT CALLED------------");
        }
        System.out.println("Enter Y to continue , else press any other key");
        Scanner sc=new Scanner(System.in);
        char c=sc.next().charAt(0);
        if(c=='Y')
        {
            menu();
        }
    }

    public static void login(){

        String id = Helper.scanString("id");
        String password = Helper.scanString("Password");
        System.out.println("Login Success");
        menu();
    }

}

/*
Notes :
Make CRsMenu to Menu .
Create a separate User Interface for Admin .
*/
