package services.CRUD_DB.Person_CRUD;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Delete_PersonTest {
    Delete_Person delete_person  = new Delete_Person();

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);

    int id=1;

    @Test
    void deleteStaff() throws SQLException {

        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement("update staff set  AGE=null , department_id=null where id=" + id))
                .thenReturn(preparedStatement);

        when(connection.prepareStatement("DELETE FROM BUS_DRIVERS WHERE ID=" + id))
                .thenReturn(preparedStatement);

        when(connection.prepareStatement("DELETE FROM ADMINISTRATION WHERE ID=" + id))
                .thenReturn(preparedStatement);

        delete_person.setConnection(connection);

        delete_person.deleteStaff(id);


    }

    @Test
    void deletePassport() throws SQLException {
        String sql = "update passport set DATE_OF_BIRTH=null , COUNTRY_OF_BIRTH=null , REGION_OF_BIRTH=null ," +
                "CITY_OF_BIRTH=null ,DOCUMENT_NO=null where id=" + id;
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        delete_person.setConnection(connection);

        delete_person.deletePassport(id);

    }

    @Test
    void deleteTaxpayerCard() throws SQLException {
        String sql = "update taxpayer_card set  TAXPAYER_NUMBER=null  where id=" + id;
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        delete_person.setConnection(connection);

        delete_person.deleteTaxpayerCard(id);
    }

    @Test
    void deleteMedicalBook() throws SQLException {
        String sql = "update Medical_book set  date_OF_MEDICAL_EXAMINATION=null , date_OF_NEXT_MEDICAL_EXAMINATION=null" +
                "  where id=" + id;
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        delete_person.setConnection(connection);

        delete_person.deleteMedicalBook(id);
    }

    @Test
    void deleteBusDriver() throws SQLException {
        String sql = "UPDATE BUS_DRIVERS SET WORK_HOUR_ID=NULL , WORK_BUS_ID=NULL WHERE ID=" + id;
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        delete_person.setConnection(connection);

        delete_person.deleteBusDriver(id);
    }
}