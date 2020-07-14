package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.Buses_Entity;
import services.Entity.HistoryOfRepair_Entity;
import services.Entity.Staff_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryOfRepairCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();

    public List<HistoryOfRepair_Entity> getHistory(int id, String target) {
        String sql = "";
        List<HistoryOfRepair_Entity> historyOfRepairList = new ArrayList<>();

        if (("bus").equals(target)) {
            sql = "SELECT * FROM REPAIRED_BUS rb inner join BUS_PARK bp INNER JOIN STAFF on (rb.BUS_ID=" + id + " AND  bp.BUS_ID=" + id + ")" +
                    " AND (rb.MECHANIC_ID =staff.id)";
        }
        if (("carMechanic").equals(target)) {
            sql = "SELECT * FROM REPAIRED_BUS  inner join STAFF on (Mechanic_ID=" + id + " AND id=" + id + ")" +
                    " INNER JOIN BUS_PARK on(REPAIRED_BUS.BUS_ID =BUS_PARK.BUS_ID )";
        }
        if (("all").equals(target)) {
            sql = "SELECT * FROM REPAIRED_BUS rb inner join BUS_PARK bp ON (rb.BUS_ID = bp.BUS_ID)" +
                    " left join STAFF s ON (s.ID = rb.MECHANIC_ID )";
        }

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {

                HistoryOfRepair_Entity historyOfRepairEntity = new HistoryOfRepair_Entity();
                historyOfRepairEntity.setRepairedNo(resultSet.getInt("REPAIRENO"));
                historyOfRepairEntity.setMalfunction(resultSet.getString("MALFUNCTION"));
                historyOfRepairEntity.setCostOfRepair(resultSet.getInt("COST_OF_REPAIRE"));
                historyOfRepairEntity.setNote(resultSet.getString("NOTE"));
                historyOfRepairEntity.setBus_id(resultSet.getInt("BUS_ID"));
                historyOfRepairEntity.setMechanic_id(resultSet.getInt("MECHANIC_ID"));

                historyOfRepairEntity.setBus(resultSet.getString("BUS"));


                historyOfRepairEntity.setNSP(resultSet.getString("NAME") + " " +
                        resultSet.getString("SURNAME") + " " +
                        resultSet.getString("patronymic"));

                if (historyOfRepairEntity.getNSP().equals("null null null")){
                    historyOfRepairEntity.setNSP("Пусто");
                }

                historyOfRepairList.add(historyOfRepairEntity);
            }
        } catch (SQLException e) {
            logger.error("SQLException in getCheckUp block:" + e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getHistory block(close statement):" + e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getHistory block(close resultSet):" + e);

            }
        }

        return historyOfRepairList;
    }


    public void createHistory(HistoryOfRepair_Entity historyOfRepairEntity, Map constantTable) {
        String sql = "INSERT INTO REPAIRED_BUS VALUES (?,?,?,?,?,?)";
        List<Buses_Entity> busesEntityList = (ArrayList) constantTable.get("bus");
        List<Staff_Entity> staffEntityList = (ArrayList) constantTable.get("carmechanics");
        int busId = historyOfRepairEntity.getBus_id();
        int carmechanicId = historyOfRepairEntity.getMechanic_id();

        int repaireNo = 1;


        if (historyOfRepairEntity.getBus_id() == 0) {
            for (Buses_Entity bus : busesEntityList) {
                if (historyOfRepairEntity.getBus().equals(bus.getBusNo())) {
                    busId = bus.getId();
                }
            }
        }


        if (historyOfRepairEntity.getMechanic_id() == 0) {
            for (Staff_Entity staffEntity : staffEntityList) {
                String NSP = staffEntity.getName() +
                        " " + staffEntity.getSurname() +
                        " " + staffEntity.getPatronymic();
                if (historyOfRepairEntity.getNSP().equals(NSP)) {
                    carmechanicId = staffEntity.getId();
                }
            }
        }

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(REPAIRENO) FROM REPAIRED_BUS");
            if (resultSet.next()) {
                repaireNo = resultSet.getInt(1) + 1;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, repaireNo);
            preparedStatement.setString(2, historyOfRepairEntity.getMalfunction());
            preparedStatement.setInt(3, historyOfRepairEntity.getCostOfRepair());
            preparedStatement.setString(4, historyOfRepairEntity.getNote());
            preparedStatement.setInt(5, busId);
            preparedStatement.setInt(6, carmechanicId);
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in createHistory block:" + e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createHistory block(close statement):" + e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createHistory block(close resultSet):" + e);

            }
        }

    }


    public void deleteHistory(int id) {
        String sql = "DELETE FROM REPAIRED_BUS WHERE REPAIRENO=" + id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            logger.error("SQLException in deleteHistory block:" + e);

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteHistory block(close preparedStatement):" + e);

            }

        }

    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
