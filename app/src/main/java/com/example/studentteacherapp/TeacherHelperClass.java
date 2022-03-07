package com.example.studentteacherapp;

public class TeacherHelperClass {
    String name,address,email,contact,password;


    public TeacherHelperClass() {
    }



    public TeacherHelperClass(String name, String address, String email, String contact,String password) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.email = email;
        this.password = password;
        //this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public String getClasses() {
//        return classes;
//    }
//
//    public void setClasses(String classes) {
//        this.classes = classes;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
