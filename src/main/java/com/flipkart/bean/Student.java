package com.flipkart.bean;

import com.flipkart.constant.Role;

public class Student extends User {

    private String branch;

    public Student(){

    }

    public Student(String id , String name , String password , Role role){
        super(id , name , password , role);
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
