package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentPage_EntityTest {
    DepartmentPage_Entity departmentPageEntity = new DepartmentPage_Entity();
    int integer=20;
    float aFloat=  1.1f;

    @Test
    void testCountOfDrivers() {
        departmentPageEntity.setCountOfDrivers(integer);
        assertEquals(departmentPageEntity.getCountOfDrivers(),integer);
    }

    @Test
    void testAvgSallaryOfDrivers() {
        departmentPageEntity.setAvgSallaryOfDrivers(aFloat);
        assertEquals(departmentPageEntity.getAvgSallaryOfDrivers(),aFloat);
    }


    @Test
    void testCountOfAdministrations() {
        departmentPageEntity.setCountOfAdministrations(++integer);
        assertEquals(departmentPageEntity.getCountOfAdministrations(),integer);
    }

    @Test
    void testAvgSallaryOfAdministrations() {
        departmentPageEntity.setAvgSallaryOfAdministrations(++aFloat);
        assertEquals(departmentPageEntity.getAvgSallaryOfAdministrations(),aFloat);
    }

    @Test
    void testCountOfMechanics() {
        departmentPageEntity.setCountOfMechanics(++integer);
        assertEquals(departmentPageEntity.getCountOfMechanics(),integer);
    }

    @Test
    void testAvgSallaryOfMechanics() {
        departmentPageEntity.setAvgSallaryOfMechanics(++aFloat);
        assertEquals(departmentPageEntity.getAvgSallaryOfMechanics(),aFloat);
    }

    @Test
    void testCountOfDoctors() {
        departmentPageEntity.setCountOfDoctors(++integer);
        assertEquals(departmentPageEntity.getCountOfDoctors(),integer);
    }

    @Test
    void testAvgSallaryOfDoctors() {
        departmentPageEntity.setAvgSallaryOfDoctors(++aFloat);
        assertEquals(departmentPageEntity.getAvgSallaryOfDoctors(),aFloat);
    }
}