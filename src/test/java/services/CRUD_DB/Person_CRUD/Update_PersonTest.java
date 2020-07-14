package services.CRUD_DB.Person_CRUD;

import org.junit.jupiter.api.Test;
import services.Entity.MedicalBook_Entity;
import services.Entity.Passport_Entity;
import services.Entity.TaxpayerCard_Entity;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Update_PersonTest {
    Update_Person update_person = new Update_Person();

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);

    int id=1;



    @Test
    void updatePassport() throws SQLException {
        String sql = "UPDATE PASSPORT SET  DATE_OF_BIRTH=? , " +
                "COUNTRY_OF_BIRTH=? ,REGION_OF_BIRTH=? ,CITY_OF_BIRTH=? , DOCUMENT_NO=? WHERE ID="+id;
        Passport_Entity passportEntity = new Passport_Entity("01-01-2001","Україна",
                "Львська обл.", "Львів" ,12121212);

        when(preparedStatement.executeUpdate()).thenReturn(1);



        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        update_person.setConnection(connection);
        update_person.updatePassport(id,passportEntity);
    }

    @Test
    void updatePassportWithNull() throws SQLException {
        String sql = "UPDATE PASSPORT SET  DATE_OF_BIRTH=? , " +
                "COUNTRY_OF_BIRTH=? ,REGION_OF_BIRTH=? ,CITY_OF_BIRTH=? , DOCUMENT_NO=? WHERE ID="+id;
        Passport_Entity passportEntity = new Passport_Entity("01-01-2001","Україна",
                null, "Львів" ,12121212);

        when(preparedStatement.executeUpdate()).thenReturn(1);



        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        update_person.setConnection(connection);
       assertThrows(NullPointerException.class,()->update_person.updatePassport(id,passportEntity));


    }

    @Test
    void updateTaxpayerCard() throws SQLException {
        String sql = "UPDATE taxpayer_card SET  TAXPAYER_NUMBER=? where id="+id;
        TaxpayerCard_Entity taxpayerCardEntity = new TaxpayerCard_Entity(121212);

        when(preparedStatement.executeUpdate()).thenReturn(1);



        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        update_person.setConnection(connection);
        update_person.updateTaxpayerCard(id,taxpayerCardEntity);

    }


    @Test
    void updateMedicalBook() throws SQLException {
        String sql="UPDATE Medical_book SET date_OF_MEDICAL_EXAMINATION=?, date_OF_NEXT_MEDICAL_EXAMINATION=? WHERE ID="+id;
        MedicalBook_Entity medicalBookEntity = new MedicalBook_Entity("01-01-2018",
                "01-01-2020");

        when(preparedStatement.executeUpdate()).thenReturn(1);



        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        update_person.setConnection(connection);
        update_person.updateMedicalBook(id,medicalBookEntity);
    }

    @Test
    void updateMedicalBookWithNull() throws SQLException {
        String sql="UPDATE Medical_book SET date_OF_MEDICAL_EXAMINATION=?, date_OF_NEXT_MEDICAL_EXAMINATION=? WHERE ID="+id;
        MedicalBook_Entity medicalBookEntity = new MedicalBook_Entity("01-01-2018",
                null);

        when(preparedStatement.executeUpdate()).thenReturn(1);



        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        update_person.setConnection(connection);
       assertThrows(NullPointerException.class,()->update_person.updateMedicalBook(id,medicalBookEntity));


    }
}