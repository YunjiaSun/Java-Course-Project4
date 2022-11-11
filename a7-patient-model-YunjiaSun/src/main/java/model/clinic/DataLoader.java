package model.clinic;

import java.util.Date;
import java.util.Random;

import com.github.javafaker.Faker;
import model.patient.PatientProfile;


public class DataLoader {
    private Faker magicBox;
    private Clinic clinic;
    private Random r;

    public DataLoader(Clinic c) {
        this.clinic = c;
        magicBox = new Faker();
        r = new Random();
    }

    public void populateClinic(int patientCount, int randomPatientNumber) {
        // creating some number of patient
        populatePatientDirectory(patientCount);
        populateEncounter(randomPatientNumber);
    }

    // Code below comes from the pricing model - you can use follow similar patterns for the clinic


    public void populatePatientDirectory(int count) {
        for (int i = 0; i < count; i++) {
            clinic.newPatient(magicBox.name().fullName(),"01/01/1990");
        }
    }

    public void populateEncounter(int count) {
        int patientCount = clinic.getPatientCount();
        for (int i = 0; i < count; i++) {
            int randomPick = r.nextInt(patientCount);
            System.out.println(randomPick);
            PatientProfile p = clinic.getPatientDirectory().getPatientByIndex(randomPick);
            p.newEncounter(new Date(2021, 10, i));
        }
    }
}

