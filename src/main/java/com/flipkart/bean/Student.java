package com.flipkart.bean;

public class Student extends User {

    private String branch;

    Student(){

    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
