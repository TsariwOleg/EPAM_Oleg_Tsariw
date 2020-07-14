package services.CRUD_DB;


import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;

import services.Entity.Department_Entity;
import services.Entity.Staff_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StaffCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();



    public List<Staff_Entity> getStaff() {
        String sql = "SELECT * FROM STAFF s LEFT JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.ID";
        List<Staff_Entity> staffEntityList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {

            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Staff_Entity staffEntity = new Staff_Entity();
                staffEntity.setName(resultSet.getString("NAME"));
                staffEntity.setSurname(resultSet.getString("SURNAME"));
                staffEntity.setPatronymic(resultSet.getString("PATRONYMIC"));
                staffEntity.setAge(resultSet.getInt("AGE"));
                staffEntity.setId(resultSet.getInt("ID"));
                staffEntity.setDepartment(resultSet.getString("DEPARTMENT"));
                staffEntityList.add(staffEntity);

            }
        } catch (SQLException e) {
            logger.error("SQLException in getStaff block:" + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getStaff block(close statement):" + e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getStaff block(close resultSet):" + e);

            }
        }
        return staffEntityList;
    }

    public void createPerson(Staff_Entity staffEntity, Map constantTables) {
        List<Department_Entity> department_entityList = (ArrayList) constantTables.get("departments");
        int department_id = 0;
        String sql = "insert  INTO STAFF(id,NAME,SURNAME,PATRONYMIC,AGE,DEPARTMENT_ID) VALUES(?,?,?,?,?,?)";


        for (Department_Entity departmentEntity : department_entityList) {
            if (departmentEntity.getDepartment().equals(staffEntity.getDepartment())) {
                department_id = departmentEntity.getDepartmentId();
            }
        }

        Statement statement = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(id) FROM staff");
            int max = 0;
            if (resultSet.next()) {
                max = resultSet.getInt(1);
            }

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, ++max);
            preparedStatement.setString(2, staffEntity.getName());
            preparedStatement.setString(3, staffEntity.getSurname());
            preparedStatement.setString(4, staffEntity.getPatronymic());
            preparedStatement.setInt(5, staffEntity.getAge());
            preparedStatement.setInt(6, department_id);
            preparedStatement.execute();

            sql = "INSERT INTO PASSPORT (ID) VALUES(" + max + ");" +
                    "INSERT INTO MEDICAL_BOOK(ID) VALUES (" + max + ");" +
                    "INSERT INTO TAXPAYER_CARD(ID) VALUES (" + max + ");";

            if (department_id == 2) {
                sql = sql + "INSERT INTO BUS_DRIVERS(id) VALUES(" + max + ")";
            }

            if (department_id == 3) {
                sql = sql + "INSERT INTO Car_MECHANICS(id) VALUES(" + max + ")";
            }
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();


        } catch (SQLException e) {
            logger.error("SQLException in createPerson block:" + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createPerson block(close statement):" + e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createPerson block(close resultSet):" + e);
            }

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createPerson block(close preparedStatement):" + e);

            }
        }
    }

    public void deletePerson(int id) {
        String sql = "DELETE FROM PASSPORT WHERE ID=" + id + ";" +
                "DELETE FROM MEDICAL_BOOK WHERE ID=" + id + ";" +
                "DELETE FROM TAXPAYER_CARD WHERE ID=" + id + ";" +
                "DELETE FROM CONTACT WHERE ID=" + id + ";" +
                "DELETE FROM ADMINISTRATION WHERE ID=" + id + ";" +
                "DELETE FROM DOCTORS WHERE ID=" + id + ";" +
                "DELETE FROM BUS_DRIVERS WHERE ID=" + id + ";" +
                "DELETE FROM ACCESS_TO_WEB WHERE ID=" + id + ";" +
                "UPDATE REPAIRED_BUS SET MECHANIC_ID=NULL WHERE MECHANIC_ID=" + id + ";" +
                "DELETE FROM CAR_MECHANICS WHERE ID=" + id + ";" +
                "DELETE FROM STAFF WHERE ID=" + id + ";" +
                "UPDATE REPAIRED_BUS SET Mechanic_ID=null where Mechanic_ID=" + id;

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in deletePerson block:" + e);

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deletePerson block(close preparedStatement):" + e);
            }
        }
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
