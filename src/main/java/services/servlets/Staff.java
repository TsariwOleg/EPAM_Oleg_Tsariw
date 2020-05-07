package services.servlets;
import services.CRUD_DB.Staff_CRUD;
import  services.ConnectionBD.*;
import services.Entity.StaffEntity;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarOutputStream;


@WebServlet("/staff")
public class Staff extends HttpServlet {
    Staff_CRUD staff_crud = new Staff_CRUD();
    List<StaffEntity> staffEntityList = new ArrayList<>();

//            staff_crud.getStaff();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        staffEntityList=staff_crud.getStaff();



        req.setAttribute("staff",staffEntityList);
        req.setAttribute("q","stntityList");

        req.setAttribute("s","asd");

        req.getRequestDispatcher("/Staff.jsp").forward(req,resp);

        ArrayList ar = (ArrayList)req.getAttribute("staff");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

}
