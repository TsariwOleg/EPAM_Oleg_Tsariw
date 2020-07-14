package services.CRUD_DB.Person_CRUD;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import services.Entity.*;

import java.io.InputStream;
import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class Read_PersonTest {
    Read_Person read_person = new Read_Person();

    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);

    int id=1;


    @Test
    void getStaffWithoutPhoto() throws SQLException {
        String sql = "SELECT * FROM STAFF s  LEFT join DEPARTMENT d  on s.department_id=d.id where s.id=" + id;
        String name="Богдан";
        String surname="Хмельницький";
        String patronymic="Михайлович";
        int age=30;

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("NAME")).thenReturn(name);
        when(resultSet.getString("SURNAME")).thenReturn(surname);
        when(resultSet.getString("PATRONYMIC")).thenReturn(patronymic);
        when(resultSet.getInt("AGE")).thenReturn(age);

        when(statement.executeQuery(sql)).thenReturn(resultSet);


        when(connection.createStatement()).thenReturn(statement);

        read_person.setConnection(connection);
        Staff_Entity staffEntity= read_person.getStaff(id);

        assertEquals(staffEntity.getId(),id);
        assertEquals(staffEntity.getName(),name);
        assertEquals(staffEntity.getSurname(),surname);
        assertEquals(staffEntity.getPatronymic(),patronymic);
        assertEquals(staffEntity.getAge(),age);
        assertNull(staffEntity.getPhoto());

    }

    @Ignore
    @Test
    void getStaffWithPhoto() throws SQLException {
        String sql = "SELECT * FROM STAFF s  LEFT join DEPARTMENT d  on s.department_id=d.id where s.id=" + id;
        String name="Богдан";
        String surname="Хмельницький";
        String patronymic="Михайлович";
        Blob photo=mock(Blob.class);
        int age=30;

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("NAME")).thenReturn(name);
        when(resultSet.getString("SURNAME")).thenReturn(surname);
        when(resultSet.getString("PATRONYMIC")).thenReturn(patronymic);
        when(resultSet.getInt("AGE")).thenReturn(age);
        when(resultSet.getBlob("photo")).thenReturn(photo);

        InputStream inputStream = resultSet.getBlob("photo").getBinaryStream();


        when(statement.executeQuery(sql)).thenReturn(resultSet);


        when(connection.createStatement()).thenReturn(statement);

        read_person.setConnection(connection);
//        Staff_Entity staffEntity= read_person.getStaff(id);



    }

    @Test
    void getDepartment() throws SQLException {
        String sql = "SELECT d.id ,d.DEPaRTMENT FROM DEPaRTMENT d inner join staff s on s.department_id=d.id WHERE s.ID=" + id;
        String department = "Водії";

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID")).thenReturn(1);
        when(resultSet.getString("DEPARTMENT")).thenReturn(department);

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        read_person.setConnection(connection);

        Department_Entity departmentEntity =read_person.getDepartment(id);
        assertEquals(departmentEntity.getDepartmentId(),1);
        assertEquals(departmentEntity.getDepartment(),department);

    }

    @Test
    void getPassport() throws SQLException {
        String sql = "Select * from PASSPORT where id=" + id;
        String date_Of_Birth="01-01-2001";
        String country_Of_Birth = "Україна";
        String region_Of_Birth = "Львівська обл";
        String city_Of_Birth = "Львів";

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("date_Of_Birth")).thenReturn(date_Of_Birth);
        when(resultSet.getString("country_Of_Birth")).thenReturn(country_Of_Birth);
        when(resultSet.getString("region_Of_Birth")).thenReturn(region_Of_Birth);
        when(resultSet.getString("city_Of_Birth")).thenReturn(city_Of_Birth);
        when(resultSet.getInt("document_No")).thenReturn(id);
        when(resultSet.getInt("id")).thenReturn(id);

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);
        read_person.setConnection(connection);
        Passport_Entity passportEntity = read_person.getPassport(id);

        assertEquals(passportEntity.getId(),id);
        assertEquals(passportEntity.getDateOfBirth(),date_Of_Birth);
        assertEquals(passportEntity.getCountryOfBirth(),country_Of_Birth);
        assertEquals(passportEntity.getRegionOfBirth(),region_Of_Birth);
        assertEquals(passportEntity.getCityOfBirth(),city_Of_Birth);
        assertEquals(passportEntity.getDocumentNo(),id);

    }
    @Test
    void getTaxpayerCard() throws SQLException {
        String sql = "select * from taxpayer_card where id=" + id;
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("Taxpayer_number")).thenReturn(id);

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        read_person.setConnection(connection);

        TaxpayerCard_Entity taxpayerCardEntity=read_person.getTaxpayerCard(id);

        assertEquals(taxpayerCardEntity.getTaxpayerNumber(),id);
    }

    @Test
    void getMedicalBook() throws SQLException {
        int id=1;
        String date_OF_MEDICAL_EXAMINATION="01-01-2018";
        String date_OF_NEXT_MEDICAL_EXAMINATION="01-01-2020";

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("date_OF_MEDICAL_EXAMINATION")).thenReturn(date_OF_MEDICAL_EXAMINATION);
        when(resultSet.getString("date_OF_NEXT_MEDICAL_EXAMINATION")).thenReturn(date_OF_NEXT_MEDICAL_EXAMINATION);
        when(resultSet.getInt("id")).thenReturn(1);



        String sql = "Select * from Medical_book where id=" + id;
        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        read_person.setConnection(connection);


        MedicalBook_Entity medicalBookEntity = read_person.getMedicalBook(1);

        assertEquals(medicalBookEntity.getId(),id);
        assertEquals(medicalBookEntity.getDateOfMedicalExam(),date_OF_MEDICAL_EXAMINATION);
        assertEquals(medicalBookEntity.getDateOfNextMedicalExam(),date_OF_NEXT_MEDICAL_EXAMINATION);

    }





    @Test
    void getBusDriver() throws SQLException {
        String sql = "select * from BUS_DRIVERS BD LEFT JOIN WORK_HOURS WH  ON WH.WORK_HOUR_ID=BD.WORK_HOUR_ID " +
                "LEFT JOIN BUS_PARK BP ON BP.BUS_ID=BD.WORK_BUS_ID where BD.id=" + id;
        String startWorkHours = "12:00";
        String endWorkHours = "22:00";
        String bus = "BC4104EE";



        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("START_WORK_HOURS")).thenReturn(startWorkHours);
        when(resultSet.getString("END_WORK_HOURS")).thenReturn(endWorkHours);
        when(resultSet.getString("BUS")).thenReturn(bus);
        when(resultSet.getInt("id")).thenReturn(id);
        when(resultSet.getInt("BUS_ID")).thenReturn(id);

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        read_person.setConnection(connection);
        BusDrivers_Entity busDriversEntity = read_person.getBusDriver(id);

        assertEquals(busDriversEntity.getIdBus(),id);
        assertEquals(busDriversEntity.getWorkBus(),bus);
        assertEquals(busDriversEntity.getStartWorkHour(),startWorkHours);
        assertEquals(busDriversEntity.getEndWorkHour(),endWorkHours);
        assertEquals(busDriversEntity.getId(),id);


    }
}