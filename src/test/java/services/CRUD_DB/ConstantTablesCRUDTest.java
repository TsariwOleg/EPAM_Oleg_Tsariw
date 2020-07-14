package services.CRUD_DB;

import org.junit.jupiter.api.Test;
import services.Entity.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConstantTablesCRUDTest {
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();

    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    int id = 1;

    @Test
    void readConstantTable() {
    }

    @Test
    void readConstantTableH() {
    }


    @Test
    void readRoute() throws SQLException {
        String sql = "SELECT ID,ROUTE FROM ROUTE";
        String route = "Львів-Миколаїв";

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("ROUTE")).thenReturn(route).thenReturn("Львів-Київ");

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        constantTablesCRUD.setConnection(connection);
        List<Route_Entity> routeEntityList = constantTablesCRUD.readRoute();

        assertEquals(routeEntityList.size(), 2);
        Route_Entity routeEntity = routeEntityList.get(0);
        assertEquals(routeEntity.getId(), id);
        assertEquals(routeEntity.getRoute(), route);
    }


    @Test
    void readStaff() throws SQLException {
        String department = "Drivers";
        String sql = "SELECT * FROM STAFF " + department;
        String name = "Остап";
        String surname = "Дембіц";
        String patronymic = "Андрійович";
        int age = 10;

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("NAME")).thenReturn(name).thenReturn("Олег");
        when(resultSet.getString("SURNAME")).thenReturn(surname).thenReturn("Олегов");
        when(resultSet.getString("PATRONYMIC")).thenReturn(patronymic).thenReturn("Олегович");
        when(resultSet.getInt("AGE")).thenReturn(age).thenReturn(15);
        when(resultSet.getInt("ID")).thenReturn(id).thenReturn(2);


        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        constantTablesCRUD.setConnection(connection);
        List<Staff_Entity> staffEntityList = constantTablesCRUD.readStaff(department);

        assertEquals(staffEntityList.size(), 2);
        Staff_Entity staffEntity = staffEntityList.get(0);
        assertEquals(staffEntity.getName(), name);
        assertEquals(staffEntity.getSurname(), surname);
        assertEquals(staffEntity.getPatronymic(), patronymic);
        assertEquals(staffEntity.getAge(), age);
        assertEquals(staffEntity.getId(), id);
    }

    @Test
    void readStaffForSignUp() {
       /* List<SignIn_Entity> signInEntityList = new ArrayList<>();
        SignIn_Entity signInEntity = new SignIn_Entity();
        signInEntity.setId(id);
        signInEntityList.add(signInEntity);

        signInEntity=new SignIn_Entity();
        signInEntity.setId(2);
        signInEntityList.add(signInEntity);

        signInEntity=new SignIn_Entity();
        signInEntity.setId(4);
        signInEntityList.add(signInEntity);*/


//        when(  List<Staff_Entity> signInEntityList = readStaff("WHERE (DEPARTMENT_ID=1 or DEPARTMENT_ID=4)")).thenReturn()
    }

    @Test
    void getWorkHours() throws SQLException {
        String string = "";
        String sql = "SELECT * FROM WORK_HOURS " + string;
        String startWorkHours = "12:00";
        String endWorkHours = "22:00";


        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("WORK_HOUR_ID")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("START_WORK_HOURS")).thenReturn(startWorkHours).thenReturn("11:00");
        when(resultSet.getString("END_WORK_HOURS")).thenReturn(endWorkHours).thenReturn("21:00");

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        constantTablesCRUD.setConnection(connection);

        List<WorkHours_Entity> workHoursEntityList = constantTablesCRUD.getWorkHours(string);
        assertEquals(workHoursEntityList.size(), 2);

        WorkHours_Entity workHoursEntity = workHoursEntityList.get(0);
        assertEquals(workHoursEntity.getId(), id);
        assertEquals(workHoursEntity.getStartWorkHour(), startWorkHours);
        assertEquals(workHoursEntity.getEndWorkHour(), endWorkHours);
    }

    @Test
    void createWorkHours() throws SQLException {
        String sql = "INSERT INTO WORK_HOURS VALUES(?,?,?)";

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(3);

        when(statement.executeQuery("SELECT MAX(WORK_HOUR_ID) FROM WORK_HOURS")).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        constantTablesCRUD.setConnection(connection);

        Object object1 = "10:10";
        Object object2 = "20:10";

        constantTablesCRUD.createWorkHours(object1, object2);
    }


    @Test
    void deleteWorkHours() throws SQLException {
        String sql = "DELETE FROM WORK_HOURS WHERE WORK_HOUR_ID=" + id;

        when(preparedStatement.execute()).thenReturn(true);

        when(connection.prepareStatement("UPDATE BUS_DRIVERS SET " +
                "WORK_HOUR_ID=NULL WHERE WORK_HOUR_ID=" + id)).thenReturn(preparedStatement);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        constantTablesCRUD.setConnection(connection);

        try {
            constantTablesCRUD.deleteWorkHours(id);

        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    void updateWorkHours() throws SQLException {
        WorkHours_Entity workHoursEntity = new WorkHours_Entity();
        workHoursEntity.setId(id);
        workHoursEntity.setStartWorkHour("10:10");
        workHoursEntity.setEndWorkHour("20:20");
        String sql = "UPDATE WORK_HOURS SET START_WORK_HOURS=? , END_WORK_HOURS=?  " +
                "WHERE WORK_HOUR_ID=" + workHoursEntity.getId();

        when(preparedStatement.execute()).thenReturn(true);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        constantTablesCRUD.setConnection(connection);
        try {
            constantTablesCRUD.updateWorkHours(workHoursEntity);
        }catch (Throwable e){
            fail(e.getMessage());
        }

    }


    @Test
    void getPositions() throws SQLException {
        String sql = "Select * from POSITION ";

        int salary=10000;
        String position= "Водій";

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("POSITION_ID")).thenReturn(id).thenReturn(2);
        when(resultSet.getInt("salary")).thenReturn(salary).thenReturn(120000);
        when(resultSet.getString("POSITION")).thenReturn(position).thenReturn("Директор компанії");

        when(statement.executeQuery(sql)).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);
        constantTablesCRUD.setConnection(connection);

        List<Position_Entity> positionEntityList = constantTablesCRUD.getPositions("");
        assertEquals(positionEntityList.size(),2);

        Position_Entity positionEntity = positionEntityList.get(0);
        assertEquals(positionEntity.getId(),id);
        assertEquals(positionEntity.getPosition(),position);
        assertEquals(positionEntity.getSalary(),salary);

    }

    @Test
    void createPosition() throws SQLException {
        String sql = "INSERT INTO POSITION VALUES(?,?,?)";


        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(3);

        when(statement.executeQuery("SELECT MAX(POSITION_ID) FROM POSITION")).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);


        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        constantTablesCRUD.setConnection(connection);

        Object object1 = "Водій";
        int sallary = 10000;

        try {
            constantTablesCRUD.createPosition(object1, sallary);
        }catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    void deletePosition() throws SQLException {
        String sql ="UPDATE BUS_DRIVERS SET POSITION_ID=NULL WHERE POSITION_ID="+id+
                ";UPDATE ADMINISTRATION SET POSITION_ID=NULL WHERE POSITION_ID="+id+
                ";UPDATE DOCTORS SET POSITION_ID=NULL WHERE POSITION_ID="+id+
                ";UPDATE CAR_MECHANICS SET POSITION_ID=NULL WHERE POSITION_ID="+id+
                ";DELETE FROM POSITION WHERE POSITION_ID="+id;

        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        constantTablesCRUD.setConnection(connection);

        try {
            constantTablesCRUD.deletePosition(id);

        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    void updatePosition() throws SQLException {
        Position_Entity positionEntity = new Position_Entity();
        positionEntity.setSalary(10000);
        positionEntity.setId(id);
        positionEntity.setPosition("Водій");

        String sql = "UPDATE POSITION SET POSITION=? , SALARY=?  " +
                "WHERE POSITION_ID="+positionEntity.getId();

        when(preparedStatement.execute()).thenReturn(true);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        constantTablesCRUD.setConnection(connection);
        try {
            constantTablesCRUD.updatePosition(positionEntity);
        }catch (Throwable e){
            fail(e.getMessage());
        }

    }

    @Test
    void getDepartments() throws SQLException {
        String sql="SELECT * FROM DEPARTMENT";
        String department="Водії";

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("department")).thenReturn(department).thenReturn("Адміністрація");

        when(statement.executeQuery(sql)).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);
        constantTablesCRUD.setConnection(connection);

        List<Department_Entity> departmentEntityList = constantTablesCRUD.getDepartments();
        assertEquals(departmentEntityList.size(),2);

        Department_Entity departmentEntity = departmentEntityList.get(0);
        assertEquals(departmentEntity.getDepartmentId(),id);
        assertEquals(departmentEntity.getDepartment(),department);
    }

    @Test
    void getWorkBus() throws SQLException {
        String sql="SELECT bus_id,BUS FROM BUS_PARK";
        String bus="BC4040EE";

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("bus_id")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("BUS")).thenReturn(bus).thenReturn("BC5322EK");

        when(statement.executeQuery(sql)).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);
        constantTablesCRUD.setConnection(connection);

        List<Buses_Entity> busesEntityList= constantTablesCRUD.getWorkBus();
        assertEquals(busesEntityList.size(),2);

        Buses_Entity busesEntity = busesEntityList.get(0);
        assertEquals(busesEntity.getBusNo(),bus);
        assertEquals(busesEntity.getId(),id);

    }

    @Test
    void getCarMechanics() throws SQLException {
        String sql = "SELECT S.ID,NAME,SURNAME,PATRONYMIC FROM CAR_MECHANICS cm INNER JOIN STAFF S ON (cm.ID = S.ID )";
        String name="Богдан";
        String surname="Богданов";
        String patronymic="Богданович";

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("id")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("NAME")).thenReturn(name).thenReturn("Олег");
        when(resultSet.getString("SURNAME")).thenReturn(surname).thenReturn("Олегов");
        when(resultSet.getString("PATRONYMIC")).thenReturn(patronymic).thenReturn("Олегович");

        when(statement.executeQuery(sql)).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);
        constantTablesCRUD.setConnection(connection);

        List<Staff_Entity> staffEntityList= constantTablesCRUD.getCarMechanics();
        assertEquals(staffEntityList.size(),2);

        Staff_Entity staffEntity = staffEntityList.get(0);
        assertEquals(staffEntity.getName(),name);
        assertEquals(staffEntity.getSurname(),surname);
        assertEquals(staffEntity.getPatronymic(),patronymic);
        assertEquals(staffEntity.getId(),id);
    }
}