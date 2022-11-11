package model.patient;

import java.util.ArrayList;

import model.clinic.Clinic;
import model.person.Person;

public class PatientDirectory {
    private Clinic clinic;
    private ArrayList<PatientProfile> patients;

    public PatientDirectory(Clinic c){
        clinic = c;
        patients = new ArrayList<PatientProfile>();
    }

    public PatientProfile newPatient(Person person){
        PatientProfile p = new PatientProfile(this, person);
        patients.add(p);
        return p;
    }

    public PatientProfile getPatient(String name, String dob) {
        for (PatientProfile p : patients) {
            Person person = p.getPerson();
            if (person.getName().equals(name)
                    && person.getDateOfBirth().equals(dob)) {
                return p;
            }
        }
        return null;
    }

    public int getPatientCount(){
        return patients.size();
    }

    public PatientProfile getPatientByIndex(int index){
        return patients.get(index);

    }
}
