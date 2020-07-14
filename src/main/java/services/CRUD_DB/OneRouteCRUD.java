package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.Buses_Entity;
import services.Entity.Route_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OneRouteCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();


    public Route_Entity getRoute(int id) {
        String sql = "SELECT * FROM ROUTE where id=" + id;
        Route_Entity routeEntity = new Route_Entity();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                routeEntity.setId(id);
                routeEntity.setFullRoute(resultSet.getString("FULL_ROUTE"));
                routeEntity.setRoute(resultSet.getString("ROUTE"));
                routeEntity.setAvarageProfit(resultSet.getInt("Avarage_Profit"));
                routeEntity.setAvarageFuelConsuption(resultSet.getInt("Avarage_Fuel_Consuption"));
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

    public List<Buses_Entity> getBuses(int id) {
        String sql = "SELECT * FROM BUS_PARK WHERE ROUTE_ID=" + id;
        List<Buses_Entity> busParkList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Buses_Entity busesEntity = new Buses_Entity();
                busesEntity.setId(resultSet.getInt("bus_id"));
                busesEntity.setBusNo(resultSet.getString("Bus"));
                busParkList.add(busesEntity);
            }

        } catch (SQLException e) {
            logger.error("SQLException in getBuses block:"+e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getBuses block(close statement):"+e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getBuses block(close resultSet):"+e);

            }
        }

        return busParkList;
    }

    public void updateRoute(int id, Route_Entity routeEntity) {
        String sql = "UPDATE ROUTE SET ROUTE=?,FULL_ROUTE=?,avarage_Profit=?,avarage_Fuel_Consuption=? where id=" + id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, routeEntity.getRoute());
            preparedStatement.setString(2, routeEntity.getFullRoute());
            preparedStatement.setInt(3, routeEntity.getAvarageProfit());
            preparedStatement.setInt(4, routeEntity.getAvarageFuelConsuption());
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in updateRoute block:"+e);

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateRoute block(close preparedStatement):"+e);

            }

        }
    }

    public void deleteRoute(int id) {
        String sql = "UPDATE ROUTE SET,FULL_ROUTE=null,avarage_Profit=null,avarage_Fuel_Consuption=null where id=" + id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in deleteRoute block:"+e);

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteRoute block(close preparedStatement):"+e);

            }


        }

    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
