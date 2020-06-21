package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.DepartmentPage_Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DepartmentCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();


    public DepartmentPage_Entity getForDepartmentPage() {
        String sql = "SELECT COUNT(*) FROM STAFF WHERE DEPARTMENT_ID=1";
        DepartmentPage_Entity departmentPageEntity = new DepartmentPage_Entity();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                departmentPageEntity.setCountOfAdministrations(resultSet.getInt(1));
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT SUM(SALARY) FROM ADMINISTRATION a INNER JOIN POSITION " +
                    "ON (a.POSITION_ID = POSITION.POSITION_ID ) ");
            try {
                if (resultSet.next()) {
                    departmentPageEntity.setAvgSallaryOfAdministrations(resultSet.getInt(1) /
                            departmentPageEntity.getCountOfAdministrations());
                }

            } catch (ArithmeticException e) {
                logger.error("ArithmeticException in getForDepartmentPage block:" + e);

            }


            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM STAFF WHERE DEPARTMENT_ID=2");
            if (resultSet.next()) {
                departmentPageEntity.setCountOfDrivers(resultSet.getInt(1));
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT SUM(SALARY) FROM BUS_DRIVERS bd  INNER JOIN POSITION " +
                    "   ON (bd.POSITION_ID = POSITION.POSITION_ID )");
            try {
                if (resultSet.next()) {
                    departmentPageEntity.setAvgSallaryOfDrivers(resultSet.getInt(1) /
                            departmentPageEntity.getCountOfDrivers());
                }
            } catch (ArithmeticException e) {
                logger.error("ArithmeticException in getForDepartmentPage block:" + e);

            }


            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM STAFF WHERE DEPARTMENT_ID=3");
            if (resultSet.next()) {
                departmentPageEntity.setCountOfMechanics(resultSet.getInt(1));
            }
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT SUM(SALARY) FROM CAR_MECHANICS cm  INNER JOIN POSITION " +
                    "   ON (cm.POSITION_ID = POSITION.POSITION_ID )");
            try {
                if (resultSet.next()) {
                    departmentPageEntity.setAvgSallaryOfMechanics(resultSet.getInt(1) /
                            departmentPageEntity.getCountOfMechanics());
                }
            } catch (ArithmeticException e) {
                logger.error("ArithmeticException in getForDepartmentPage block:" + e);

            }


            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT COUNT(*) FROM STAFF WHERE DEPARTMENT_ID=4");
            if (resultSet.next()) {
                departmentPageEntity.setCountOfDoctors(resultSet.getInt(1));
            }

            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT SUM(SALARY) FROM DOCTORS doc  INNER JOIN POSITION " +
                    "   ON (doc.POSITION_ID = POSITION.POSITION_ID )");
            try {
                if (resultSet.next()) {
                    departmentPageEntity.setAvgSallaryOfDoctors(resultSet.getInt(1) /
                            departmentPageEntity.getCountOfDoctors());
                }
            } catch (ArithmeticException e) {
                logger.error("ArithmeticException in getForDepartmentPage block:" + e);

            }


        } catch (SQLException e) {
            logger.error("SQLException in getForDepartmentPage block:" + e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getForDepartmentPage block(close statement):" + e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getForDepartmentPage block(close resultSet):" + e);
            }
        }

        return departmentPageEntity;
    }

}
