package services.Entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckUp_EntityTest {
CheckUp_Entity checkUpEntity = new CheckUp_Entity();
    int integer=20;
    String string="string";

    @Test
    void testId() {
        checkUpEntity.setId(integer);
        assertEquals(checkUpEntity.getId(),integer);
    }

    @Test
    void testNSP() {
        checkUpEntity.setNSP(string+"1");
        assertEquals(checkUpEntity.getNSP(),string+"1");
    }

    @Test
    void testPressure() {
        checkUpEntity.setPressure(string+"2");
        assertEquals(checkUpEntity.getPressure(),string+"2");
    }

    @Test
    void testPpm() {
        checkUpEntity.setPpm(string+"3");
        assertEquals(checkUpEntity.getPpm(),string+"3");
    }

    @Test
    void testWellBeing() {
        checkUpEntity.setWellBeing(string+"4");
        assertEquals(checkUpEntity.getWellBeing(),string+"4");
    }

    @Test
    void testNote() {
        checkUpEntity.setNote(string+"5");
        assertEquals(checkUpEntity.getNote(),string+"5");
    }

    @Test
    void testDoctorNSP() {
        checkUpEntity.setDoctorNSP(string+"6");
        assertEquals(checkUpEntity.getDoctorNSP(),string+"6");
    }

    @Test
    void testDoctorId() {
        checkUpEntity.setDoctorId(++integer);
        assertEquals(checkUpEntity.getDoctorId(),integer);
    }
}