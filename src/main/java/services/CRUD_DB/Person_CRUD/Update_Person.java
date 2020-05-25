package services.CRUD_DB.Person_CRUD;

import services.ConnectionBD.ConnectionBD;
import services.Entity.*;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Update_Person extends ConnectionBD {
    Connection connection = getConnection();
//todo create one method for updating img
    public void updateStaffImg(int id, InputStream inputStream) {
        String sql = "update staff set photo= ? where id=" + id;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void updatePassportImg(int id, InputStream inputStream) {
        String sql = "update passport set Scanned_passport= ? where id=" + id;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void updateTaxpayerCardImg(int id, InputStream inputStream) {
        String sql = "update taxpayer_card set scanned_taxpayer_card= ? where id=" + id;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void updateBusDriverImg(int id, InputStream inputStream) {
        String sql = "update bus_drivers set driver_license= ? where id=" + id;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setBlob(1, inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }




    public void updateStaff(int id, Map newmap) {
        Staff_Entity staffEntity = (Staff_Entity) newmap.get("staff");

        int department_id = 0;
        switch (((Department_Entity) newmap.get("department")).getDepartment()) {
            case "Адміністрація":
                department_id = 1;
                break;
            case "Водії":
                department_id = 2;
                break;
            case "Автомеханіки":
                department_id = 3;
                break;

            case "Медперсонал":
                department_id = 4;
                break;
        }


        PreparedStatement preparedStatement;
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
                    newdepartm = "insert into ADMINISTRATION (id) values(" + id + ")";
                    break;
                case 2:
                    newdepartm = "insert into BUS_DRIVERS (id) values(" + id + ")";
                    break;

                case 3:
                    newdepartm = "insert into CAR_MECHANICS (id) values(" + id + ")";
                    break;

                case 4:
                    newdepartm = "insert into DOCTORS (id) values(" + id + ")";
                    break;
            }

            //todo
            preparedStatement = connection.prepareStatement("delete from administration where id=" + id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from  BUS_DRIVERS where id=" + id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from  CAR_MECHANICS where id=" + id);
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("delete from  DOCTORS where id=" + id);
            preparedStatement.executeUpdate();


            preparedStatement = connection.prepareStatement(newdepartm);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }


    }


    public void updatePosition(int id, Map newValues, Department_Entity oldValues) {
        PreparedStatement preparedStatement;
        String sql = "";
        int idPosition = 0;
        Department_Entity departmentEntity = ((Department_Entity) newValues.get("department"));

        switch (((Position_Entity) newValues.get("position")).getPosition()) {
            case "Директор компанії":
                idPosition = 1;
                break;
            case "Заступник директора":
                idPosition = 2;
                break;
        }




        if (departmentEntity.getDepartment().equals(oldValues.getDepartment())) {
            switch (departmentEntity.getDepartment()) {
                case "Адміністрація":
                    sql = "UPDATE ADMINISTRATION SET POSITION_ID= " + idPosition + "where id=" + id;
                    break;
                case "Водії":
                    sql = "UPDATE BUS_DRIVERS SET POSITION_ID= " + idPosition + "where id=" + id;
                    break;
                case "Автомеханіки":
                    sql = "UPDATE CAR_MECHANICS SET POSITION_ID= " + idPosition + "where id=" + id;
                    break;

                case "Медперсонал":
                    sql = "UPDATE DOCTORS SET POSITION_ID= " + idPosition + "where id=" + id;
                    break;
            }

            try {
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e);
            }

        } else {
            try {
                switch (departmentEntity.getDepartment()) {
                    case "Адміністрація":
                        sql = "insert  INTO ADMINISTRATION (ID,POSITION_ID) VALUES (?,?) ";
                        break;
                    case "Водії":
                        sql = "insert into  BUS_DRIVERS (ID,POSITION_ID) VALUES (?,?) ";
                        break;

                    case "Автомеханіки":
                        sql = "insert into  CAR_MECHANICS (ID,POSITION_ID) VALUES (?,?) ";
                        break;

                    case "Медаперсонал":
                        sql = "insert into  DOCTORS (ID,POSITION_ID) VALUES (?,?) ";
                        break;
                }


                preparedStatement = connection.prepareStatement("DELETE FROM BUS_DRIVERS WHERE ID=" + id);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("DELETE FROM ADMINISTRATION WHERE ID=" + id);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("DELETE FROM CAR_MECHANICS WHERE ID=" + id);
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("DELETE FROM DOCTORS WHERE ID=" + id);
                preparedStatement.executeUpdate();

                preparedStatement= connection.prepareStatement(sql);
                preparedStatement.setInt(1,id);
                preparedStatement.setInt(2,idPosition);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }


    public void updateBusDriver(int id , BusDrivers_Entity busDriversEntity,Map map){
        String sql = "UPDATE BUS_DRIVERS SET WORK_HOUR_ID =? , WORK_BUS_ID=? WHERE ID="+id;
        List<Buses_Entity> busDriversEntityList = (ArrayList)map.get("workBuses");
        List<WorkHours_Entity> workHoursEntityList = (ArrayList)map.get("workHours");
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

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,hoursId);
            preparedStatement.setInt(2,busId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }





    }




    BlobToString blobToString = new BlobToString();
    public void updatePassport (int id , Passport_Entity passportEntity){
        String sql = "UPDATE PASSPORT SET  DATE_OF_BIRTH=? , " +
        "COUNTRY_OF_BIRTH=? ,REGION_OF_BIRTH=? ,CITY_OF_BIRTH=? , DOCUMENT_NO=? WHERE ID="+id;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1,blobToString.getUTFString(passportEntity.getDateOfBirth()) );
            preparedStatement.setString(2,blobToString.getUTFString(passportEntity.getCountryOfBirth()));
            preparedStatement.setString(3,blobToString.getUTFString(passportEntity.getRegionOfBirth()));
            preparedStatement.setString(4,blobToString.getUTFString(passportEntity.getCityOfBirth()));
            preparedStatement.setInt(5,passportEntity.getDocumentNo());
            preparedStatement.executeUpdate();
        }catch (SQLException  e){
            System.out.println(e);
        }

    }


    public void updateTaxpayerCard(int id , TaxpayerCard_Entity taxpayerCardEntity){
        String sql = "UPDATE taxpayer_card SET  TAXPAYER_NUMBER=? where id="+id;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,taxpayerCardEntity.getTaxpayerNumber());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }
    }


    public void updateMedicalBook(int id , MedicalBook_Entity medicalBookEntity) {
        String sql="UPDATE Medical_book SET date_OF_MEDICAL_EXAMINATION=?, date_OF_NEXT_MEDICAL_EXAMINATION=? WHERE ID="+id;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,blobToString.getUTFString(medicalBookEntity.getDateOfMedicalExam()));
            preparedStatement.setString(2,blobToString.getUTFString(medicalBookEntity.getDateOfNextMedicalExam()));
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println(e);
        }

    }





}
