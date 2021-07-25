package com.flipkart.application;

import com.flipkart.bean.Professor;
import com.flipkart.business.ProfessorOperation;
import com.flipkart.input.Helper;
import com.flipkart.validator.Authentication;
import org.apache.log4j.Logger;
import sun.security.util.math.intpoly.P256OrderField;

import java.util.ArrayList;
import java.util.Arrays;


public class ProfessorCRSMenu {

    private ProfessorOperation professorOperation;
    private Professor professor;
    public ProfessorCRSMenu(Professor professor){
        this.professor = professor;
        professorOperation = new ProfessorOperation(professor);

    }
    public void menu(){

        Logger logger = Logger.getLogger(ProfessorCRSMenu.class);
        ArrayList<String> list = new ArrayList<>(Arrays.asList("Add Grades" , "Choose Course" , "View Enrolled Student" , "View Courses"  , "List All courses" , "Logout"));

        int count = 1;
        System.out.println("============= Select from the below ===============");
        for(String value : list){
            System.out.println("*************  " + count + ". " + value + "  ****************");
            ++count;
        }

        Integer value = Helper.scanInt();

        if(value==null || value > 6){
            logger.info("Invalid Option");
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
            logger.info("Logged out successfully");
            return;
        }
        menu();
    }
}
