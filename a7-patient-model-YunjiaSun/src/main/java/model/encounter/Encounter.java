package model.encounter;

import model.appointments.Appointment;
import model.appointments.AppointmentStatus;
import model.patient.PatientProfile;
import model.staff.Employee;

import java.util.Date;

public class Encounter {
    EncounterHistory history;
    Date visitDate;
    PatientProfile patient;
    Employee nurse;
    Employee doctor;
    Appointment appointment;
    Appointment followUp;
    ChiefComplaint chiefComplaint;
    Diagnosis diagnosis;
    MedicationOrder medicationOrder;
    VitalSigns vitalSigns;
    String treatment;

    public Encounter(PatientProfile p, EncounterHistory h, Date vd){
        patient = p;
        history = h;
        visitDate = vd;
    }

    public void setNurse(Employee n) {
        this.nurse = n;
    }

    public void setDoctor(Employee d) {
        this.doctor = d;
    }

    public Employee getDoctor() {
        return doctor;
    }

    public void setAppointment(Appointment a) {
        appointment = a;
    }

    public void completeAppointment() {
        appointment.setComplete();
    }

    public void newVitalSigns(double weight, double height, double temp){
        vitalSigns = new VitalSigns(weight, height, temp);
    }

    public void newComplaint(String content){
        chiefComplaint = new ChiefComplaint(this.patient, content);
    }

    public Diagnosis newDiagnosis(){
        diagnosis = new Diagnosis(vitalSigns);
        return diagnosis;
    }

    public MedicationOrder newMedicationOrder(String medicationName){
        medicationOrder = new MedicationOrder(medicationName);
        return medicationOrder;
    }

    public VitalSigns getVitalSigns(){
        return vitalSigns;
    }

    public Appointment scheduleFollowUp(){
        return new Appointment(this.getPatient(), this.getDoctor(), new Date(), AppointmentStatus.Scheduled);
    }

    public PatientProfile getPatient(){
        return patient;
    }

    public EncounterHistory getEncounterHistory(){
        return history;
    }

    public void orderLabTest() {
        // order lab test
    }

    public void treatment(String treatment) {
        this.treatment = treatment;
        System.out.println("You suggested to: " + treatment);
    }

    public void printPreviousEncounterHistory(){
        history.printPreviousHistory(this);
    }

    public String toString() {
        return "---------------------\n" +
                "Visit Date: " + this.visitDate + "\n" +
                "Vital Signs: " + this.getVitalSigns() + "\n" +
                "Diagnosis: isPatientGetFever: " + (this.diagnosis == null ? "" : this.diagnosis.isFever) + "\n" +
                "Medication Order: " + this.medicationOrder + "\n";
    }
}
