package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Position_EntityTest {
Position_Entity positionEntity =new Position_Entity();

    int integer=20;
    String string="string";


    @Test
    void testId() {
        positionEntity.setId(integer);
        assertEquals(positionEntity.getId(),integer);
    }

    @Test
    void testPosition() {
        positionEntity.setPosition(string);
        assertEquals(positionEntity.getPosition(),string);
    }

    @Test
    void testSalary() {
        positionEntity.setSalary(++integer);
        assertEquals(positionEntity.getSalary(),integer);
    }
}