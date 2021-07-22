package com.flipkart.application;

import com.flipkart.input.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class StudentCRSMenu {

    public static void menu(){
        ArrayList<String> list = new ArrayList<>(Arrays.asList("View Enrolled Course" , "Add Course" , "Drop Course" , "View Grade Card"));
        int count = 1;
        for(String value : list){
            System.out.println(count + ". " + value);
            ++count;
        }

        Integer value = Helper.scanInt();

        if(value==null || value > 4){
            System.out.println("Invalid Option");
            menu();
        }
        else if(value == 1){
            System.out.println("View Enrolled Course called");
        }
        else if(value==2){
            System.out.println("Add course Called");
        }
        else if(value==3){
            System.out.println("Drop course Called");
        }
        else if(value==4){
            System.out.println("View Grade Card Called");
        }

    }
    public static void loginMenu(){

        while(true){
            System.out.println("1. Login");
            System.out.println("2. Signup");

            Integer value = Helper.scanInt();
            if(value==null || value > 2){
                System.out.println("Invalid Option");
                continue;
            }
            if(value==1){
                menu();
                return ;
            }
            if(value==2) {
                registerMenu();
                return;
            }

        }
    }
    public static void registerMenu(){

        String name = Helper.scanString("Name");
        String id = Helper.scanString("id");
        String password = Helper.scanString("Password");
        String branch = Helper.scanString("Branch");

        System.out.println("Registered");

        menu();

    }
}
