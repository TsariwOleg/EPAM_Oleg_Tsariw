package services.servlets;

import services.CRUD_DB.CheckUpCRUD;
import services.CRUD_DB.ConstantTablesCRUD;
import services.BlobToString;
import services.Entity.CheckUp_Entity;
import services.Entity.Staff_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/checkUp")
public class CheckUp extends HttpServlet {
    CheckUpCRUD checkUpCRUD = new CheckUpCRUD();
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();
    int selectDateTriger=0;
    String date="";



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId")!=null){
            System.out.println(req.getSession().getAttribute("departmentId"));
            req.setAttribute("access",req.getSession().getAttribute("departmentId"));
        }


        if (selectDateTriger==0){
            req.setAttribute("inputDate","inputDate");

        }else {
            if (("ChangeDate").equals(req.getParameter("regime"))){
                selectDateTriger=0;
                resp.sendRedirect("/checkUp");
                return;
            }

        List<Staff_Entity> staff = constantTablesCRUD.readStaff();
        List<Staff_Entity> doctors = constantTablesCRUD.readDoctor();
        if (req.getParameter("regime") != null) {
            if (req.getParameter("regime").equals("AddCheckUp")) {
                req.setAttribute("staff", staff);
                req.setAttribute("doctor", doctors);
            }

            if (req.getParameter("regime").equals("DeleteCheckUp")) {
                req.setAttribute("changeCheckUpId", req.getParameter("id"));
            }

            if (req.getParameter("regime").equals("EditCheckUp")) {
                req.setAttribute("changeCheckUpId", req.getParameter("id"));
                if (req.getParameter("id")!=null){
                req.setAttribute("changeCheckUpId",req.getParameter("id"));
                req.setAttribute("oneCheckUp",
                        checkUpCRUD.getCheckUp(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("staff", staff);
                    req.setAttribute("doctor", doctors);
            }}


            req.setAttribute("regime", req.getParameter("regime"));


        }

        req.setAttribute("checkUp", checkUpCRUD.getCheckUp());
        req.setAttribute("date",date);
        }
        req.getRequestDispatcher("/CheckUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Staff_Entity> staff = constantTablesCRUD.readStaff();
        List<Staff_Entity> doctors = constantTablesCRUD.readDoctor();
        BlobToString blobToString = new BlobToString();
        if (req.getParameter("confirmCheckUp") != null) {
            CheckUp_Entity checkUpEntity = new CheckUp_Entity();
            checkUpEntity.setNSP(blobToString.getUTFString(req.getParameter("newPerson")));
            checkUpEntity.setPressure(blobToString.getUTFString(req.getParameter("newPressure")));
            checkUpEntity.setPpm(blobToString.getUTFString(req.getParameter("newPpm")));
            checkUpEntity.setWellBeing(blobToString.getUTFString(req.getParameter("newWellBeing")));
            checkUpEntity.setNote(blobToString.getUTFString(req.getParameter("newNote")));
            checkUpEntity.setDoctorNSP(blobToString.getUTFString(req.getParameter("newDoctor")));
            checkUpCRUD.createCheckUp(checkUpEntity, staff, doctors);
        }

        if (req.getParameter("deleteCheckUp") != null) {
            checkUpCRUD.deleteCheckUp(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/checkUp");
            return;
        }

        if (req.getParameter("confirmEdittingCheckUp") != null) {
            CheckUp_Entity checkUpEntity = new CheckUp_Entity();
            checkUpEntity.setId(Integer.parseInt(req.getParameter("id")));
            checkUpEntity.setNSP(blobToString.getUTFString(req.getParameter("newPerson")));
            checkUpEntity.setPressure(blobToString.getUTFString(req.getParameter("newPressure")));
            checkUpEntity.setPpm(blobToString.getUTFString(req.getParameter("newPpm")));
            checkUpEntity.setWellBeing(blobToString.getUTFString(req.getParameter("newWellBeing")));
            checkUpEntity.setNote(blobToString.getUTFString(req.getParameter("newNote")));
            checkUpEntity.setDoctorNSP(blobToString.getUTFString(req.getParameter("newDoctor")));


            checkUpCRUD.updateCheckUp(checkUpEntity, staff, doctors);
            resp.sendRedirect("/checkUp");
            return;
        }

        if (req.getParameter("cancelDeletingCheckUp") != null) {
            resp.sendRedirect("/checkUp");
            return;
        }

        if (req.getParameter("inputDateOfCheckUp")!=null) {
            date = req.getParameter("DateOfCheckUp");
            checkUpCRUD.setLocalDate(LocalDate.parse(date));
            selectDateTriger = 1;
        }




        doGet(req, resp);
    }
}
