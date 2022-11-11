package model.appointments;

import java.util.ArrayList;

import model.clinic.Clinic;
import model.patient.PatientProfile;
import model.staff.Employee;

public class MasterAppointmentsList {
    private Clinic clinic;
    private ArrayList<Appointment> appointments;

    public MasterAppointmentsList(Clinic c) {
        clinic = c;
        appointments = new ArrayList<>();
    }

    public void addAppointment(Appointment ap) {
        this.appointments.add(ap);
    }

    public ArrayList<Appointment> getScheduledAppointmentsForDoctor(Employee doc){
        ArrayList<Appointment> result = new ArrayList<Appointment>();

        for (Appointment a : appointments){
            if (a.getDoctor().equals(doc) && a.getStatus().equals(AppointmentStatus.Scheduled))
                result.add(a);
        }

        return result;
    }

    public Appointment getLastScheduledAppointmentsForPatient(PatientProfile p){
        for (int i = appointments.size() - 1; i >= 0; i--){
            Appointment a = appointments.get(i);
            if (a.getPatient().equals(p) && a.getStatus().equals(AppointmentStatus.Scheduled))
                return a;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("================ Master Appointment Info ================\n");
        for (Appointment a : appointments){
            sb.append(a + "\n");
        }
        return sb.toString();
    }
}
