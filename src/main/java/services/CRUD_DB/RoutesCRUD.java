package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.Route_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoutesCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();

    public List<Route_Entity> getRoute() {
        List<Route_Entity> routeEntityList = new ArrayList<>();
        String sql = "SELECT r.id,r.ROUTE,r.AVARAGE_FUEL_CONSUPTION ,r.AVARAGE_PROFIT ,COUNT(bp.BUS_ID ) AS countOfDriver " +
                "FROM ROUTE r left JOIN  BUS_PARK bp  ON (r.ID = bp.ROUTE_ID ) GROUP BY r.ID ";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Route_Entity routeEntity = new Route_Entity();
                routeEntity.setId(resultSet.getInt("ID"));
                routeEntity.setRoute(resultSet.getString("ROUTE"));
                routeEntity.setAvarageFuelConsuption(resultSet.getInt("AVARAGE_FUEL_CONSUPTION"));
                routeEntity.setAvarageProfit(resultSet.getInt("AVARAGE_PROFIT"));
                routeEntity.setCountOfBuses(resultSet.getInt("countOfDriver"));
                routeEntityList.add(routeEntity);
            }
        } catch (SQLException e) {
            logger.error("SQLException in getRoute block:" + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getRoute block(close statement):" + e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getRoute block(close statement):" + e);
            }
        }
        return routeEntityList;
    }

    public void createRoute(Route_Entity routeEntity) {
        String sql = "INSERT INTO ROUTE VALUES (?,?,?,?,NULL)";
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(ID) FROM ROUTE");
            int max = 0;
            if (resultSet.next()) {
                max = resultSet.getInt(1) + 1;
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, max);
            preparedStatement.setString(2, routeEntity.getRoute());
            preparedStatement.setInt(3, routeEntity.getAvarageProfit());
            preparedStatement.setInt(4, routeEntity.getAvarageFuelConsuption());
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQLException in createRoute block:" + e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();}
            } catch (SQLException e) {
                logger.error("SQLException in createRoute block(close statement):" + e); }
            try {
                if (resultSet != null) {
                    resultSet.close();}
            } catch (SQLException e) {
                logger.error("SQLException in createRoute block(close statement):" + e);}
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createRoute block(close statement):" + e);
            }
        }
    }

    public void deleteRoute(int id) {
        String sql = "UPDATE BUS_PARK SET ROUTE_ID = null";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            sql = "DELETE FROM ROUTE WHERE ID = " + id;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQLException in deleteRoute block:" + e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteRoute block(close statement):" + e);

            }
        }
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}

