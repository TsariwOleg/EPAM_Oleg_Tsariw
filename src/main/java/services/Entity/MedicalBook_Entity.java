package services.Entity;

public class MedicalBook_Entity {
    private int id;
    private String dateOfMedicalExam;
    private String dateOfNextMedicalExam;


    public MedicalBook_Entity(int id, String dateOfMedicalExam, String dateOfNextMedicalExam) {
        this.id = id;
        this.dateOfMedicalExam = dateOfMedicalExam;
        this.dateOfNextMedicalExam = dateOfNextMedicalExam;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateOfMedicalExam() {
        return dateOfMedicalExam;
    }

    public void setDateOfMedicalExam(String dateOfMedicalExam) {
        this.dateOfMedicalExam = dateOfMedicalExam;
    }

    public String getDateOfNextMedicalExam() {
        return dateOfNextMedicalExam;
    }

    public void setDateOfNextMedicalExam(String dateOfNextMedicalExam) {
        this.dateOfNextMedicalExam = dateOfNextMedicalExam;
    }

    @Override
    public String toString() {
        return "MedicalBook_Entity{" +
                "id=" + id +
                ", dateOfMedicalExam='" + dateOfMedicalExam + '\'' +
                ", dateOfNextMedicalExam='" + dateOfNextMedicalExam + '\'' +
                '}';
    }
}
