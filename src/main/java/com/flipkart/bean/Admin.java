package com.flipkart.bean;

import com.flipkart.constant.Role;

public class Admin extends User {

    public Admin(){
    }

    public Admin(String id , String name , String password , Role role){
        super(id , name , password , role);
    }
}
