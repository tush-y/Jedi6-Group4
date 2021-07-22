package com.flipkart.application;

import com.flipkart.input.Helper;

public class CRSApplication {

    public static void main(String[] args) {

        CRSApplication application = new CRSApplication();
        application.mainMenu();
    }

    public void mainMenu(){

        System.out.println("*********** Welcome to CRS! ************");
        System.out.println("*********** 1. Admin ********************");
        System.out.println("*********** 2. Student ******************");
        System.out.println("*********** 3. Professor ****************");

        Integer value = Helper.scanInt();
        if(value==null){
            System.out.println("Invalid Option");
            mainMenu();
        }
        else if(value==1)
            AdminCRSMenu.login();
        else if(value==2)
            StudentCRSMenu.loginMenu();
        else if(value==3)
            ProfessorCRSMenu.login();
    }

}
