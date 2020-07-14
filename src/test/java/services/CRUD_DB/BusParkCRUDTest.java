package services.CRUD_DB;

import org.junit.jupiter.api.Test;
import services.Entity.Buses_Entity;
import services.Entity.Route_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BusParkCRUDTest {
    BusParkCRUD busParkCRUD = new BusParkCRUD();

    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);

    int id = 1;
    String bus = "BC4040EE";
    String modelOfBus = "Volvo e90";
    int year = 2001;
    String route = "Львів-Миколаїв";


    @Test
    void readBusPark() throws SQLException {
        String sql = "SELECT * FROM BUS_PARK bp LEFT JOIN ROUTE r ON bp.ROUTE_ID =r.id ";


        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("BUS_ID")).thenReturn(id).thenReturn(id + 1);
        when(resultSet.getString("BUS")).thenReturn(bus).thenReturn(bus + "_col2");
        when(resultSet.getString("MODEL")).thenReturn(modelOfBus).thenReturn(modelOfBus + "_col2");
        when(resultSet.getInt("Year_Of_Issue")).thenReturn(year).thenReturn(year + 1);
        when(resultSet.getInt("ROUTE_ID")).thenReturn(id).thenReturn(id + 1);
        when(resultSet.getString("ROUTE")).thenReturn(route).thenReturn(route + "_col2");


        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);
        busParkCRUD.setConnection(connection);

        List<Buses_Entity> busesEntityList = busParkCRUD.readBusPark();
        Buses_Entity busesEntity = busesEntityList.get(0);

        assertEquals(busesEntityList.size(), 2);

        assertEquals(busesEntity.getRouteId(), id);
        assertEquals(busesEntity.getBusNo(), bus);
        assertEquals(busesEntity.getModel(), modelOfBus);
        assertEquals(busesEntity.getYearOfIssue(), year);
        assertEquals(busesEntity.getRoute(), route);
        assertEquals(busesEntity.getRouteId(), id);

    }

    @Test
    void createBus() throws SQLException {
        String sql = "INSERT INTO BUS_PARK(BUS_ID,BUS,Year_Of_Issue,MODEL,ROUTE_ID) VALUES (?,?,?,?,?)";
        Buses_Entity busesEntity = new Buses_Entity();
        busesEntity.setModel(modelOfBus);
        busesEntity.setId(id);
        busesEntity.setBusNo(bus);
        busesEntity.setYearOfIssue(year);
        busesEntity.setRoute(route);


        List<Route_Entity> routeEntityList = new ArrayList<>();
        Route_Entity routeEntity = new Route_Entity();
        routeEntity.setRoute("Львів-Миколаїв");
        routeEntity.setId(1);
        routeEntityList.add(routeEntity);

        routeEntity = new Route_Entity();
        routeEntity.setRoute("Львів-Київ");
        routeEntity.setId(2);
        routeEntityList.add(routeEntity);


        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(2).thenReturn(4);

        when(statement.executeQuery("SELECT MAX(BUS_ID) FROM BUS_PARK")).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);

        when(preparedStatement.execute()).thenReturn(true);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);



        busParkCRUD.setConnection(connection);
        try {
            busParkCRUD.createBus(busesEntity, routeEntityList);
        }catch (Throwable e){
            fail(e);
        }
    }


    @Test
    void createBusWithNull() {
        assertThrows(NullPointerException.class,()-> busParkCRUD.createBus(null,null));

    }

    @Test
    void deleteBus() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement("UPDATE BUS_DRIVERS SET WORK_BUS_ID=NULL WHERE WORK_BUS_ID =" + id))
                .thenReturn(preparedStatement);


        when(connection.prepareStatement("delete from BUS_PARK where bus_id=" + id))
                .thenReturn(preparedStatement);


        busParkCRUD.setConnection(connection);


        try {
            busParkCRUD.deleteBus(id);
        } catch (Throwable e) {
            fail(e);
        }

    }
}