package services.CRUD_DB;


import services.CRUD_DB.Person_CRUD.Delete_Person;
import services.CRUD_DB.Person_CRUD.Read_Person;
import services.CRUD_DB.Person_CRUD.Update_Person;
import services.Entity.*;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class PersonCRUD {


//todo create dao  close connection


    public Map readPerson(int id) {
        Map<String, Object> map = new HashMap<>();
        Read_Person read_person = new Read_Person();

        map.put("staff", read_person.getStaff(id));
        map.put("department", read_person.getDepartment(id));
        map.put("position", read_person.getPosition(((Department_Entity) map.get("department")).getDepartment(), id));
        map.put("passport", read_person.getPassport(id));
        map.put("taxpayerCard", read_person.getTaxpayerCard(id));
        map.put("medicalBook", read_person.getMedicalBook(id));
        map.put("getParametersOfDrivers", read_person.getParametersOfDrivers(id));

        if (("Водії").equals(((Department_Entity) map.get("department")).getDepartment())) {
            map.put("busDriver", read_person.getBusDriver(id));
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


    public void updatePerson(int id, Map newmap, String table, Map constantTables) {
        Update_Person updatePerson = new Update_Person();
        if (table.equals("staff")) {
            updatePerson.updateStaff(id, newmap, constantTables);
        }

    }


    public void updatePerson(int id, Object newValue, String table, Map constantTables) {
        Update_Person updatePerson = new Update_Person();
        if (table.equals("busDriver")) {
            updatePerson.updateBusDriver(id, (BusDrivers_Entity) newValue, constantTables);
        }


    }

    public void updatePerson(int id, Object newValue, String table) {
        Update_Person updatePerson = new Update_Person();
        if (table.equals("passport")) {
            updatePerson.updatePassport(id, (Passport_Entity) newValue);
        }

        if (table.equals("taxpayerCard")) {
            updatePerson.updateTaxpayerCard(id, (TaxpayerCard_Entity) newValue);
        }

        if (table.equals("medicalBook")) {
            updatePerson.updateMedicalBook(id, (MedicalBook_Entity) newValue);
        }

    }


    public void deletePerson(int id, String table) {
        Delete_Person deletePerson = new Delete_Person();

        if (table.equals("staff")) {
            deletePerson.deleteStaff(id);
        }

        if (table.equals("passport")) {
            deletePerson.deletePassport(id);
        }

        if (table.equals("taxpayerCard")) {
            deletePerson.deleteTaxpayerCard(id);
        }

        if (table.equals("medicalBook")) {
            deletePerson.deleteMedicalBook(id);
        }

        if (table.equals("busDriver")) {
            deletePerson.deleteBusDriver(id);
        }

    }


}
