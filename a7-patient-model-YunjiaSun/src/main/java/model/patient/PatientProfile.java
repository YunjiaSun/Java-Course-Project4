package model.patient;

import model.appointments.Appointment;
import model.appointments.AppointmentStatus;
import model.encounter.Encounter;
import model.encounter.EncounterHistory;
import model.person.Person;

import java.util.Date;

public class PatientProfile {
    private PatientDirectory directory;
    private Person person;
    private EncounterHistory history;

    public PatientProfile(PatientDirectory directory,Person person){
        this.directory = directory;
        this.person = person;
        history = new EncounterHistory(this);
    }

    public Person getPerson() {
        return person;
    }

    public Encounter newEncounter(Date date){
        Encounter e = history.newEncounter(this, date);
        return e;
    }

    public String toString() {
        return "Patient: " + this.person;
    }
}
