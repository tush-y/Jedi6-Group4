package com.flipkart.application;

import com.flipkart.bean.Admin;
import com.flipkart.bean.User;
import com.flipkart.constant.Role;
import com.flipkart.input.Helper;

import java.util.ArrayList;

public class CRSApplication {

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu(){

        System.out.println("*********** Welcome to CRS! ************");
        System.out.println("*********** Choose any option************");
        System.out.println("*********** 1. Login ********************");
        System.out.println("*********** 2. Student Sign Up ******************");
        System.out.println("*********** 3. Update Password ****************");
        System.out.println("*********** 4. Exit ****************");

        ArrayList<User> list = new ArrayList<>();
        list.add(new Admin("001","admin","pass",null,Role.ADMIN));

        Integer value = Helper.scanInt();
        if(value==null){
            System.out.println("Invalid Option");
            mainMenu();
        }
        else if(value==1)
            login(list);
        else if(value==2)
            StudentCRSMenu.signUpMenu(list);
        else if(value==3)
            updatePassword(list);
        else if(value==4)
            System.exit(0);
    }

    public static void login(ArrayList<User> list) {
        String id = Helper.scanString("id");
        String password = Helper.scanString("Password");
        int flag=0;
        for(User i: list)
        {
            if(i.getId().equals(id) && i.getPassword().equals(password)) {
                System.out.println("Login Success");
                if(i.getRole()==Role.ADMIN)
                {
                    //Call adminCRS menu
                }
                else if(i.getRole()==Role.STUDENT)
                {
                    StudentCRSMenu.menu(i);
                }
                else if(i.getRole()==Role.PROF)
                {
                    //Call ProfCRS menu
                }
                flag=1;
                break;
            }
        }
        if(flag==0) {
            System.out.println("Login failed. Enter Again.");
            mainMenu();
        }
    }

    public static void updatePassword(ArrayList<User> list) {
        String id = Helper.scanString("id");
        String oldPassword = Helper.scanString("Old Password");
        int flag=0;
        for(User i: list)
        {
            if(i.getId().equals(id) && i.getPassword().equals(oldPassword)) {
                String newPassword = Helper.scanString("New Password");
                i.setPassword(newPassword);
                flag=1;
                break;
            }
        }
        if(flag==0) {
            System.out.println("User Not Found");
            mainMenu();
        }
    }

}
