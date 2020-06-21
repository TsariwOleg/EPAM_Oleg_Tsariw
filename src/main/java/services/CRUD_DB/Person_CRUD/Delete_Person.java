package services.CRUD_DB.Person_CRUD;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete_Person {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();


    public void deleteStaff(int id) {
        String sql = "update staff set  AGE=null , department_id=null where id=" + id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement("DELETE FROM BUS_DRIVERS WHERE ID=" + id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM ADMINISTRATION WHERE ID=" + id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in deleteStaff block:"+e);

        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteStaff block(close statement):"+e);

            }
        }

    }

    public void deletePassport(int id) {
        String sql = "update passport set DATE_OF_BIRTH=null , COUNTRY_OF_BIRTH=null , REGION_OF_BIRTH=null ," +
                "CITY_OF_BIRTH=null ,DOCUMENT_NO=null where id=" + id;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in deletePassport block:"+e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deletePassport block(close statement):"+e);

            }
        }

    }


    public void deleteTaxpayerCard(int id) {
        String sql = "update taxpayer_card set  TAXPAYER_NUMBER=null  where id=" + id;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in deleteTaxpayerCard block:"+e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteTaxpayerCard block(close statement):"+e);

            }
        }

    }

    public void deleteMedicalBook(int id) {
        String sql = "update Medical_book set  date_OF_MEDICAL_EXAMINATION=null , date_OF_NEXT_MEDICAL_EXAMINATION=null  where id=" + id;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            logger.error("SQLException in deleteMedicalBook block:"+e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteMedicalBook block(close statement):"+e);

            }
        }

    }

    public void deleteBusDriver(int id) {
        String sql = "UPDATE BUS_DRIVERS SET WORK_HOUR_ID=NULL , WORK_BUS_ID=NULL WHERE ID=" + id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException in deleteBusDriver block:"+e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteBusDriver block(close statement):"+e);

            }
        }
    }

}
