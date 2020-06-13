package services.CRUD_DB.Person_CRUD;

import services.ConnectionBD.ConnectionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete_Person /*extends ConnectionBD*/ {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();


//    Connection connection = getConnection();


    public void deleteStaff(int id ){
        String sql = "update staff set  AGE=null , department_id=null where id="+id;
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

            //todo
            preparedStatement = connection.prepareStatement("DELETE FROM BUS_DRIVERS WHERE ID="+id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("DELETE FROM ADMINISTRATION WHERE ID="+id);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

    public void deletePassport(int id ){
        String sql = "update passport set DATE_OF_BIRTH=null , COUNTRY_OF_BIRTH=null , REGION_OF_BIRTH=null ," +
                "CITY_OF_BIRTH=null ,DOCUMENT_NO=null where id="+id;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }


    public void deleteTaxpayerCard(int id ) {
        String sql = "update taxpayer_card set  TAXPAYER_NUMBER=null  where id="+id;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

    public void deleteMedicalBook(int id  ) {
        String sql = "update Medical_book set  date_OF_MEDICAL_EXAMINATION=null , date_OF_NEXT_MEDICAL_EXAMINATION=null  where id="+id;
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }

    public void deleteBusDriver(int id  ){
        String sql ="UPDATE BUS_DRIVERS SET WORK_HOUR_ID=NULL , WORK_BUS_ID=NULL WHERE ID="+id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

    }




    }
