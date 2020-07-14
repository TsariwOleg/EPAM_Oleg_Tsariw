package services.CRUD_DB;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.Entity.CheckUp_Entity;
import services.Entity.Staff_Entity;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CheckUpCRUDTest {

    CheckUpCRUD checkUpCRUD = new CheckUpCRUD();

    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);

    String formattedDate = "";
    int id = 1;

    @BeforeEach
    void setUp() {
        LocalDate localDate = LocalDate.now();
        checkUpCRUD.setLocalDate(localDate);
        formattedDate= localDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
    }


    @Test
    void getCheckUp() throws SQLException {
        String createTable = "CREATE TABLE IF NOT EXISTS CHECKUP_" + formattedDate + " (id int primary key," +
                "pressure varchar(16)," +
                "ppm varchar(10) ," +
                "wellBeing varchar(5)," +
                "note varchar(1000)," +
                "doctorId int," +
                "idPerson int" +
                ")";

        String sql = "SELECT C.ID,PRESSURE,PPM,WELLBEING,NOTE,IDPERSON ,S1.NAME,S1.SURNAME ,S1.PATRONYMIC, " +
                "DOCTORID,S2.NAME,S2.SURNAME ,S2.PATRONYMIC  FROM CHECKUP_" + formattedDate + " c " +
                "INNER JOIN STAFF s1 ON C.IDPERSON=s1.ID " +
                "INNER JOIN STAFF s2 ON C.DOCTORID =s2.ID";


        when(preparedStatement.execute()).thenReturn(true);

        when(connection.prepareStatement(createTable)).thenReturn(preparedStatement);

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(id).thenReturn(id+1);
        when(resultSet.getString(2)).thenReturn("123/100").thenReturn("123/100");
        when(resultSet.getString(3)).thenReturn("3проміле").thenReturn("4проміле");
        when(resultSet.getString(4)).thenReturn("10/10").thenReturn("4/10");
        when(resultSet.getString(5)).thenReturn("З водієм все добре").thenReturn("У водія головокружіння");
        when(resultSet.getInt(6)).thenReturn(1).thenReturn(2);
        when(resultSet.getString(7)).thenReturn("Володимир").thenReturn("Ігор");
        when(resultSet.getString(8)).thenReturn("Петрик").thenReturn("Боришко");
        when(resultSet.getString(9)).thenReturn("Володимирович").thenReturn("Ігорович");

        when(resultSet.getInt(10)).thenReturn(3);
        when(resultSet.getString(11)).thenReturn("Петро");
        when(resultSet.getString(12)).thenReturn("Володимиров");
        when(resultSet.getString(13)).thenReturn("Володимирович");

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        checkUpCRUD.setConnection(connection);
        List<CheckUp_Entity> checkUpEntityList=new ArrayList<>();
        try {
            checkUpEntityList= checkUpCRUD.getCheckUp();
        }catch (Throwable e){
            fail(e.getMessage());
        }

        CheckUp_Entity checkUpEntity = checkUpEntityList.get(0);

        assertEquals(checkUpEntityList.size(),2);
        assertEquals(checkUpEntity.getId(),id);
        assertEquals(checkUpEntity.getPressure(),"123/100");
        assertEquals(checkUpEntity.getPpm(),"3проміле");
        assertEquals(checkUpEntity.getWellBeing(),"10/10");
        assertEquals(checkUpEntity.getId(),id);
        assertEquals(checkUpEntity.getNSP(),"Володимир Петрик Володимирович");
        assertEquals(checkUpEntity.getDoctorId(),3);
        assertEquals(checkUpEntity.getDoctorNSP(),"Петро Володимиров Володимирович");


    }


    @Test
    void testGetCheckUp() throws SQLException {
        String sql = "SELECT C.ID,PRESSURE,PPM,WELLBEING,NOTE,IDPERSON ,S1.NAME,S1.SURNAME ,S1.PATRONYMIC, " +
                "DOCTORID,S2.NAME,S2.SURNAME ,S2.PATRONYMIC  FROM CHECKUP_" + formattedDate + " c " +
                "INNER JOIN STAFF s1 ON C.IDPERSON=s1.ID " +
                "INNER JOIN STAFF s2 ON C.DOCTORID =s2.ID";


        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(id).thenReturn(id+1);
        when(resultSet.getString(2)).thenReturn("123/100");
        when(resultSet.getString(3)).thenReturn("3проміле");
        when(resultSet.getString(4)).thenReturn("10/10");
        when(resultSet.getString(5)).thenReturn("З водієм все добре");
        when(resultSet.getInt(6)).thenReturn(1);
        when(resultSet.getString(7)).thenReturn("Володимир");
        when(resultSet.getString(8)).thenReturn("Петрик");
        when(resultSet.getString(9)).thenReturn("Володимирович");
        when(resultSet.getInt(10)).thenReturn(3);
        when(resultSet.getString(11)).thenReturn("Петро");
        when(resultSet.getString(12)).thenReturn("Володимиров");
        when(resultSet.getString(13)).thenReturn("Володимирович");

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        checkUpCRUD.setConnection(connection);
        CheckUp_Entity checkUpEntity =new CheckUp_Entity();

        try {
            checkUpEntity= checkUpCRUD.getCheckUp(0);
        }catch (Throwable e){
            fail(e.getMessage());
        }



        assertEquals(checkUpEntity.getId(),id);
        assertEquals(checkUpEntity.getPressure(),"123/100");
        assertEquals(checkUpEntity.getPpm(),"3проміле");
        assertEquals(checkUpEntity.getWellBeing(),"10/10");
        assertEquals(checkUpEntity.getId(),id);
        assertEquals(checkUpEntity.getNSP(),"Володимир Петрик Володимирович");
        assertEquals(checkUpEntity.getDoctorId(),3);
        assertEquals(checkUpEntity.getDoctorNSP(),"Петро Володимиров Володимирович");
    }

    @Test
    void createCheckUp() throws SQLException {
        String sql = "INSERT INTO CHECKUP_" + formattedDate + " VALUES(?,?,?,?,?,?,?)";


        CheckUp_Entity checkUpEntity = new CheckUp_Entity();
        checkUpEntity.setId(id);
        checkUpEntity.setNSP("Володимир Володмиров Володимирович");
        checkUpEntity.setDoctorId(id+1);
        checkUpEntity.setDoctorNSP("Петро Петрик Петрович");

        List<Staff_Entity> person = new ArrayList<>();
        Staff_Entity staffEntity = new Staff_Entity();
        staffEntity.setName("Олег");
        staffEntity.setSurname("Олегов");
        staffEntity.setPatronymic("Олегович");
        person.add(staffEntity);

        staffEntity= new Staff_Entity();
        staffEntity.setName("Володимир");
        staffEntity.setSurname("Володмиров");
        staffEntity.setPatronymic("Володимирович");
        staffEntity.setId(id);
        person.add(staffEntity);

        List<Staff_Entity> doctor = new ArrayList<>();
        staffEntity = new Staff_Entity();
        staffEntity.setName("Олег");
        staffEntity.setSurname("Олегов");
        staffEntity.setPatronymic("Олегович");
        doctor.add(staffEntity);

        staffEntity= new Staff_Entity();
        staffEntity.setName("Петро");
        staffEntity.setSurname("Петрик");
        staffEntity.setPatronymic("Петрович");
        staffEntity.setId(id+1);
        doctor.add(staffEntity);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(3);
        when(statement.executeQuery("SELECT MAX(id) FROM CHECKUP_" + formattedDate)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        checkUpCRUD.setConnection(connection);

        try {
            checkUpCRUD.createCheckUp(checkUpEntity,person,doctor);
        }catch (Throwable e){
            fail(e.getMessage());
        }

    }

    @Test
    void createCheckUpWithNull(){
        assertThrows(NullPointerException.class,()-> checkUpCRUD.createCheckUp(null,null,null));
    }

    @Test
    void deleteCheckUp() throws SQLException {
        String sql = "DELETE  CHECKUP_" + formattedDate + " WHERE ID=" + id;
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        checkUpCRUD.setConnection(connection);

        try {
            checkUpCRUD.deleteCheckUp(id);
        }catch (Throwable e){
            fail(e.getMessage());
        }

    }

    @Test
    void updateCheckUp() throws SQLException {
        CheckUp_Entity checkUpEntity = new CheckUp_Entity();
        checkUpEntity.setId(id);
        checkUpEntity.setNSP("Володимир Володмиров Володимирович");
        checkUpEntity.setDoctorId(id+1);
        checkUpEntity.setDoctorNSP("Петро Петрик Петрович");

        List<Staff_Entity> person = new ArrayList<>();
        Staff_Entity staffEntity = new Staff_Entity();
        staffEntity.setName("Олег");
        staffEntity.setSurname("Олегов");
        staffEntity.setPatronymic("Олегович");
        person.add(staffEntity);

        staffEntity= new Staff_Entity();
        staffEntity.setName("Володимир");
        staffEntity.setSurname("Володмиров");
        staffEntity.setPatronymic("Володимирович");
        staffEntity.setId(id);
        person.add(staffEntity);



        List<Staff_Entity> doctor = new ArrayList<>();
        staffEntity = new Staff_Entity();
        staffEntity.setName("Олег");
        staffEntity.setSurname("Олегов");
        staffEntity.setPatronymic("Олегович");
        doctor.add(staffEntity);

        staffEntity= new Staff_Entity();
        staffEntity.setName("Петро");
        staffEntity.setSurname("Петрик");
        staffEntity.setPatronymic("Петрович");
        staffEntity.setId(id+1);
        doctor.add(staffEntity);

        String sql = "UPDATE CHECKUP_" + formattedDate +
                " SET PRESSURE=? , PPM=? , WELLBEING=? , NOTE=? ,IDPERSON=? , DOCTORID=? WHERE id=" + checkUpEntity.getId();

        when(preparedStatement.execute()).thenReturn(true);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        checkUpCRUD.setConnection(connection);

        try {
            checkUpCRUD.updateCheckUp(checkUpEntity,person,doctor);
        }catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    void updateCheckUpWithNull() {
        assertThrows(NullPointerException.class,()-> checkUpCRUD.updateCheckUp(null,null,null));
    }


}