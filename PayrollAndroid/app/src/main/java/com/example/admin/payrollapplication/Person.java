package com.example.admin.payrollapplication;

public class Person {
    String id;
    String name;
    String password;

    Person(){

    }

    public Person(String id,String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getId() {
        return id;
    }
}
