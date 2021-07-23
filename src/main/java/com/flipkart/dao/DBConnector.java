package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    private static Connection instance;

    private DBConnector(){

        try{
            instance = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/crs?user=brijesh1in");
            System.out.println("Connected!");

        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getInstance(){

        new DBConnector();
        return instance;
    }
}
