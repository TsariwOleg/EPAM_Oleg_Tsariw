package services.servlets;

import services.CRUD_DB.ConstantTablesCRUD;
import services.CRUD_DB.PersonCRUD;
import services.ConnectionBD.ConnectionBD;
import services.Entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//todo close connection

@WebServlet("/person")
@MultipartConfig(maxFileSize = 16177215)
// indicates this servlet will handle multipart request. We restrict maximum size of the upload file up to 16 MB.
public class Person extends HttpServlet {
    PersonCRUD personCRUD = new PersonCRUD();
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();
    private Map mapPerson;
    private Map mapConstantTable;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        mapPerson = personCRUD.readPerson(Integer.parseInt(req.getParameter("id")));


        req.setAttribute("staff", mapPerson.get("staff"));
        req.setAttribute("department", mapPerson.get("department"));
        req.setAttribute("passport", mapPerson.get("passport"));
        req.setAttribute("positionOfPerson", mapPerson.get("position"));
        req.setAttribute("taxpayerCard", mapPerson.get("taxpayerCard"));
        req.setAttribute("medicalBook", mapPerson.get("medicalBook"));
        req.setAttribute("busDriver", mapPerson.get("busDriver"));


        if (req.getParameter("regime") != null) {
            mapConstantTable = constantTablesCRUD.readConstantTable(req.getParameter("regime"));
            if (req.getParameter("regime").equals("UpdateInfoAboutPers")) {
                req.setAttribute("departments", mapConstantTable.get("departments"));
                req.setAttribute("positions", mapConstantTable.get("positions"));
            }

            if (req.getParameter("regime").equals("UpdateInfoOther")){
                req.setAttribute("workHours",mapConstantTable.get("workHours"));
                req.setAttribute("workBuses",mapConstantTable.get("workBuses"));
            }




            req.setAttribute("regime", req.getParameter("regime"));
        }


        try {
            req.getRequestDispatcher("/Person.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        mapPerson = personCRUD.readPerson(Integer.parseInt(req.getParameter("id")));
        int id = Integer.parseInt(req.getParameter("id"));
        Part part;
        BlobToString blobToString = new BlobToString();



        try {

            if (req.getParameter("confirmPerson") != null) {
                Map newmap = new HashMap();
                Staff_Entity staffEntity = new Staff_Entity(blobToString.getUTFString(req.getParameter("newName")),
                        blobToString.getUTFString(req.getParameter("newSurname")),
                        blobToString.getUTFString(req.getParameter("newPatronymic")),
                        Integer.parseInt(req.getParameter("newAge"))
                        );
                Position_Entity positionEntity = new Position_Entity();
                positionEntity.setPosition(blobToString.getUTFString(req.getParameter("newPosition")));
                Department_Entity departmentEntity = new Department_Entity();
                departmentEntity.setDepartment(blobToString.getUTFString(req.getParameter("newDepartment")));

                newmap.put("staff",staffEntity);
                newmap.put("department",departmentEntity);
                newmap.put("position",positionEntity);

                PersonCRUD personCRUD = new PersonCRUD();
                personCRUD.updatePerson(id,newmap,"staff",mapPerson);
            }

            if(req.getParameter("confirmPassport")!=null){
                Passport_Entity passportEntity = new Passport_Entity(req.getParameter("newDateOfBirth"),
                        req.getParameter("newCountryOfBirth"),
                        req.getParameter("newRegionOfBirth"),
                        req.getParameter("newCityOfBirth"),
                        Integer.parseInt(req.getParameter("newDocumentNo"))
                        );

                PersonCRUD personCRUD = new PersonCRUD();
                personCRUD.updatePerson(id,passportEntity,"passport");

            }


            if(req.getParameter("confirmTaxpayerCard")!=null){
                TaxpayerCard_Entity taxpayerCardEntity = new TaxpayerCard_Entity(Integer.parseInt(req.getParameter("newTaxpayerNumber")));
                PersonCRUD personCRUD = new PersonCRUD();
                personCRUD.updatePerson(id,taxpayerCardEntity,"taxpayerCard");
            }


            if (req.getParameter("confirmMedicalBook")!=null){
                MedicalBook_Entity medicalBookEntity = new MedicalBook_Entity(
                        req.getParameter("newDateOfMedicalExam"),
                        req.getParameter("newDateOfNextMedicalExam")
                );
                PersonCRUD personCRUD = new PersonCRUD();
                personCRUD.updatePerson(id,medicalBookEntity,"medicalBook");
            }

            if(req.getParameter("confirmBusDriver")!=null){
                String [] hours =req.getParameter("newStartWorkHour").split("-");
                  BusDrivers_Entity busDriversEntity = new BusDrivers_Entity(
                    hours[0],hours[1],
                    req.getParameter("newBus"));

                PersonCRUD personCRUD = new PersonCRUD();
                personCRUD.updatePerson(id,busDriversEntity,"busDriver",mapConstantTable);

            }


            if (req.getParameter("staff-button") != null) {
                part = req.getPart("staff-img");
                if (part.getSize() != 0) {
                    personCRUD.updatePersonImg(id, part, "staff");
                }
            }

            if (req.getParameter("passport-button") != null) {
                part = req.getPart("passport-img");
                if (part.getSize() != 0) {
                    personCRUD.updatePersonImg(id, part, "passport");
                }
            }


            if (req.getParameter("taxpayerCard-button") != null) {
                part = req.getPart("taxpayerCard-img");
                if (part.getSize() != 0) {
                    personCRUD.updatePersonImg(id, part, "taxpayerCard");
                }
            }


            if (req.getParameter("driverLicense-button") != null) {
                part = req.getPart("driverLicense-img");
                if (part.getSize() != 0) {
                    personCRUD.updatePersonImg(id, part, "driverLicense");
                }
            }


            if (req.getParameter("deletePerson")!=null){
                PersonCRUD personCRUD =new PersonCRUD();
                personCRUD.deletePerson(id,"staff");
            }

            if (req.getParameter("deletePassport")!=null){
                PersonCRUD personCRUD =new PersonCRUD();
                personCRUD.deletePerson(id,"passport");
            }

            if (req.getParameter("deleteTaxpayerCard")!=null){
                PersonCRUD personCRUD =new PersonCRUD();
                personCRUD.deletePerson(id,"taxpayerCard");
            }

            if (req.getParameter("deleteMedicalBook")!=null){
                PersonCRUD personCRUD = new PersonCRUD();
                personCRUD.deletePerson(id,"medicalBook");
            }

            if (req.getParameter("deleteBusDriver")!=null){
                PersonCRUD personCRUD = new PersonCRUD();
                personCRUD.deletePerson(id,"busDriver");
            }





        } catch (IOException e) {
            System.out.println(e);
        } catch (ServletException e) {
            System.out.println(e);
        } catch (IllegalStateException e) {
            System.out.println("Задовгий" + e);
        }




/*
        InputStream inputStream = null;
        Part part = null;
        try {
            part = req.getPart("photo");//Obtaining the part of upload file in the request:
        } catch (IllegalStateException e) {
            System.out.println("Заддовгий");
        }

        //OK
        if (part != null) {
            System.out.println("part not null");
            inputStream = part.getInputStream();//Obtaining input stream of the upload file
        }


        try {
            String sql = "INSERT INTO image  values (3, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            if (inputStream != null) {
                System.out.println("input stream null");
                preparedStatement.setBlob(1, inputStream);
                preparedStatement.setBlob(2, inputStream);
                preparedStatement.executeUpdate();

            }


        } catch (SQLException e) {
            System.out.println("image");
            System.out.println(e);
        }*/

        doGet(req, resp);
    }
}
