package services.servlets;

import services.CRUD_DB.DepartmentCRUD;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/department")
public class Department extends HttpServlet {
DepartmentCRUD departmentCRUD = new DepartmentCRUD();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId")!=null){
            req.setAttribute("access",req.getSession().getAttribute("departmentId"));
        }

        req.setAttribute("department",departmentCRUD.getForDepartmentPage());
        req.getRequestDispatcher("/Department.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        doGet(req,resp);
    }
}
