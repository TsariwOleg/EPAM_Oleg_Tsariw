package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Department_EntityTest {
    Department_Entity departmentEntity = new Department_Entity();
    int integer=20;
    String string="string";

    @Test
    void testDepartmentId() {
    departmentEntity.setDepartmentId(integer);
        assertEquals(departmentEntity.getDepartmentId(),integer);
    }

    @Test
    void testDepartment() {
        departmentEntity.setDepartment(string);
        assertEquals(departmentEntity.getDepartment(),string);
    }
}