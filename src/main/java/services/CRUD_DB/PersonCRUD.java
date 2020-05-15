package services.CRUD_DB;


import services.CRUD_DB.Person_CRUD.Read_Person;
import services.CRUD_DB.Person_CRUD.Update_Person;
import services.ConnectionBD.ConnectionBD;


import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class PersonCRUD extends ConnectionBD {
    Connection connection = getConnection();
    private String className = this.getClass().getSimpleName();

//todo create dao  close connection


    public Map readPerson(int id) {
        Map<String, Object> map = new HashMap<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            Read_Person read_person = new Read_Person();

            map.put("department", read_person.getDepartment(statement, id));
            map.put("staff", read_person.getStaff(statement, id));
            map.put("passport", read_person.getPassport(statement, id));
            map.put("taxpayerCard", read_person.getTaxpayerCard(statement, id));
            map.put("medicalBook", read_person.getMedicalBook(statement, id));
            map.put("getParametersOfDrivers", read_person.getParametersOfDrivers(statement, id));


        } catch (SQLException e) {

            System.out.println(e);
        } finally {

            if (statement != null) {
                closeStatement(statement, className);
            }

        }

        return map;
    }


    public void updatePersonImg(int id, Part part,String table) throws IOException {
        InputStream inputStream = part.getInputStream();
        Update_Person update_person = new Update_Person();


        if (table.equals("staff")){
            update_person.updateStaffImg(id,inputStream);
        }

        if (table.equals("passport")){
            update_person.updatePassportImg(id,inputStream);
        }

        if (table.equals("taxpayerCard")){
            update_person.updateTaxPayerCardImg(id,inputStream);
        }



    }


}
