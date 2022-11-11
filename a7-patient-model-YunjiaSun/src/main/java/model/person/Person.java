package model.person;

import model.user.Role;

public class Person {
    private String name;
    private String dateOfBirth;
    private PersonDirectory directory;

    public Person(String n,String dob,PersonDirectory d,Role r){
        name = n;
        dateOfBirth = dob;
        directory = d;
    }

    public String getName(){
        return name;
    }

    public String getDateOfBirth(){
        return dateOfBirth;
    }

    public String toString() {
        return name;
    }
}
