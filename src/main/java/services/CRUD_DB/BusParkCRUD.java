package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.Buses_Entity;
import services.Entity.Route_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BusParkCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();


    public List<Buses_Entity> readBusPark() {
        List<Buses_Entity> busParkEntityList = new ArrayList<>();
        String sql = "SELECT * FROM BUS_PARK bp LEFT JOIN ROUTE r ON bp.ROUTE_ID =r.id ";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Buses_Entity busesEntity = new Buses_Entity();
                busesEntity.setId(resultSet.getInt("BUS_ID"));
                busesEntity.setBusNo(resultSet.getString("BUS"));
                busesEntity.setModel(resultSet.getString("MODEL"));
                busesEntity.setYearOfIssue(resultSet.getInt("Year_Of_Issue"));

                busesEntity.setRouteId(resultSet.getInt("ROUTE_ID"));
                busesEntity.setRoute(resultSet.getString("ROUTE"));
                busParkEntityList.add(busesEntity);
            }


        } catch (SQLException e) {
            logger.error("SQLException in readBusPark block:"+e);

        } finally {

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in readBusPark block(close statement):"+e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in readBusPark block(close resultSet):"+e);

            }
        }
        return busParkEntityList;
    }


    public void createBus(Buses_Entity busesEntity, List<Route_Entity> list) {
        String sql = "INSERT INTO BUS_PARK(BUS_ID,BUS,Year_Of_Issue,MODEL,ROUTE_ID) VALUES (?,?,?,?,?)";
        int routeid = 0;
        for (Route_Entity routeEntity : list) {
            if (busesEntity.getRoute().equals(routeEntity.getRoute())) {
                routeid = routeEntity.getId();
            }
        }
        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(BUS_ID) FROM BUS_PARK");
            int max = 0;
            if (resultSet.next()) {
                max = resultSet.getInt(1) + 1;
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, max);
            preparedStatement.setString(2, busesEntity.getBusNo());
            preparedStatement.setInt(3, busesEntity.getYearOfIssue());
            preparedStatement.setString(4, busesEntity.getModel());
            preparedStatement.setInt(5, routeid);
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in createBus block:"+e);

        } finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createBus block(close preparedStatement):"+e);

            }

            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createBus block(close statement):"+e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createBus block(close resultSet):"+e);

            }
        }

    }


    public void deleteBus(int id) {
        String sql = "UPDATE BUS_DRIVERS SET WORK_BUS_ID=NULL WHERE WORK_BUS_ID =" + id;
        PreparedStatement preparedStatement=null;
        try {
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            sql = "delete from BUS_PARK where bus_id=" + id;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in deleteBus block:"+e);

        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteBus block(close preparedStatement):"+e);

            }
        }

    }

}
