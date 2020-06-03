package services.Entity;

public class CarMechanics_Entity {
    private int id;
    private String NSP;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNSP() {
        return NSP;
    }

    public void setNSP(String NSP) {
        this.NSP = NSP;
    }

    @Override
    public String toString() {
        return "CarMechanics_Entity{" +
                "id=" + id +
                ", NSP='" + NSP + '\'' +
                '}';
    }
}
