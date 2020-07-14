package services.CRUD_DB;

import org.junit.jupiter.api.Test;
import services.Entity.Buses_Entity;
import services.Entity.Route_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BusCRUDTest {
    BusCRUD busCRUD = new BusCRUD();

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);

    int id = 1;


    @Test
    void updateBus() throws SQLException {
        String sql = "UPDATE BUS_PARK SET BUS=? , MODEL=? , YEAR_OF_ISSUE=? ,FUEL_CONSUMPTION=? , ROUTE_ID=? " +
                "WHERE BUS_ID = " + id;
        Buses_Entity busesEntity = new Buses_Entity();
        busesEntity.setBusNo("BC4040EE");
        busesEntity.setId(id);
        busesEntity.setRoute("Львів-Миколаїв");
        busesEntity.setModel("Volvo e90");

        Route_Entity routeEntity = new Route_Entity();
        List<Route_Entity> routeEntityList = new ArrayList<>();
        routeEntity.setRoute("Львів-Київ");
        routeEntityList.add(routeEntity);

        routeEntity.setRoute("Львів-Миколаїв");
        routeEntityList.add(routeEntity);

        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        busCRUD.setConnection(connection);


        try {
            busCRUD.updateBus(id, busesEntity, routeEntityList);
        } catch (Throwable e) {
            fail(e);
        }


    }

    @Test
    void deleteBus() throws SQLException {
        String sql = "UPDATE BUS_PARK SET YEAR_OF_ISSUE=NULL,MODEL=NULL," +
                "FUEL_CONSUMPTION=NULL,ROUTE_ID=NULL WHERE BUS_ID=" + id;
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        busCRUD.setConnection(connection);

        try {
            busCRUD.deleteBus(id);
        }catch (Throwable e){
            fail(e);
        }
    }

    @Test
    void getBusEntity() throws SQLException {
        String sql = "SELECT * FROM BUS_PARK WHERE BUS_ID=" + id;
        String bus = "BC4040EE";
        String modelOfBus = "Volvo e90";
        int year = 2001;
        String fuelConsuption = "10/100km";

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("BUS_ID")).thenReturn(id);
        when(resultSet.getString("BUS")).thenReturn(bus);
        when(resultSet.getString("MODEL")).thenReturn(modelOfBus);
        when(resultSet.getInt("YEAR_OF_ISSUE")).thenReturn(year);
        when(resultSet.getString("FUEL_CONSUMPTION")).thenReturn(fuelConsuption);
        when(resultSet.getInt("ROUTE_ID")).thenReturn(id);

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        busCRUD.setConnection(connection);

        Buses_Entity busesEntity = busCRUD.getBusEntity(id);
        assertEquals(busesEntity.getId(), id);
        assertEquals(busesEntity.getModel(), modelOfBus);
        assertEquals(busesEntity.getYearOfIssue(), year);
        assertEquals(busesEntity.getFuelConsumption(), fuelConsuption);
        assertEquals(busesEntity.getBusNo(), bus);
        assertEquals(busesEntity.getRouteId(), id);

    }

    @Test
    void getRoute() throws SQLException {
        String sql = "SELECT * FROM ROUTE WHERE ID=" + id;

        String route = "Львів-Миколаїв";
        int avarageProfit = 10000;
        int avarageFuelConsuption = 10;
        String fullRoute = "Львів-Солонка-Раківець-Липівка-Миколаїв";

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("ROUTE")).thenReturn(route);
        when(resultSet.getInt("AVARAGE_PROFIT")).thenReturn(avarageProfit);
        when(resultSet.getInt("AVARAGE_FUEL_CONSUPTION")).thenReturn(avarageFuelConsuption);
        when(resultSet.getString("FULL_ROUTE")).thenReturn(fullRoute);

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        busCRUD.setConnection(connection);
        Route_Entity routeEntity = busCRUD.getRoute(id);

        assertEquals(routeEntity.getRoute(), route);
        assertEquals(routeEntity.getAvarageFuelConsuption(), avarageFuelConsuption);
        assertEquals(routeEntity.getFullRoute(), fullRoute);
        assertEquals(routeEntity.getAvarageProfit(), avarageProfit);


    }
}