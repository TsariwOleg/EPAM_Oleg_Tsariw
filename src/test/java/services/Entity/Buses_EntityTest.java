package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Buses_EntityTest {
    Buses_Entity busesEntity = new Buses_Entity();
    int integer=20;
    String string="string";

    @Test
    void testId() {
        busesEntity.setId(integer);
        assertEquals(busesEntity.getId(),integer);
    }

    @Test
    void testBusNo() {
    busesEntity.setBusNo(string);
    assertEquals(busesEntity.getBusNo(),string);
    }

    @Test
    void testYearOfIssue() {
        busesEntity.setYearOfIssue(++integer);
        assertEquals(busesEntity.getYearOfIssue(),integer);
    }

    @Test
    void testModel() {
    busesEntity.setModel(string+"1");
    assertEquals(busesEntity.getModel(),string+"1");
    }

    @Test
    void testFuelConsumption() {
        busesEntity.setFuelConsumption(string+"2");
        assertEquals(busesEntity.getFuelConsumption(),string+"2");
    }

    @Test
    void testRouteId() {
        busesEntity.setRouteId(++integer);
        assertEquals(busesEntity.getRouteId(),integer);
    }

    @Test
    void testRoute() {
        busesEntity.setRoute(string+"3");
        assertEquals(busesEntity.getRoute(),string+"3");
    }
}