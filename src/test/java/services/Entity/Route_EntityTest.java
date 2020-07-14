package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Route_EntityTest {
Route_Entity routeEntity = new Route_Entity();

    int integer=20;
    String string="string";

    @Test
    void testId() {
        routeEntity.setId(integer);
        assertEquals(routeEntity.getId(),integer);
    }

    @Test
    void testRoute() {
        routeEntity.setRoute(string);
        assertEquals(routeEntity.getRoute(),string);
    }

    @Test
    void testAvarageProfit() {
        routeEntity.setAvarageProfit(++integer);
        assertEquals(routeEntity.getAvarageProfit(),integer);
    }

    @Test
    void testAvarageFuelConsuption() {
        routeEntity.setAvarageFuelConsuption(++integer);
        assertEquals(routeEntity.getAvarageFuelConsuption(),integer);
    }

    @Test
    void testFullRoute() {
        routeEntity.setFullRoute(string+"1");
        assertEquals(routeEntity.getFullRoute(),string+"1");
    }

    @Test
    void testCountOfBuses() {
        routeEntity.setCountOfBuses(++integer);
        assertEquals(routeEntity.getCountOfBuses(),integer);
    }
}