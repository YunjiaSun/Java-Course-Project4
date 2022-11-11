package model.staff;

import model.clinic.Clinic;
import model.person.Person;
import model.user.Role;


import java.util.ArrayList;

public class EmployeeDirectory {
    private Clinic clinic;
    private ArrayList<Employee> employees;

    public EmployeeDirectory(Clinic c){
        employees = new ArrayList<Employee>();
        clinic = c;
    }

    public Employee newEmployee(Person p,  Role role){
        Employee e = new Employee(p,this, role);
        employees.add(e);
        return e;
    }

    public Employee findDoctorByName(String candidate) {
        for (Employee e : employees) {
            if (e.getPerson().getName().equals(candidate)) {
                return e;
            }
        }
        return null;
    }

    public Employee getNurse() {
        for (Employee e : employees) {
            if (e.getRole() == Role.Nurse) {
                return e;
            }
        }
        return null;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("================ Employee Info ================\n");
        for (Employee e : employees){
            sb.append(e + "\n");
        }
        return sb.toString();
    }










    //String str = new String ("Stanford  ");
    //str += "Lost!!";



}
