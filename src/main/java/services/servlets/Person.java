package services.servlets;

import services.CRUD_DB.PersonCRUD;
import services.ConnectionBD.ConnectionBD;
import services.Entity.Passport_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

//todo close connection

@WebServlet("/person")
@MultipartConfig(maxFileSize = 16177215)
// indicates this servlet will handle multipart request. We restrict maximum size of the upload file up to 16 MB.
public class Person extends HttpServlet {
    PersonCRUD person_crud = new PersonCRUD();

    private Map map;



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        map = person_crud.readPerson(Integer.parseInt(req.getParameter("name")));

        req.setAttribute("staff", map.get("staff"));

        req.setAttribute("passport", map.get("passport"));
        req.setAttribute("department", map.get("department"));
        req.setAttribute("taxpayerCard", map.get("taxpayerCard"));
        req.setAttribute("medicalBook", map.get("medicalBook"));


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
        int id = Integer.parseInt(req.getParameter("name"));
        InputStream inputStream = null;
        Part part;

       try {
            if (req.getParameter("staff-button") != null) {
                 part=req.getPart("staff-img");

                System.out.println(part);
                if (part.getSize() != 0) {
                    person_crud.updatePersonImg(id, part, "staff");
                    inputStream=part.getInputStream();
                }
            }

            if (req.getParameter("passport-button") != null) {

                part = req.getPart("passport-img");
                if (part.getSize() != 0) {
                    person_crud.updatePersonImg(id, part, "passport");
                }
            }

            if (req.getParameter("taxpayerCard-button") != null) {

                part = req.getPart("taxpayerCard-img");
                if (part.getSize() != 0) {
                    person_crud.updatePersonImg(id, part, "taxpayerCard");
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        } catch (ServletException e) {
            System.out.println(e);
        } catch (IllegalStateException e) {
            System.out.println("Задовгий" + e);
        }


        req.setAttribute("image",inputStream);

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
