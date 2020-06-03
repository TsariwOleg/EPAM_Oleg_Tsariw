package services.Entity;

public class HistoryOfRepair_Entity {
    private int repairedNo;
    private String malfunction;
    private int costOfRepair;
    private String note;
    private int bus_id;
    private String bus;
    private String NSP;//name ,surname , patronymic
    private int mechanic_id;

    public String getBus() {
        return bus;
    }

    public void setBus(String bus) {
        this.bus = bus;
    }

    public String getNSP() {
        return NSP;
    }

    public void setNSP(String NSP) {
        this.NSP = NSP;
    }

    public int getRepairedNo() {
        return repairedNo;
    }

    public void setRepairedNo(int repairedNo) {
        this.repairedNo = repairedNo;
    }

    public String getMalfunction() {
        return malfunction;
    }

    public void setMalfunction(String malfunction) {
        this.malfunction = malfunction;
    }

    public int getCostOfRepair() {
        return costOfRepair;
    }

    public void setCostOfRepair(int costOfRepair) {
        this.costOfRepair = costOfRepair;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getBus_id() {
        return bus_id;
    }

    public void setBus_id(int bus_id) {
        this.bus_id = bus_id;
    }

    public int getMechanic_id() {
        return mechanic_id;
    }

    public void setMechanic_id(int mechanic_id) {
        this.mechanic_id = mechanic_id;
    }

    @Override
    public String toString() {
        return "HistoryOfRepair_Entity{" +
                "repairedNo=" + repairedNo +
                ", malfunction='" + malfunction + '\'' +
                ", costOfRepair=" + costOfRepair +
                ", note='" + note + '\'' +
                ", bus_id=" + bus_id +
                ", bus='" + bus + '\'' +
                ", NSP='" + NSP + '\'' +
                ", mechanic_id=" + mechanic_id +
                '}';
    }
}
