package services.CRUD_DB;

import org.junit.jupiter.api.Test;
import services.Entity.Buses_Entity;
import services.Entity.Route_Entity;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OneRouteCRUDTest {
    OneRouteCRUD oneRouteCRUD = new OneRouteCRUD();

    Connection connection = mock(Connection.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);

    int id=1;
    @Test
    void getRoute() throws SQLException {
        String sql = "SELECT * FROM ROUTE where id=" + id;


        String route= "Львів-Миколаїв";
        int avarageFuelConsuption =12;
        int avarageProfit= 10000;
       String fullRoute= "Львів-Солонка-Раковець-Липівка-Миколаїв";


        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("FULL_ROUTE")).thenReturn(fullRoute);
        when(resultSet.getString("ROUTE")).thenReturn(route);
        when(resultSet.getInt("Avarage_Profit")).thenReturn(avarageProfit);
        when(resultSet.getInt("Avarage_Fuel_Consuption")).thenReturn(avarageFuelConsuption);

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        oneRouteCRUD.setConnection(connection);

        Route_Entity routeEntity = oneRouteCRUD.getRoute(id);

        assertEquals(routeEntity.getRoute(),route);
        assertEquals(routeEntity.getFullRoute(),fullRoute);
        assertEquals(routeEntity.getAvarageProfit(),avarageProfit);
        assertEquals(routeEntity.getAvarageFuelConsuption(),avarageFuelConsuption);

    }

    @Test
    void getBuses() throws SQLException {
        String sql = "SELECT * FROM BUS_PARK WHERE ROUTE_ID=" + id;
        String bus = "BC4040EE";

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("bus_id")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("Bus")).thenReturn(bus).thenReturn("BC4123EE");

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        oneRouteCRUD.setConnection(connection);

        List<Buses_Entity> busesEntityList = oneRouteCRUD.getBuses(id);
        assertEquals(busesEntityList.size(),2);

        Buses_Entity busesEntity = busesEntityList.get(0);
        assertEquals(busesEntity.getBusNo(),bus);
        assertEquals(busesEntity.getId(),id);
    }

    @Test
    void updateRoute() throws SQLException {
        String sql = "UPDATE ROUTE SET ROUTE=?,FULL_ROUTE=?,avarage_Profit=?,avarage_Fuel_Consuption=? where id=" + id;
        Route_Entity routeEntity = new Route_Entity();
        routeEntity.setRoute("Львів-Миколаїв");
        routeEntity.setAvarageFuelConsuption(12);
        routeEntity.setAvarageProfit(10000);
        routeEntity.setFullRoute("Львів-Солонка-Раковець-Липівка-Миколаїв");

        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        oneRouteCRUD.setConnection(connection);


        try {
            oneRouteCRUD.updateRoute(id,routeEntity);
        }catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    void updateRouteWithNull(){
        assertThrows(NullPointerException.class ,()->oneRouteCRUD.updateRoute(id,null) );
    }


    @Test
    void deleteRoute() throws SQLException {
        String sql = "UPDATE ROUTE SET,FULL_ROUTE=null,avarage_Profit=null,avarage_Fuel_Consuption=null where id=" + id;

        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        oneRouteCRUD.setConnection(connection);

        try {
            oneRouteCRUD.deleteRoute(id);
        }catch (Throwable e){
            fail(e.getMessage());
        }
    }
}