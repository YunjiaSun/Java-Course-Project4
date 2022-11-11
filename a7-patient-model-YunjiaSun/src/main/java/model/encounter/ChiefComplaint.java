package model.encounter;

import model.patient.PatientProfile;

public class ChiefComplaint {
    PatientProfile patient;
    String content;

    public ChiefComplaint(PatientProfile p, String c) {
        patient = p;
        content = c;
    }
}
