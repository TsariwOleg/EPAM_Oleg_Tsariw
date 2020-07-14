package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicalBook_EntityTest {
    int integer=20;
    String string="string";
    MedicalBook_Entity medicalBookEntity =new MedicalBook_Entity(string,string+"1");

    @Test
    void testId() {
        medicalBookEntity.setId(integer);
        assertEquals(medicalBookEntity.getId(),integer);
    }

    @Test
    void testDateOfMedicalExam() {
        assertEquals(medicalBookEntity.getDateOfMedicalExam(),string);

    }

    @Test
    void testDateOfNextMedicalExam() {
        assertEquals(medicalBookEntity.getDateOfNextMedicalExam(), string+"1");
    }
}