package com.flipkart.application;

import com.flipkart.input.Helper;

import java.util.ArrayList;
import java.util.Arrays;

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
            System.out.println("Add Course called");
        }
        else if(value==2){
            System.out.println("Remove Course called");
        }
        else if(value==3){
            System.out.println("Add Professor Called");
        }
        else if(value==4){
            System.out.println("Approve Student Called");
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
