package ui;

import model.appointments.Appointment;
import model.appointments.AppointmentStatus;
import model.clinic.Clinic;
import model.encounter.Diagnosis;
import model.encounter.Encounter;
import model.encounter.EncounterHistory;
import model.encounter.MedicationOrder;
import model.patient.PatientDirectory;
import model.patient.PatientProfile;
import model.person.Person;
import model.person.PersonDirectory;
import model.staff.Employee;
import model.staff.EmployeeDirectory;
import model.user.Role;
import model.user.User;

import java.util.Date;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class PatientExampleMain {
    public static void patientScheduleUI(Clinic uhcs) {
        System.out.println("Welcome to the Schedule Services!");

        Scanner scanner = new Scanner(System.in);

        PatientProfile patient = null;

        while (patient == null) {
            System.out.println("Please input your name: ");
            String name = scanner.nextLine();
            System.out.println("Please input your DOB: ");
            String dob = scanner.nextLine();
            patient = uhcs.getPatientDirectory().getPatient(name, dob);
            if (patient == null)
                patient = uhcs.getPatientDirectory().newPatient(uhcs.getPersonDirectory().newPerson(name, dob, Role.Patient));
        }

        System.out.println("Please input your intend doctor name: ");
        String doctorName = scanner.nextLine();
        Employee doctor = uhcs.getEmployeeDirectory().findDoctorByName(doctorName);
        while (doctor == null) {
            System.out.println("Please input a valid doctor name: ");
            doctorName = scanner.nextLine();
            doctor = uhcs.getEmployeeDirectory().findDoctorByName(doctorName);
        }

        Appointment upcomingAppointment = new Appointment(patient, doctor, new Date(), AppointmentStatus.Scheduled);
        uhcs.getMasterAppointmentsList().addAppointment(upcomingAppointment);

        System.out.println("Appointment schedule successful with info: " + upcomingAppointment);
    }

    public static Encounter patientRegisterUI(Clinic uhcs) {
        System.out.println("Welcome to the Register service!");

        Scanner scanner = new Scanner(System.in);

        PatientProfile patient = null;

        while (patient == null) {
            System.out.println("Please input your name: ");
            String name = scanner.nextLine();
            System.out.println("Please input your DOB: ");
            String dob = scanner.nextLine();
            patient = uhcs.getPatientDirectory().getPatient(name, dob);
            if (patient == null)
                System.out.println("Cannot find patient based on name and DOB! Please re-input: ");
        }

        Appointment appointment = uhcs.getMasterAppointmentsList().getLastScheduledAppointmentsForPatient(patient);

        if (appointment == null) {
            System.out.println("This patient doesn't have scheduled appointment. Bye!");
            return null;
        }

        System.out.println("You have a registration record, please complete the follow-up medical examination！");

        Encounter encounter = patient.newEncounter(new Date());
        encounter.setAppointment(appointment);
        encounter.setDoctor(appointment.getDoctor());
        return encounter;

    }

    public static Encounter nurseUI(Clinic uhcs, Encounter encounter) {

        if (encounter == null) return null;
        System.out.println("Welcome to the nurse service!");

        Employee nurse = uhcs.getEmployeeDirectory().getNurse();
        encounter.setNurse(nurse);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please input patient weight: ");
        double weight = scanner.nextDouble();
        System.out.println("Please input patient height: ");
        double height = scanner.nextDouble();
        System.out.println("Please input patient temperature: ");
        double temperature = scanner.nextDouble();
        encounter.newVitalSigns(weight, height, temperature);
        System.out.println("Please input patient complaint: ");
        String complaint = scanner.next();
        encounter.newComplaint(complaint);

        System.out.println("Completed extraction of patient vital signs and chief complaint records!" );

        return encounter;
    }

    public static void doctorUI(Clinic uhcs, Encounter encounter) {
        if (encounter == null) return;
        System.out.println("Welcome to the doctor service!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Patient name: " + encounter.getPatient());
        System.out.println("Patient visit history: ");
        encounter.printPreviousEncounterHistory();
        Diagnosis diagnosis = encounter.newDiagnosis();
        System.out.println(diagnosis);

        System.out.println("Order lab test?");
        String isOrderL = scanner.nextLine();
        if (isOrderL.equalsIgnoreCase("yes") || isOrderL.equalsIgnoreCase("y")) {
            encounter.orderLabTest();
            System.out.println("Lab test ordered!");
        }

        System.out.println("Input suggested treatment");
        String suggestedTreatment = scanner.nextLine();
        encounter.treatment(suggestedTreatment);

        System.out.println("Order medication?");
        String isOrderM = scanner.nextLine();
        if (isOrderM.equalsIgnoreCase("yes") || isOrderM.equalsIgnoreCase("y")) {
            System.out.println("Please input medication name: ");
            String medicationName = scanner.nextLine();
            encounter.newMedicationOrder(medicationName);
            System.out.println("Medication: " + medicationName + " ordered!");
        }

        System.out.println("Schedule follow up? ");
        String isSchedule = scanner.nextLine();

        if (isSchedule.equalsIgnoreCase("yes") || isSchedule.equalsIgnoreCase("y")) {
            Appointment followUp = encounter.scheduleFollowUp();
            uhcs.getMasterAppointmentsList().addAppointment(followUp);
            System.out.println("Appointment schedule successful with info: " + followUp);
        }

        encounter.completeAppointment();
    }

    public static void main( String[] args ) {
        // Northeastern Health and Consoling Services
        Clinic uhcs = new Clinic("Northeastern Health and Consoling Services.");

        User userDoctor1 = new User("Dr.Martin", Role.Doctor);
        User userDoctor2 = new User("Dr.Yan", Role.Doctor);

        //creat an object for PD and build link between Clinic and PD
        PersonDirectory pd = uhcs.getPersonDirectory();

        Person PatientLily = uhcs.getPersonDirectory().newPerson("Lily","01/01/1994",Role.Patient);
        Person PatientAnna = uhcs.getPersonDirectory().newPerson("Anna","06/01/1990",Role.Patient);

        Person DoctorMartin = uhcs.getPersonDirectory().newPerson("Dr.Martin","05/01/1980",Role.Doctor);
        Person DoctorSummer = uhcs.getPersonDirectory().newPerson("Dr.Summer","09/02/1970",Role.Doctor);

        Person NurseTom = uhcs.getPersonDirectory().newPerson("Tom","02/11/1992",Role.Nurse);
        Person NurseLucy = uhcs.getPersonDirectory().newPerson("Lucy","06/06/1990",Role.Nurse);

        PatientDirectory patientd = uhcs.getPatientDirectory();

        PatientProfile patientAnna = patientd.newPatient(PatientAnna);
        PatientProfile patientLily = patientd.newPatient(PatientLily);

        EmployeeDirectory employeeDir = uhcs.getEmployeeDirectory();

        Employee doctorMartin = employeeDir.newEmployee(DoctorMartin, Role.Doctor);
        Employee doctorSummer = employeeDir.newEmployee(DoctorSummer, Role.Doctor);
        Employee nurseTom = employeeDir.newEmployee(NurseTom, Role.Nurse);
        Employee nurseLucy = employeeDir.newEmployee(NurseLucy, Role.Nurse);

        System.out.println(employeeDir);

        //creat patient's encounter history
        EncounterHistory patientAnnaEH = new EncounterHistory(patientAnna);

        Encounter patientAnnaFirstEncounter = patientAnnaEH.newEncounter(patientAnna, new Date(2021, 10, 1));
        Encounter patientAnnaSecondEncounter = patientAnnaEH.newEncounter(patientAnna, new Date(2021, 11, 1));

        patientAnnaFirstEncounter.newVitalSigns(150,6,101);
        patientAnnaSecondEncounter.newVitalSigns(140,6,99);

        Diagnosis annaDiagnosis1 = patientAnnaFirstEncounter.newDiagnosis();
        Diagnosis annaDiagnosis2 = patientAnnaSecondEncounter.newDiagnosis();
        MedicationOrder annaMD1 = patientAnnaFirstEncounter.newMedicationOrder("Tylenol");

        System.out.println(patientAnnaEH);

        //Schedule upcoming appointment for the patient
        Appointment upcomingAppointment = new Appointment(patientLily, doctorMartin, new Date(), AppointmentStatus.Scheduled);
        uhcs.getMasterAppointmentsList().addAppointment(upcomingAppointment);
        System.out.println(uhcs.getMasterAppointmentsList());

        //Patient/Nurse/Doctor UI
        patientScheduleUI(uhcs);
        Encounter encounter = patientRegisterUI(uhcs);
        nurseUI(uhcs, encounter);
        doctorUI(uhcs, encounter);
    }
}
