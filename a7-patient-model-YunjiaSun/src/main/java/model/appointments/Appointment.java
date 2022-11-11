package model.appointments;

import model.patient.PatientProfile;
import model.staff.Employee;
import model.user.Role;

import java.util.Date;

public class Appointment {
    private PatientProfile patient;
    private Employee doctor;
    private Date date;
    private AppointmentStatus status;

    public Appointment(PatientProfile p, Employee e, Date d, AppointmentStatus s) {
        patient = p;
        doctor = e;
        date = d;
        status = s;
    }

    public void setComplete() {
        this.setStatus(AppointmentStatus.Complete);
    }

    public PatientProfile getPatient() {
        return patient;
    }

    public Employee getDoctor() {
        return doctor;
    }

    public void setDoctor(Employee doctor) {
        this.doctor = doctor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus appointmentStatus) {
        this.status = appointmentStatus;
    }

    public String toString() {
        return "Appointment: " + this.getDoctor() + "|" + this.getPatient() + " at date " + this.getDate();
    }

}
