package services.CRUD_DB.Person_CRUD;

import org.apache.log4j.Logger;
import org.h2.jdbc.JdbcSQLException;
import services.BlobToString;
import services.ConnectionBD.ConnectionBD;
import services.Entity.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Update_Person   {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();


    public void updateStaffImg(int id, InputStream inputStream) {
        String sql = "update staff set photo= ? where id=" + id;
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in updateStaffImg block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateStaffImg block(close statement):"+e);
            }
        }
    }


    public void updatePassportImg(int id, InputStream inputStream) {
        String sql = "update passport set Scanned_passport= ? where id=" + id;
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in updatePassportImg block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updatePassportImg block(close statement):"+e);
            }
        }
    }


    public void updateTaxpayerCardImg(int id, InputStream inputStream) {
        String sql = "update taxpayer_card set scanned_taxpayer_card= ? where id=" + id;
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in updateTaxpayerCardImg block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateTaxpayerCardImg block(close statement):"+e);
            }
        }
    }


    public void updateBusDriverImg(int id, InputStream inputStream) {
        String sql = "update bus_drivers set driver_license= ? where id=" + id;
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in updateBusDriverImg block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateBusDriverImg block(close statement):"+e);
            }
        }
    }




    public void updateStaff(int id,Map newValues , Map constantTables) {
       Staff_Entity staffEntity =(Staff_Entity)newValues.get("staff");
        List<Department_Entity> department_entityList = (ArrayList) constantTables.get("departments");
        List<Position_Entity> positionEntityList = (ArrayList)constantTables.get("positions");

        int department_id = 0;
        int idPosition=0;


        for (Department_Entity departmentEntity : department_entityList) {
            if (departmentEntity.getDepartment().equals(staffEntity.getDepartment())) {
                department_id = departmentEntity.getId();
            }
        }

        for (Position_Entity positionEntity:positionEntityList) {
            if (positionEntity.getPosition().equals(((Position_Entity) newValues.get("position")).getPosition())){
                idPosition=positionEntity.getId();
            }
        }


        PreparedStatement preparedStatement=null;
        String sql = "UPDATE STAFF SET NAME=? ,SURNAME=? , Patronymic=? , AGE=? , department_id=?  WHERE ID=" + id;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, staffEntity.getName());
            preparedStatement.setString(2, staffEntity.getSurname());
            preparedStatement.setString(3, staffEntity.getPatronymic());
            preparedStatement.setInt(4, staffEntity.getAge());
            preparedStatement.setInt(5, department_id);
            preparedStatement.executeUpdate();


            String newdepartm = "";
            switch (department_id) {
                case 1:
                    newdepartm = "set mode MySQL;INSERT INTO ADMINISTRATION(id,POSITION_ID ) " +
                            "VALUES("+id+", "+ idPosition+") ON DUPLICATE KEY UPDATE position_id="+idPosition;

                    break;
                case 2:
                    newdepartm = "set mode MySQL;INSERT INTO BUS_DRIVERS(id,POSITION_ID ) " +
                            "VALUES("+id+", "+ idPosition+") ON DUPLICATE KEY UPDATE position_id="+idPosition;
                    break;

                case 3:
                    newdepartm = "set mode MySQL;INSERT INTO CAR_MECHANICS(id,POSITION_ID ) " +
                            "VALUES("+id+", "+ idPosition+") ON DUPLICATE KEY UPDATE position_id="+idPosition;

                    break;

                case 4:
                    newdepartm = "set mode MySQL;INSERT INTO DOCTORS(id,POSITION_ID ) " +
                            "VALUES("+id+", "+ idPosition+") ON DUPLICATE KEY UPDATE position_id="+idPosition;
                    break;
            }

            preparedStatement = connection.prepareStatement("delete from administration where id=" + id);
            preparedStatement.executeUpdate();

            if (!staffEntity.getDepartment().equals("Водії")){
            preparedStatement = connection.prepareStatement("delete from  BUS_DRIVERS where id=" + id);
            preparedStatement.executeUpdate();
            }

            if (!staffEntity.getDepartment().equals("Автомеханіки")){
                try {
                    preparedStatement = connection.prepareStatement("delete from  CAR_MECHANICS where id=" + id);
                    preparedStatement.executeUpdate();
                }catch (JdbcSQLException e){
                    logger.error("JdbcSQLException referential integrity violation in updateStaff block:"+e);
                }

            }

            if (!staffEntity.getDepartment().equals("Медики")){
            preparedStatement = connection.prepareStatement("delete from  DOCTORS where id=" + id);
            preparedStatement.executeUpdate();
            }


            preparedStatement = connection.prepareStatement(newdepartm);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in updateStaff block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateStaff block(close statement):"+e);
            }
        }

    }


    public void updateBusDriver(int id , BusDrivers_Entity busDriversEntity,Map constantTables){
        String sql = "UPDATE BUS_DRIVERS SET WORK_HOUR_ID =? , WORK_BUS_ID=? WHERE ID="+id;
        List<Buses_Entity> busDriversEntityList = (ArrayList)constantTables.get("workBuses");
        List<WorkHours_Entity> workHoursEntityList = (ArrayList)constantTables.get("workHours");
        int busId=0;
        int hoursId=0;
        String workHours = busDriversEntity.getStartWorkHours()+"-"+busDriversEntity.getEndWorkHours();
        for (Buses_Entity workBus:busDriversEntityList) {
            if (workBus.getBusNo().equals(busDriversEntity.getWorkBus())){
                busId=workBus.getId();
            }
        }


        for (WorkHours_Entity workHoursEntity:workHoursEntityList) {
            if ((workHoursEntity.getStartWorkHour()+"-"+workHoursEntity.getEndWorkHour()).equals(workHours)){
                hoursId=workHoursEntity.getId();
            }
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,hoursId);
            preparedStatement.setInt(2,busId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("SQLException in updateBusDriver block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateBusDriver block(close statement):"+e);
            }
        }


    }




    public void updatePassport (int id , Passport_Entity passportEntity){
        BlobToString blobToString = new BlobToString();

        String sql = "UPDATE PASSPORT SET  DATE_OF_BIRTH=? , " +
        "COUNTRY_OF_BIRTH=? ,REGION_OF_BIRTH=? ,CITY_OF_BIRTH=? , DOCUMENT_NO=? WHERE ID="+id;
        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,blobToString.getUTFString(passportEntity.getDateOfBirth()) );
            preparedStatement.setString(2,blobToString.getUTFString(passportEntity.getCountryOfBirth()));
            preparedStatement.setString(3,blobToString.getUTFString(passportEntity.getRegionOfBirth()));
            preparedStatement.setString(4,blobToString.getUTFString(passportEntity.getCityOfBirth()));
            preparedStatement.setInt(5,passportEntity.getDocumentNo());
            preparedStatement.executeUpdate();
        }catch (SQLException  e){
            logger.error("SQLException in updatePassport block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updatePassport block(close statement):"+e);
            }
        }

    }


    public void updateTaxpayerCard(int id , TaxpayerCard_Entity taxpayerCardEntity){
        String sql = "UPDATE taxpayer_card SET  TAXPAYER_NUMBER=? where id="+id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,taxpayerCardEntity.getTaxpayerNumber());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("SQLException in updateTaxpayerCard block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateTaxpayerCard block(close statement):"+e);
            }
        }
    }


    public void updateMedicalBook(int id , MedicalBook_Entity medicalBookEntity) {
        BlobToString blobToString = new BlobToString();
        String sql="UPDATE Medical_book SET date_OF_MEDICAL_EXAMINATION=?, date_OF_NEXT_MEDICAL_EXAMINATION=? WHERE ID="+id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,blobToString.getUTFString(medicalBookEntity.getDateOfMedicalExam()));
            preparedStatement.setString(2,blobToString.getUTFString(medicalBookEntity.getDateOfNextMedicalExam()));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            logger.error("SQLException in updateMedicalBook block:"+e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateMedicalBook block(close statement):"+e);
            }
        }
    }

}
