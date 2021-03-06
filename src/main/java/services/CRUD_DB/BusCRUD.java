package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.Buses_Entity;
import services.Entity.Route_Entity;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();

    public Map getBus(int id) {
        Map map = new HashMap();
        map.put("bus", getBusEntity(id));
        map.put("route", getRoute(((Buses_Entity) map.get("bus")).getRouteId()));
        return map;
    }


    public void updateBus(int id, Buses_Entity busesEntity, List<Route_Entity> routeEntityList) {
        String sql = "UPDATE BUS_PARK SET BUS=? , MODEL=? , YEAR_OF_ISSUE=? ,FUEL_CONSUMPTION=? , ROUTE_ID=? " +
                "WHERE BUS_ID = " + id;
        int routeId = 0;
        for (Route_Entity routeEntity : routeEntityList) {
            if (routeEntity.getRoute().equals(busesEntity.getRoute())) {
                routeId = routeEntity.getId();

            }
        }
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, busesEntity.getBusNo());
            preparedStatement.setString(2, busesEntity.getModel());
            preparedStatement.setInt(3, busesEntity.getYearOfIssue());
            preparedStatement.setString(4, busesEntity.getFuelConsumption());
            preparedStatement.setInt(5, routeId);
            preparedStatement.execute();
        } catch (SQLException e) {
           logger.error("SQLException in updateBus block:"+e);
        } finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateBus block(close preparedStatement):"+e);
            }
        }


    }

    public void deleteBus(int id) {
        String sql = "UPDATE BUS_PARK SET YEAR_OF_ISSUE=NULL,MODEL=NULL," +
                "FUEL_CONSUMPTION=NULL,ROUTE_ID=NULL WHERE BUS_ID=" + id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in deleteBus block:"+e);
        } finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteBus block(close preparedStatement):"+e);
            }
        }

    }


    public Buses_Entity getBusEntity(int id) {
        String sql = "SELECT * FROM BUS_PARK WHERE BUS_ID=" + id;
        Buses_Entity busesEntity = new Buses_Entity();
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            statement=connection.createStatement();
            resultSet=statement.executeQuery(sql);
            if (resultSet.next()) {
                busesEntity.setId(resultSet.getInt("BUS_ID"));
                busesEntity.setBusNo(resultSet.getString("BUS"));
                busesEntity.setModel(resultSet.getString("MODEL"));
                busesEntity.setYearOfIssue(resultSet.getInt("YEAR_OF_ISSUE"));
                busesEntity.setFuelConsumption(resultSet.getString("FUEL_CONSUMPTION"));
                busesEntity.setRouteId(resultSet.getInt("ROUTE_ID"));
            }


        } catch (SQLException e) {
            logger.error("SQLException in getBusEntity block:"+e);

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getBusEntity block(close statement):"+e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateBus block(close resultSet):"+e);

            }


        }

        return busesEntity;
    }


    public Route_Entity getRoute(int id) {
        String sql = "SELECT * FROM ROUTE WHERE ID=" + id;
        Route_Entity routeEntity = new Route_Entity();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                routeEntity.setRoute(resultSet.getString("ROUTE"));
                routeEntity.setAvarageProfit(resultSet.getInt("AVARAGE_PROFIT"));
                routeEntity.setAvarageFuelConsuption(resultSet.getInt("AVARAGE_FUEL_CONSUPTION"));
                routeEntity.setFullRoute(resultSet.getString("FULL_ROUTE"));
            }
        } catch (SQLException e) {
            logger.error("SQLException in getRoute block:"+e);

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getRoute block(close statement):"+e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getRoute block(close resultSet):"+e);

            }
        }

        return routeEntity;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
