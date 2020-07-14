package services.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Passport_EntityTest {
    int integer=20;
    String string="string";

    Passport_Entity passportEntity =new Passport_Entity(string,string+"1",string+"2"
            ,string+"3",integer);

    @Test
    void testId() {
        passportEntity.setId(integer);
        assertEquals(passportEntity.getId(),integer);
    }

    @Test
    void testDateOfBirth() {
        assertEquals(passportEntity.getDateOfBirth(),string);
    }

    @Test
    void testCityOfBirth() {
        assertEquals(passportEntity.getCityOfBirth(),string+"3");

    }

    @Test
    void testRegionOfBirth() {
        assertEquals(passportEntity.getRegionOfBirth(),string+"2");

    }

    @Test
    void testCountryOfBirth() {
        assertEquals(passportEntity.getCountryOfBirth(),string+"1");

    }

    @Test
    void testDocumentNo() {
        assertEquals(passportEntity.getDocumentNo(),integer);
    }


}