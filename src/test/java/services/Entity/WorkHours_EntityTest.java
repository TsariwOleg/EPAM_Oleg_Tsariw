package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkHours_EntityTest {
    WorkHours_Entity workHoursEntity =new WorkHours_Entity();
    int integer=20;
    String string="string";

    @Test
    void testId() {
        workHoursEntity.setId(integer);
        assertEquals(workHoursEntity.getId(),integer);
    }

    @Test
    void testStartWorkHour() {
        workHoursEntity.setStartWorkHour(string+"1");
        assertEquals(workHoursEntity.getStartWorkHour(),string+"1");
    }

    @Test
    void testEndWorkHour() {
        workHoursEntity.setEndWorkHour(string+"2");
        assertEquals(workHoursEntity.getEndWorkHour(),string+"2");
    }
}