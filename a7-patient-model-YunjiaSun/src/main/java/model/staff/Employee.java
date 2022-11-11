package model.staff;

import model.person.Person;
import model.user.Role;

public class Employee {
    private Person person;
    private EmployeeDirectory employeeDirectory;
    private Role role;

    public Employee(Person p, EmployeeDirectory e, Role r){
        person = p;
        employeeDirectory = e;
        role = r;
    }

    public Person getPerson() {
        return person;
    }

    public Role getRole() {
        return role;
    }

    public String toString() {
        return this.role + ": " + this.person;
    }
}
