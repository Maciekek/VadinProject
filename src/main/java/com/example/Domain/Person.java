package com.example.Domain;

/**
 * Created by Maciek on 2015-03-17.
 */
public class Person {
    String name = "";
    String userName = "";
    String lastName = "";
    String password = "";

    public Person(String name, String userName, String lastName, String password) {
        this.name = name;
        this.userName = userName;
        this.lastName = lastName;
        this.password = password;
    }

    public Person() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
