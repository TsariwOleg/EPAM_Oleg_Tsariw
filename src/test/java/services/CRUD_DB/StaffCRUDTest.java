package services.CRUD_DB;

import org.junit.jupiter.api.Test;
import services.Entity.Department_Entity;
import services.Entity.Staff_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StaffCRUDTest {
    StaffCRUD staffCRUD = new StaffCRUD();

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);

    int id = 1;

    @Test
    void getStaff() throws SQLException {
        String sql = "SELECT * FROM STAFF s LEFT JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.ID";
        String name = "Петро";
        String surname = "Мельник";
        String patronymic="Петрович";
        int age = 15;
        String department = "Адміністрація";

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("NAME")).thenReturn(name).thenReturn("Данило");
        when(resultSet.getString("SURNAME")).thenReturn(surname).thenReturn("Модрина");
        when(resultSet.getString("PATRONYMIC")).thenReturn(patronymic).thenReturn("Андрійович");
        when(resultSet.getInt("AGE")).thenReturn(age).thenReturn(17);
        when(resultSet.getInt("ID")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("DEPARTMENT")).thenReturn(department).thenReturn("Водії");

        when(statement.executeQuery(sql)).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);

        staffCRUD.setConnection(connection);

        List<Staff_Entity> staffEntityList = staffCRUD.getStaff();
        assertEquals(staffEntityList.size(),2);

        Staff_Entity staffEntity = staffEntityList.get(0);
        assertEquals(staffEntity.getName(),name);
        assertEquals(staffEntity.getSurname(),surname);
        assertEquals(staffEntity.getPatronymic(),patronymic);
        assertEquals(staffEntity.getAge(),age);
        assertEquals(staffEntity.getId(),id);
        assertEquals(staffEntity.getDepartment(),department);
    }

    @Test
    void createPerson() throws SQLException {
        String sql = "insert  INTO STAFF(id,NAME,SURNAME,PATRONYMIC,AGE,DEPARTMENT_ID) VALUES(?,?,?,?,?,?)";

        int max = 5;

        Staff_Entity staffEntity = new Staff_Entity();
        staffEntity.setName("Петро");
        staffEntity.setSurname("Мельник");
        staffEntity.setPatronymic("Петрович");
        staffEntity.setAge(20);
        staffEntity.setDepartment("Водії");
        staffEntity.setId(id);

        Map map = new HashMap();
        List<Department_Entity> departmentEntityList = new ArrayList<>();
        Department_Entity departmentEntity = new Department_Entity();
        departmentEntity.setDepartment("Водії");
        departmentEntity.setDepartmentId(1);
        departmentEntityList.add(departmentEntity);

        departmentEntity=new Department_Entity();
        departmentEntity.setDepartment("Адміністрація");
        departmentEntity.setDepartmentId(2);
        departmentEntityList.add(departmentEntity);

        map.put("departments",departmentEntityList);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(max);

        when(statement.executeQuery("SELECT MAX(id) FROM staff")).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);

        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        when(connection.prepareStatement("INSERT INTO PASSPORT (ID) VALUES(" + ++max + ");" +
                "INSERT INTO MEDICAL_BOOK(ID) VALUES (" + max + ");" +
                "INSERT INTO TAXPAYER_CARD(ID) VALUES (" + max + ");")).thenReturn(preparedStatement);

        staffCRUD.setConnection(connection);

        try {
            staffCRUD.createPerson(staffEntity,map);
        }catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    void createPersonWithNull() {
        assertThrows(NullPointerException.class, ()->staffCRUD.createPerson(null,null));
    }

    @Test
    void deletePerson() throws SQLException {
        String sql = "DELETE FROM PASSPORT WHERE ID=" + id + ";" +
                "DELETE FROM MEDICAL_BOOK WHERE ID=" + id + ";" +
                "DELETE FROM TAXPAYER_CARD WHERE ID=" + id + ";" +
                "DELETE FROM CONTACT WHERE ID=" + id + ";" +
                "DELETE FROM ADMINISTRATION WHERE ID=" + id + ";" +
                "DELETE FROM DOCTORS WHERE ID=" + id + ";" +
                "DELETE FROM BUS_DRIVERS WHERE ID=" + id + ";" +
                "DELETE FROM ACCESS_TO_WEB WHERE ID=" + id + ";" +
                "DELETE FROM CAR_MECHANICS WHERE ID=" + id + ";" +
                "DELETE FROM STAFF WHERE ID=" + id + ";" +
                "UPDATE REPAIRED_BUS SET Mechanic_ID=null where Mechanic_ID=" + id;

        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        staffCRUD.setConnection(connection);
        try {
            staffCRUD.deletePerson(id);

        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }
}