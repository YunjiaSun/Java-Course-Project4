package model.person;

import model.clinic.Clinic;
import model.staff.Employee;
import model.user.Role;

import java.util.ArrayList;

public class PersonDirectory {
    private Clinic clinic;
    private ArrayList<Person> persons;

    public PersonDirectory(Clinic c){
        persons = new ArrayList<Person>();
        clinic = c;
    }

    public Person newPerson(String name, String dateOfBirth,Role role){
        Person p = new Person(name,dateOfBirth,this,role);
        persons.add(p);
        return p;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("================ Employee Info ================\n");
        for (Person p : persons){
            sb.append(p + "\n");
        }
        return sb.toString();
    }

}
