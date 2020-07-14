package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Staff_EntityTest {
    Staff_Entity staffEntity = new Staff_Entity();
    int integer=20;
    String string="string";

    @Test
    void testId() {
        staffEntity.setId(integer);
        assertEquals(staffEntity.getId(),integer);
    }

    @Test
    void testName() {
        staffEntity.setName(string+"1");
        assertEquals(staffEntity.getName(),string+"1");
    }

    @Test
    void testSurname() {
        staffEntity.setSurname(string+"2");
        assertEquals(staffEntity.getSurname(),string+"2");
    }

    @Test
    void testPatronymic() {
        staffEntity.setPatronymic(string+"3");
        assertEquals(staffEntity.getPatronymic(),string+"3");
    }

    @Test
    void testAge() {
        staffEntity.setAge(++integer);
        assertEquals(staffEntity.getAge(),integer);
    }

    @Test
    void testDepartment() {
        staffEntity.setDepartment(string+"4");
        assertEquals(staffEntity.getDepartment(),string+"4");
    }

    @Test
    void testPosition() {
        staffEntity.setPosition(string+"5");
        assertEquals(staffEntity.getPosition(),string+"5");
    }
}