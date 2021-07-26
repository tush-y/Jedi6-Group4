package com.flipkart.application;

import com.flipkart.bean.Admin;

import com.flipkart.bean.Professor;
import com.flipkart.bean.Student;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.exceptions.*;
import com.flipkart.input.Helper;
import com.flipkart.validator.Authentication;
import org.apache.log4j.Logger;

import java.util.ArrayList;

public class CRSApplication {

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {

        Logger logger = Logger.getLogger(CRSApplication.class);
        System.out.println("*********** Welcome to CRS! ************");
        System.out.println("*********** Select an Option ************");
        System.out.println("*********** 1. Login ********************");
        System.out.println("*********** 2. Signup Student ******************");
        System.out.println("*********** 3. Update Password **************");
        System.out.println("*********** 4. Exit *******************");

        Integer value = Helper.scanInt();
        if (value == null || value > 4) {
            System.out.println("Invalid Option");
        } else if (value == 1) {

            String id = Helper.scanString("id");
            String password = Helper.scanString("Password");
            try{
                User user = new Authentication().login(id, password);
                if (user == null) {
                    System.out.println("Wrong username or password");
                } else if (user instanceof Admin) {
                    AdminCRSMenu.menu();
                } else if (user instanceof Professor) {
                    ProfessorCRSMenu professorCRSMenu = new ProfessorCRSMenu((Professor) user);
                    professorCRSMenu.menu();
                } else if (user instanceof Student) {
                    StudentCRSMenu studentCRSMenu = new StudentCRSMenu((Student) user);
                    studentCRSMenu.menu();
                }
            } catch (UserNotApprovedException ex){
                logger.error(ex.getMessage());
            }
            catch (InvalidCredentialsException ex){
                logger.error(ex.getMessage());
            }

        } else if (value == 2) {

            String id = Helper.scanString("id");
            String name = Helper.scanString("Name");
            String password = Helper.scanString("Password");
            String branch = Helper.scanString("Branch");
            try {
                new Authentication().register(id , name , password , branch);
            }catch (UserAlreadyExistsException ex){
                logger.warn(ex.getMessage());
            }

        } else if (value == 3) {

            String id = Helper.scanString("id");
            String oldPass = Helper.scanString("Old Password");
            String newPass = Helper.scanString("New Password");
            String confirmNewPass = Helper.scanString("Confirm new Password");

            try{
                new Authentication().updatePassword(id , oldPass , newPass , confirmNewPass);
            }catch (AuthException | InvalidCredentialsException ex){
                logger.error(ex.getMessage());
            }
            catch (UserNotFoundException ex){
                logger.warn(ex.getMessage());
            }
        } else if (value == 4) {
            System.exit(0);
        }
        mainMenu();
    }
}
