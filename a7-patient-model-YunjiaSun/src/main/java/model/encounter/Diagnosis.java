package model.encounter;

public class Diagnosis {
    boolean isFever;

    Diagnosis(VitalSigns vitalSigns) {
        check(vitalSigns);
    }

    private void check(VitalSigns vitalSigns) {
        isFever = checkIfFever(vitalSigns);
    }

    public boolean getIsFever() {
        return isFever;
    }

    private boolean checkIfFever(VitalSigns vitalSigns) {
        return vitalSigns.getTemp() > 99.5;
    }

    public String toString() {
        return isFever ? "This patient has fever!" : "This patient is healthy!";
    }
}
