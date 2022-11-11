package model.encounter;

public class MedicationOrder {
    private String name;

    public MedicationOrder(String n) {
        this.name = n;
    }

    public String toString() {
        return this.name;
    }
}
