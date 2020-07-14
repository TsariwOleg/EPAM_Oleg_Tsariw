package services.CRUD_DB;

import org.junit.jupiter.api.Test;
import services.Entity.Route_Entity;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoutesCRUDTest {
    RoutesCRUD routesCRUD = new RoutesCRUD();

    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    int id = 1;

    @Test
    void getRoute() throws SQLException {
        String sql = "SELECT r.id,r.ROUTE,r.AVARAGE_FUEL_CONSUPTION ,r.AVARAGE_PROFIT ,COUNT(bp.BUS_ID ) AS countOfDriver " +
                "FROM ROUTE r left JOIN  BUS_PARK bp  ON (r.ID = bp.ROUTE_ID ) GROUP BY r.ID ";

        String route= "Львів-Миколаїв";
        int avarageFuelConsuption =12;
        int avarageProfit= 10000;

        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("ID")).thenReturn(id).thenReturn(2);
        when(resultSet.getString("ROUTE")).thenReturn(route).thenReturn( "Львів-Київ");
        when(resultSet.getInt("AVARAGE_FUEL_CONSUPTION")).thenReturn(avarageFuelConsuption).thenReturn(20);
        when(resultSet.getInt("AVARAGE_PROFIT")).thenReturn(avarageProfit).thenReturn(12000);
        when(resultSet.getInt("countOfDriver")).thenReturn(2);

        when(statement.executeQuery(sql)).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        routesCRUD.setConnection(connection);

        List<Route_Entity> routeEntityList = routesCRUD.getRoute();
        assertEquals(routeEntityList.size(),2);

        Route_Entity routeEntity = routeEntityList.get(0);
        assertEquals(routeEntity.getId(),id);
        assertEquals(routeEntity.getAvarageFuelConsuption(),avarageFuelConsuption);
        assertEquals(routeEntity.getAvarageProfit(),avarageProfit);
        assertEquals(routeEntity.getCountOfBuses(),2);
        assertEquals(routeEntity.getRoute(),route);
    }

    @Test
    void createRoute() throws SQLException {
        String sql = "INSERT INTO ROUTE VALUES (?,?,?,?,NULL)";


        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt(1)).thenReturn(3);

        when(statement.executeQuery("SELECT MAX(ID) FROM ROUTE")).thenReturn(resultSet);

        when(connection.createStatement()).thenReturn(statement);

        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        routesCRUD.setConnection(connection);

        Route_Entity routeEntity = new Route_Entity();
        routeEntity.setFullRoute( "Львів-Солонка-Раковець-Липівка-Миколаїв");
        routeEntity.setAvarageProfit(10000);
        routeEntity.setAvarageFuelConsuption(12);
        routeEntity.setRoute("Львів-Миколаїв");

        try {
            routesCRUD.createRoute(routeEntity);
        }catch (Throwable e){
            fail(e.getMessage());
        }
    }

    @Test
    void createRouteWithNull(){
        assertThrows(NullPointerException.class, ()->routesCRUD.createRoute(null));
    }

    @Test
    void deleteRoute() throws SQLException {
        String sql = "UPDATE BUS_PARK SET ROUTE_ID = null";


        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        when(connection.prepareStatement("DELETE FROM ROUTE WHERE ID = "+id)).thenReturn(preparedStatement);

        routesCRUD.setConnection(connection);
        try {
            routesCRUD.deleteRoute(id);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }
}