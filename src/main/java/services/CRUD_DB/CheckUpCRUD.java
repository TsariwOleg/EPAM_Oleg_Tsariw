package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.CheckUp_Entity;
import services.Entity.Staff_Entity;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CheckUpCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    String formattedDate = "";
    private final Logger logger = Logger.getRootLogger();

    public List<CheckUp_Entity> getCheckUp() {
        List<CheckUp_Entity> checkUpEntityList = new ArrayList<>();

        String createTable = "CREATE TABLE IF NOT EXISTS CHECKUP_" + formattedDate + " (id int primary key," +
                "pressure varchar(16)," +
                "ppm varchar(10) ," +
                "wellBeing varchar(5)," +
                "note varchar(1000)," +
                "doctorId int," +
                "idPerson int" +
                ")";

        String sql = "SELECT C.ID,PRESSURE,PPM,WELLBEING,NOTE,IDPERSON ,S1.NAME,S1.SURNAME ,S1.PATRONYMIC, " +
                "DOCTORID,S2.NAME,S2.SURNAME ,S2.PATRONYMIC  FROM CHECKUP_" + formattedDate + " c " +
                "INNER JOIN STAFF s1 ON C.IDPERSON=s1.ID " +
                "INNER JOIN STAFF s2 ON C.DOCTORID =s2.ID";

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(createTable);
            preparedStatement.execute();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                CheckUp_Entity checkUpEntity = new CheckUp_Entity();
                checkUpEntity.setId(resultSet.getInt(1));
                checkUpEntity.setPressure(resultSet.getString(2));
                checkUpEntity.setPpm(resultSet.getString(3));
                checkUpEntity.setWellBeing(resultSet.getString(4));
                checkUpEntity.setNote(resultSet.getString(5));
                checkUpEntity.setIdPerson(resultSet.getInt(6));
                String NSPPersone = resultSet.getString(7) + " "
                        + resultSet.getString(8) + " "
                        + resultSet.getString(9);
                checkUpEntity.setNSP(NSPPersone);
                checkUpEntity.setDoctorId(resultSet.getInt(10));
                String NSPDoctor = resultSet.getString(11) + " "
                        + resultSet.getString(12) + " "
                        + resultSet.getString(13);
                checkUpEntity.setDoctorNSP(NSPDoctor);
                checkUpEntityList.add(checkUpEntity);
            }

        } catch (SQLException e) {
            logger.error("SQLException in getCheckUp block:"+e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getCheckUp block(close preparedStatement):"+e);

            }
        }

        return checkUpEntityList;
    }

    public CheckUp_Entity getCheckUp(int id) {
        String sql = "SELECT C.ID,PRESSURE,PPM,WELLBEING,NOTE,IDPERSON ,S1.NAME,S1.SURNAME ,S1.PATRONYMIC, " +
                "DOCTORID,S2.NAME,S2.SURNAME ,S2.PATRONYMIC  FROM CHECKUP_" + formattedDate + " c " +
                "INNER JOIN STAFF s1 ON C.IDPERSON=s1.ID " +
                "INNER JOIN STAFF s2 ON C.DOCTORID =s2.ID";
        CheckUp_Entity checkUpEntity = new CheckUp_Entity();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                checkUpEntity.setId(resultSet.getInt(1));
                checkUpEntity.setPressure(resultSet.getString(2));
                checkUpEntity.setPpm(resultSet.getString(3));
                checkUpEntity.setWellBeing(resultSet.getString(4));
                checkUpEntity.setNote(resultSet.getString(5));
                checkUpEntity.setIdPerson(resultSet.getInt(6));
                String NSPPersone = resultSet.getString(7) + " "
                        + resultSet.getString(8) + " "
                        + resultSet.getString(9);
                checkUpEntity.setNSP(NSPPersone);
                checkUpEntity.setDoctorId(resultSet.getInt(10));
                String NSPDoctor = resultSet.getString(11) + " "
                        + resultSet.getString(12) + " "
                        + resultSet.getString(13);
                checkUpEntity.setDoctorNSP(NSPDoctor);

            }

        } catch (SQLException e) {
            logger.error("SQLException in getCheckUp block:"+e);

        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getCheckUp block(close statement):"+e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteBus block(close resultSet):"+e);

            }
        }
        return checkUpEntity;
    }

    public void createCheckUp(CheckUp_Entity checkUpEntity, List<Staff_Entity> person, List<Staff_Entity> doctor) {
        String sql = "INSERT INTO CHECKUP_" + formattedDate + " VALUES(?,?,?,?,?,?,?)";
        int personId = 0;
        int doctorId = 0;
        for (Staff_Entity staff : person) {
            String NSP = staff.getName() + " " + staff.getSurname() + " " + staff.getPatronymic();
            if (checkUpEntity.getNSP().equals(NSP)) {
                personId = staff.getId();
            }
        }

        for (Staff_Entity staff : doctor) {
            String NSP = staff.getName() + " " + staff.getSurname() + " " + staff.getPatronymic();
            if (checkUpEntity.getDoctorNSP().equals(NSP)) {
                doctorId = staff.getId();
            }
        }

        Statement statement = null;
        ResultSet resultSet = null;
        try {
             statement = connection.createStatement();
             resultSet = statement.executeQuery("SELECT MAX(id) FROM CHECKUP_" + formattedDate);
            int id = 0;
            if (resultSet.next()) {
                id = resultSet.getInt(1) + 1;
            }

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, checkUpEntity.getPressure());
            preparedStatement.setString(3, checkUpEntity.getPpm());
            preparedStatement.setString(4, checkUpEntity.getWellBeing());
            preparedStatement.setString(5, checkUpEntity.getNote());
            preparedStatement.setInt(6, doctorId);
            preparedStatement.setInt(7, personId);
            preparedStatement.execute();


        } catch (SQLException e) {
            logger.error("SQLException in createCheckUp block:"+e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createCheckUp block(close statement):"+e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createCheckUp block(close resultSet):"+e);

            }
        }
    }

    public void deleteCheckUp(int id) {

        String sql = "DELETE  CHECKUP_" + formattedDate + " WHERE ID=" + id;
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in deleteCheckUp block:"+e);

        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteCheckUp block(close preparedStatement):"+e);

            }
        }
    }

    public void updateCheckUp(CheckUp_Entity checkUpEntity, List<Staff_Entity> person, List<Staff_Entity> doctor) {
        String sql = "UPDATE CHECKUP_" + formattedDate +
                " SET PRESSURE=? , PPM=? , WELLBEING=? , NOTE=? ,IDPERSON=? , DOCTORID=? WHERE id=" + checkUpEntity.getId();
        int personId = 0;
        int doctorId = 0;
        for (Staff_Entity staff : person) {
            String NSP = staff.getName() + " " + staff.getSurname() + " " + staff.getPatronymic();
            if (checkUpEntity.getNSP().equals(NSP)) {
                personId = staff.getId();
            }
        }

        for (Staff_Entity staff : doctor) {
            String NSP = staff.getName() + " " + staff.getSurname() + " " + staff.getPatronymic();
            if (checkUpEntity.getDoctorNSP().equals(NSP)) {
                doctorId = staff.getId();
            }
        }
        PreparedStatement preparedStatement = null;
        try {

             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, checkUpEntity.getPressure());
            preparedStatement.setString(2, checkUpEntity.getPpm());
            preparedStatement.setString(3, checkUpEntity.getWellBeing());
            preparedStatement.setString(4, checkUpEntity.getNote());
            preparedStatement.setInt(5, personId);
            preparedStatement.setInt(6, doctorId);
            preparedStatement.execute();

        } catch (SQLException e) {
            logger.error("SQLException in updateCheckUp block:"+e);

        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateCheckUp block(close preparedStatement):"+e);

            }


        }

    }

    public void setLocalDate(LocalDate localDate) {
        formattedDate = localDate.format(DateTimeFormatter.ofPattern("dd_MM_yyyy"));
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
