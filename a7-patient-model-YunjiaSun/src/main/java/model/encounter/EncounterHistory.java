package model.encounter;

import model.patient.PatientProfile;

import java.util.ArrayList;
import java.util.Date;

public class EncounterHistory {
    private PatientProfile patient;
    private ArrayList<Encounter> encounterList;
    // Should contain

    public EncounterHistory(PatientProfile p){
        patient = p;
        encounterList = new ArrayList<Encounter>();
    }

    public void printPreviousHistory(Encounter encounter) {
        ArrayList<Encounter> result = new ArrayList<>();
        for (Encounter e : encounterList) {
            if (e != encounter) result.add(e);
        }

        StringBuffer sb = new StringBuffer();
        for (Encounter e : result){
            sb.append(e + "\n");
        }

        if (sb.length() == 0) {
            System.out.println("This patient has no previous visit!");
            return;
        }
        System.out.println("================ Encounter History ================\n");
        System.out.println(sb);
    }


    public ArrayList<VitalSigns> getVitalSignsList(){
        ArrayList<VitalSigns> vitalSignsList = new ArrayList<>();
        for(Encounter e: encounterList){
            if(e.vitalSigns != null) {
                vitalSignsList.add(e.vitalSigns);
            }
        }
        return vitalSignsList;
    }

    public ArrayList<Diagnosis> getDiagnosisList(){
        ArrayList<Diagnosis> diagnosisList = new ArrayList<>();
        for(Encounter e: encounterList){
            if(e.diagnosis != null) {
                diagnosisList.add(e.diagnosis);
            }
        }
        return diagnosisList;
    }

    public ArrayList<MedicationOrder> getMedicationOrderList(){
        ArrayList<MedicationOrder> medicationOrderList = new ArrayList<>();
        for(Encounter e: encounterList){
            if(e.medicationOrder != null){
                medicationOrderList.add((e.medicationOrder));
            }
        }
        return medicationOrderList;
    }


    public PatientProfile getPatient() {
        return patient;
    }

    public Encounter newEncounter(PatientProfile p, Date date){
        this.patient = p;
        Encounter encounter = new Encounter(p, this, date);
        encounterList.add(encounter);
        return encounter;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("================ Encounter Info ================\n");
        for (Encounter e : encounterList){
            sb.append(e + "\n");
        }
        return sb.toString();
    }
}
