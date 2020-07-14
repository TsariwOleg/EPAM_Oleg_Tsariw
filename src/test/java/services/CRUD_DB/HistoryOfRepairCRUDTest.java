package services.CRUD_DB;

import org.junit.jupiter.api.Test;
import services.Entity.Buses_Entity;
import services.Entity.HistoryOfRepair_Entity;
import services.Entity.Staff_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HistoryOfRepairCRUDTest {
    HistoryOfRepairCRUD historyOfRepairCRUD = new HistoryOfRepairCRUD();

    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    int id = 1;

    @Test
    void getHistory() throws SQLException {
        String sql = "SELECT * FROM REPAIRED_BUS rb inner join BUS_PARK bp INNER JOIN STAFF on (rb.BUS_ID=" + id + " AND  bp.BUS_ID=" + id + ")" +
                " AND (rb.MECHANIC_ID =staff.id)";

        String target = "bus";
        String malfunction="Заміна масла";
        int costOfRepaire = 500;
        String note = "Заміна масла була замінена успішно";
        String bus = "BC4040EE";
        String name= "Влад";
        String surname="Мельник";
        String patronymic = "Богданович";


        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("REPAIRENO")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("MALFUNCTION")).thenReturn(malfunction).thenReturn("Заміна мотора");
        when(resultSet.getInt("COST_OF_REPAIRE")).thenReturn(costOfRepaire).thenReturn(1000000);
        when(resultSet.getString("NOTE")).thenReturn(note).thenReturn("");
        when(resultSet.getInt("BUS_ID")).thenReturn(id).thenReturn(2);
        when(resultSet.getInt("MECHANIC_ID")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("BUS")).thenReturn(bus).thenReturn("AA3232EE");
        when(resultSet.getString("NAME")).thenReturn(name).thenReturn("Ігор");
        when(resultSet.getString("SURNAME")).thenReturn(surname).thenReturn("Стельмах");
        when(resultSet.getString("patronymic")).thenReturn(patronymic).thenReturn("Ігорович");

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        historyOfRepairCRUD.setConnection(connection);

        List<HistoryOfRepair_Entity> historyOfRepairEntities= historyOfRepairCRUD.getHistory(id,target);
        assertEquals(historyOfRepairEntities.size(),2);

        HistoryOfRepair_Entity historyOfRepairEntity = historyOfRepairEntities.get(0);
        assertEquals(historyOfRepairEntity.getBus(),bus);
        assertEquals(historyOfRepairEntity.getBus_id(),id);
        assertEquals(historyOfRepairEntity.getMechanic_id(),id);
        assertEquals(historyOfRepairEntity.getNSP(),name+" "+surname+" "+patronymic);
        assertEquals(historyOfRepairEntity.getCostOfRepair(),costOfRepaire);
        assertEquals(historyOfRepairEntity.getMalfunction(),malfunction);
        assertEquals(historyOfRepairEntity.getNote(),note);
    }

    @Test
    void createHistory() throws SQLException {
        String sql = "INSERT INTO REPAIRED_BUS VALUES (?,?,?,?,?,?)";

        HistoryOfRepair_Entity historyOfRepairEntity =new HistoryOfRepair_Entity();
        historyOfRepairEntity.setBus_id(id);
        historyOfRepairEntity.setBus("BC4040EE");
        historyOfRepairEntity.setMechanic_id(id);
        historyOfRepairEntity.setNSP("Богдан Мельник Богданович");
        historyOfRepairEntity.setMalfunction("Заміна мастла");
        historyOfRepairEntity.setCostOfRepair(10000);


        Map constantTables = new HashMap();
        List<Buses_Entity> busesEntityList = new ArrayList<>();
        Buses_Entity busesEntity = new Buses_Entity();
        busesEntity.setId(id);
        busesEntity.setBusNo("BC4040EE");
        busesEntityList.add(busesEntity);

        busesEntity=new Buses_Entity();
        busesEntity.setId(2);
        busesEntity.setBusNo("BC2340EE");
        busesEntityList.add(busesEntity);
        constantTables.put("bus",busesEntityList);



        List<Staff_Entity> staffEntityList=new ArrayList<>();
        Staff_Entity staffEntity = new Staff_Entity();
        staffEntity.setId(id);
        staffEntity.setName("Богдан");
        staffEntity.setSurname("Мельник");
        staffEntity.setPatronymic("Богданович");
        staffEntityList.add(staffEntity);

        staffEntity = new Staff_Entity();
        staffEntity.setId(2);
        staffEntity.setName("Юра");
        staffEntity.setSurname("Мельник");
        staffEntity.setPatronymic("Богданович");
        staffEntityList.add(staffEntity);
        constantTables.put("carmechanics",staffEntityList);

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(5);

        when(statement.executeQuery("SELECT MAX(REPAIRENO) FROM REPAIRED_BUS")).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);


        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        historyOfRepairCRUD.setConnection(connection);

        historyOfRepairCRUD.createHistory(historyOfRepairEntity,constantTables);


    }

    @Test
    void deleteHistory() throws SQLException {
        String sql = "DELETE FROM REPAIRED_BUS WHERE REPAIRENO=" + id;


        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        historyOfRepairCRUD.setConnection(connection);

        try {
            historyOfRepairCRUD.deleteHistory(id);

        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }
}