package com.flipkart.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {

    private static Connection instance;

    private DBConnector(){

        try{
            instance = DriverManager.getConnection("jdbc:mysql://localhost:3306/crs","root","root1234");
            System.out.println("Connected!");

        }
        catch (SQLException ex){
            ex.printStackTrace();
            System.exit(0);
        }
    }

    public static Connection getInstance(){

        if(instance==null)
            new DBConnector();
        return instance;
    }
}
