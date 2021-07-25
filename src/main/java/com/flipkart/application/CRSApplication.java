package com.flipkart.application;

import com.flipkart.bean.Admin;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.input.Helper;
import com.flipkart.validator.Authentication;
import com.mysql.cj.log.Log;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class CRSApplication {

    public static void main(String[] args) {
        Logger logger = Logger.getLogger(CRSApplication.class);
        logger.info("Application Started");
        mainMenu();
    }

    public static void mainMenu(){

        Logger logger = Logger.getLogger(CRSApplication.class);
        System.out.println("*********** Welcome to CRS! ************");
        System.out.println("*********** Select an Option ************");
        System.out.println("*********** 1. Login ********************");
        System.out.println("*********** 2. Signup Student ******************");
        System.out.println("*********** 3. Update Password **************");
        System.out.println("*********** 4. Exit *******************");

        Integer value = Helper.scanInt();
        if(value==null || value > 4){
            System.out.println("Invalid Option");
            mainMenu();
        }
        else if(value==1){

            String id = Helper.scanString("id");
            String password = Helper.scanString("Password");
            User user = new Authentication().login(id , password);
            if(user == null){
                System.out.println("Wrong username or password");
            }
            else if(user instanceof Admin){
                AdminCRSMenu.menu();
            }
            else if(user instanceof Professor){
                ProfessorCRSMenu professorCRSMenu = new ProfessorCRSMenu((Professor)user);
                professorCRSMenu.menu();
            }
            else if(user instanceof Student) {
                System.out.println("student logged in");
                StudentCRSMenu studentCRSMenu = new StudentCRSMenu((Student)user);
                studentCRSMenu.menu();
            }

        }
        else if(value==2){

            String name = Helper.scanString("Name");
            String id = Helper.scanString("id");
            String password = Helper.scanString("Password");
            String branch = Helper.scanString("Branch");

        }
        else if(value==3){
            String userId = Helper.scanString("id");
            String oldPass = Helper.scanString("Old Password");
            String newPass = Helper.scanString("New Password");
            String newPass2 = Helper.scanString("New Password Again");
            if(!newPass.equals(newPass2)){
                logger.warn("New Passwords don't match .");
            }

            User user = new Authentication().login(userId , oldPass);
            if(user == null){
                logger.warn("Wrong userId or Password");
            }
        }

        else if(value==4){
            System.exit(0);
        }
    }

}
