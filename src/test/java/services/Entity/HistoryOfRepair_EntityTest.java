package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryOfRepair_EntityTest {
    HistoryOfRepair_Entity historyOfRepairEntity = new HistoryOfRepair_Entity();
    int integer=20;
    String string="string";

    @Test
    void testRepairedNo() {
        historyOfRepairEntity.setRepairedNo(integer);
        assertEquals(historyOfRepairEntity.getRepairedNo(),integer);
    }

    @Test
    void testMalfunction() {
        historyOfRepairEntity.setMalfunction(string);
        assertEquals(historyOfRepairEntity.getMalfunction(),string);
    }

    @Test
    void testCostOfRepair() {
        historyOfRepairEntity.setCostOfRepair(++integer);
        assertEquals(historyOfRepairEntity.getCostOfRepair(),integer);
    }

    @Test
    void testNote() {
        historyOfRepairEntity.setNote(string+"1");
        assertEquals(historyOfRepairEntity.getNote(),string+"1");
    }

    @Test
    void testBus_id() {
        historyOfRepairEntity.setBus_id(++integer);
        assertEquals(historyOfRepairEntity.getBus_id(),integer);
    }

    @Test
    void testBus() {
        historyOfRepairEntity.setBus(string+"2");
        assertEquals(historyOfRepairEntity.getBus(),string+"2");
    }

    @Test
    void testNSP() {
        historyOfRepairEntity.setNSP(string+"3");
        assertEquals(historyOfRepairEntity.getNSP(),string+"3");
    }

    @Test
    void testMechanic_id() {
        historyOfRepairEntity.setMechanic_id(++integer);
        assertEquals(historyOfRepairEntity.getMechanic_id(),integer);
    }
}