package model.clinic;

import model.appointments.MasterAppointmentsList;
import model.patient.PatientDirectory;
import model.patient.PatientProfile;
import model.person.Person;
import model.person.PersonDirectory;
import model.staff.EmployeeDirectory;
import model.user.Role;
import model.user.UserDirectory;

public class Clinic {
    private String name;
    private MasterAppointmentsList alst;
    private PatientDirectory patientDirectory;
    private UserDirectory userDirectory;
    private PersonDirectory personDirectory;
    private EmployeeDirectory employeeDirectory;


    public Clinic(String name){
        this.name = name;
        this.alst = new MasterAppointmentsList(this);
        patientDirectory = new PatientDirectory(this);
        userDirectory = new UserDirectory(this);
        personDirectory = new PersonDirectory(this);
        employeeDirectory = new EmployeeDirectory(this);
    }

    public MasterAppointmentsList getMasterAppointmentsList() {
        return alst;
    }

    public PatientProfile newPatient(String name,String dateOfBirth){
        Person person = personDirectory.newPerson(name,dateOfBirth,Role.Patient);
        PatientProfile patient = patientDirectory.newPatient(person);
        return patient;
    }

    public int getPatientCount(){
        return patientDirectory.getPatientCount();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setPatientDirectory(PatientDirectory patientDirectory) {
        this.patientDirectory = patientDirectory;
    }

    public PatientDirectory getPatientDirectory(){
        return patientDirectory;
    }

    public void setPersonDirectory(PersonDirectory personDirectory){
        this.personDirectory = personDirectory;
    }

    public PersonDirectory getPersonDirectory(){
        return personDirectory;
    }

    public void setUserDirectory(UserDirectory userDirectory) {
        this.userDirectory = userDirectory;
    }

    public UserDirectory getUserDirectory(){
        return userDirectory;
    }

    public void setEmployeeDirectory(EmployeeDirectory employeeDirectory){
        this.employeeDirectory = employeeDirectory;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeeDirectory;
    }
}
