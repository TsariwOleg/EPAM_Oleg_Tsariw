package services.servlets;
import services.CRUD_DB.ConstantTablesCRUD;
import services.CRUD_DB.Staff_CRUD;
import services.Entity.BlobToString;
import services.Entity.Staff_Entity;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@WebServlet("/staff")
public class Staff extends HttpServlet {
    Staff_CRUD staff_crud = new Staff_CRUD();
    List<Staff_Entity> staffEntityList = new ArrayList<>();
    private Map mapConstantTable;
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)  {
        staffEntityList=staff_crud.getStaff();

//        System.out.println(req.getParameter("regime"));


        req.setAttribute("staff",staffEntityList);
        req.setAttribute("q","stntityList");

        if (req.getParameter("regime")!=null){
            mapConstantTable = constantTablesCRUD.readConstantTable("");
            if (req.getParameter("regime").equals("AddPerson")){
                req.setAttribute("regime",req.getParameter("regime"));
                req.setAttribute("departments", mapConstantTable.get("departments"));
            }
            if (req.getParameter("regime").equals("DeletePerson")){
                req.setAttribute("regime",req.getParameter("regime"));
                    if (req.getParameter("id")!=null){
                        req.setAttribute("deletePersonId",req.getParameter("id"));
                    }
            }
        }



        try {
            req.getRequestDispatcher("/Staff.jsp").forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("deletePerson")!=null){
            staff_crud.deletePerson(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/staff");
            return;
        }
        if(req.getParameter("cancelDeletingPerson")!=null){
            resp.sendRedirect("/staff");
            return;
        }





        if (req.getParameter("confirmPerson")!=null){
            BlobToString blobToString = new BlobToString();
            Staff_Entity staffEntity = new Staff_Entity(blobToString.getUTFString(req.getParameter("newName")),
                    blobToString.getUTFString(req.getParameter("newSurname")),
                    blobToString.getUTFString(req.getParameter("newPatronymic")),
                    Integer.parseInt(req.getParameter("newAge"))
            );
            staffEntity.setDepartment(blobToString.getUTFString(req.getParameter("newDepartment")));
            staff_crud.createPerson(staffEntity,mapConstantTable);
        }

        doGet(req,resp);
    }


}
