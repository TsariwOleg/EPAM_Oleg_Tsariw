package services.Entity;

public class CheckUp_Entity {
    private int id;
    private int idPerson;
    private String NSP;//name surname patronymic
    private String pressure;
    private String ppm;//promile
    private String wellBeing;
    private String note;
    private String doctorNSP;
    private int doctorId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getNSP() {
        return NSP;
    }

    public void setNSP(String NSP) {
        this.NSP = NSP;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getPpm() {
        return ppm;
    }

    public void setPpm(String ppm) {
        this.ppm = ppm;
    }

    public String getWellBeing() {
        return wellBeing;
    }

    public void setWellBeing(String wellBeing) {
        this.wellBeing = wellBeing;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDoctorNSP() {
        return doctorNSP;
    }

    public void setDoctorNSP(String doctorNSP) {
        this.doctorNSP = doctorNSP;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "CheckUp_Entity{" +
                "id=" + id +
                ", idPerson=" + idPerson +
                ", NSP='" + NSP + '\'' +
                ", pressure='" + pressure + '\'' +
                ", ppm='" + ppm + '\'' +
                ", wellBeing='" + wellBeing + '\'' +
                ", note='" + note + '\'' +
                ", doctorNSP='" + doctorNSP + '\'' +
                ", doctorId=" + doctorId +
                '}';
    }
}
