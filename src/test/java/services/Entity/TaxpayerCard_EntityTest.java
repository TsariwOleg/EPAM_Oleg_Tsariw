package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaxpayerCard_EntityTest {
    int integer=20;

    TaxpayerCard_Entity taxpayerCardEntity = new TaxpayerCard_Entity(integer);

    @Test
    void tesTaxpayerNumbert() {
        assertEquals(taxpayerCardEntity.getTaxpayerNumber(),integer);
    }

    @Test
    void testId() {
        taxpayerCardEntity.setId(++integer);
        assertEquals(taxpayerCardEntity.getId(),integer);
    }


}