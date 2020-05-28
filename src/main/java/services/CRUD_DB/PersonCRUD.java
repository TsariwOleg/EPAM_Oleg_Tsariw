package services.CRUD_DB;


import services.CRUD_DB.Person_CRUD.Delete_Person;
import services.CRUD_DB.Person_CRUD.Read_Person;
import services.CRUD_DB.Person_CRUD.Update_Person;
import services.ConnectionBD.ConnectionPool;
import services.Entity.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class PersonCRUD {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();


//todo create dao  close connection


    public Map readPerson(int id) {
        Map<String, Object> map = new HashMap<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            Read_Person read_person = new Read_Person();


            map.put("staff", read_person.getStaff(statement, id));
            map.put("department", read_person.getDepartment(statement, id));
            map.put("position", read_person.getPosition(statement, ((Department_Entity) map.get("department")).getDepartment(), id));
            map.put("passport", read_person.getPassport(statement, id));
            map.put("taxpayerCard", read_person.getTaxpayerCard(statement, id));
            map.put("medicalBook", read_person.getMedicalBook(statement, id));
            map.put("getParametersOfDrivers", read_person.getParametersOfDrivers(statement, id));

            if (("Водії").equals(((Department_Entity) map.get("department")).getDepartment())){
                map.put("busDriver",read_person.getBusDriver(statement,id));
            }


        } catch (SQLException e) {

            System.out.println(e);
        }

        return map;
    }


    public void updatePersonImg(int id, Part part, String table) throws IOException {
        InputStream inputStream = part.getInputStream();
        Update_Person update_person = new Update_Person();


        if (table.equals("staff")) {
            update_person.updateStaffImg(id, inputStream);
        }

        if (table.equals("passport")) {
            update_person.updatePassportImg(id, inputStream);
        }

        if (table.equals("taxpayerCard")) {
            update_person.updateTaxpayerCardImg(id, inputStream);
        }

        //todo
        if (table.equals("driverLicense")) {
            update_person.updateBusDriverImg(id, inputStream);
        }

    }


    public void updatePerson(int id, Map newmap, String table , Map constantTables) {
        Update_Person updatePerson = new Update_Person();
        if (table.equals("staff")) {
            updatePerson.updateStaff(id,newmap,constantTables);
        }

    }


    public void updatePerson(int id, Object newValue, String table, Map constantTables) {
        Update_Person updatePerson = new Update_Person();
        if (table.equals("busDriver")){
            updatePerson.updateBusDriver(id,(BusDrivers_Entity)newValue,constantTables);
        }


    }

    public void updatePerson(int id, Object newValue, String table) {
        Update_Person updatePerson = new Update_Person();
        if (table.equals("passport")) {
            updatePerson.updatePassport(id,(Passport_Entity)newValue );
        }

        if (table.equals("taxpayerCard")) {
            updatePerson.updateTaxpayerCard(id,(TaxpayerCard_Entity)newValue );
        }

        if (table.equals("medicalBook")) {
            updatePerson.updateMedicalBook(id,(MedicalBook_Entity)newValue );
        }

    }









    public void deletePerson(int id , String table){
        Delete_Person deletePerson = new Delete_Person();
        PreparedStatement preparedStatement=null;
        if (table.equals("staff")){
            deletePerson.deleteStaff(id,preparedStatement);
        }

        if (table.equals("passport")){
            deletePerson.deletePassport(id,preparedStatement);
        }

        if (table.equals("taxpayerCard")){
            deletePerson.deleteTaxpayerCard(id,preparedStatement);
        }

        if (table.equals("medicalBook")){
            deletePerson.deleteMedicalBook(id,preparedStatement);
        }

        if (table.equals("busDriver")){
            deletePerson.deleteBusDriver(id,preparedStatement);
        }

    }


}
