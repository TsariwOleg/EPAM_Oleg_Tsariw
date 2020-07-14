package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusDrivers_EntityTest {
    BusDrivers_Entity busDriversEntity = new BusDrivers_Entity();
    int integer=20;
    String string="string";
    @Test
    void testId() {
        int id=1;
        busDriversEntity.setId(integer);
        assertEquals(busDriversEntity.getId(),integer);
    }

    @Test
    void testWorkBus() {
        busDriversEntity.setWorkBus(string);
        assertEquals(busDriversEntity.getWorkBus(),string);
    }

    @Test
    void testIdBus() {
        int id=1;
        busDriversEntity.setId(id);
        assertEquals(busDriversEntity.getId(),id);
    }

}