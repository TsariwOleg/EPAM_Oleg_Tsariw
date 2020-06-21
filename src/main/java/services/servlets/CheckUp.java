package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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

    int idSession = -1;
    private final Logger logger = Logger.getRootLogger();

    @Override
    public void init() {
        BasicConfigurator.configure();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId")!=null){
            req.setAttribute("access",req.getSession().getAttribute("departmentId"));
            idSession = (Integer) req.getSession().getAttribute("departmentId");

        }else {
            idSession=-1;
        }


        if (selectDateTriger==0){
            req.setAttribute("inputDate","inputDate");

        }else {
            if (("ChangeDate").equals(req.getParameter("regime"))){
                selectDateTriger=0;
                resp.sendRedirect("/checkUp");
                return;
            }



        if (req.getParameter("regime") != null) {
            List<Staff_Entity> staff = constantTablesCRUD.readStaff("");
            List<Staff_Entity> doctors = constantTablesCRUD.readStaff("WHERE DEPARTMENT_ID=4");

            if (req.getParameter("regime").equals("AddCheckUp")) {
                logger.info("User want to add checkUp note on "+date+" .User_id=" + idSession);
                req.setAttribute("staff", staff);
                req.setAttribute("doctor", doctors);
            }

            if (req.getParameter("regime").equals("DeleteCheckUp")) {
                logger.info("User want to delete checkUp note on "+date+" .User_id=" + idSession);
                req.setAttribute("changeCheckUpId", req.getParameter("id"));
                if (req.getAttribute("changeCheckUpId")!=null){
                    logger.info("User chose a checkUp note(checkUpNote_id=" + req.getParameter("id") +
                            ") in order to delete it from database.User_id=" + idSession);
                }
            }

            if (req.getParameter("regime").equals("EditCheckUp")) {
                req.setAttribute("changeCheckUpId", req.getParameter("id"));
                if (req.getParameter("id")!=null){
                req.setAttribute("changeCheckUpId",req.getParameter("id"));
                req.setAttribute("oneCheckUp",
                        checkUpCRUD.getCheckUp(Integer.parseInt(req.getParameter("id"))));
                    req.setAttribute("staff", staff);
                    req.setAttribute("doctor", doctors);
                    logger.info("User chose a checkUp note(checkUpNote_id=" + req.getParameter("id") +
                            ") in order to update it.User_id=" + idSession);
            }else {
                    logger.info("User want to update checkUp note on "+date+" .User_id=" + idSession);

                }

            }


            req.setAttribute("regime", req.getParameter("regime"));


        }else {
            if (idSession != -1) {
                logger.info("User open checkUp page on "+date+" .User_id=" + idSession);
            }
        }


        req.setAttribute("checkUp", checkUpCRUD.getCheckUp());
        req.setAttribute("date",date);
        }
        req.getRequestDispatcher("/CheckUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Staff_Entity> staff = constantTablesCRUD.readStaff("");
        List<Staff_Entity> doctors = constantTablesCRUD.readStaff("WHERE DEPARTMENT_ID=4");
        BlobToString blobToString = new BlobToString();
        if (req.getParameter("confirmCheckUp") != null) {
            CheckUp_Entity checkUpEntity = new CheckUp_Entity();
            checkUpEntity.setNSP(blobToString.getUTFString(req.getParameter("newPerson")));
            checkUpEntity.setPressure(blobToString.getUTFString(req.getParameter("newPressure")));
            checkUpEntity.setPpm(blobToString.getUTFString(req.getParameter("newPpm")));
            checkUpEntity.setWellBeing(blobToString.getUTFString(req.getParameter("newWellBeing")));
            checkUpEntity.setNote(blobToString.getUTFString(req.getParameter("newNote")));
            checkUpEntity.setDoctorNSP(blobToString.getUTFString(req.getParameter("newDoctor")));

            logger.info("User add a new checkUp note:" +
                    "\n NSP of patient:" + checkUpEntity.getNSP() +
                    "\n Pressure:" + checkUpEntity.getPressure() +
                    "\n Ppm:" + checkUpEntity.getPpm() +
                    "\n WellBeing:" + checkUpEntity.getWellBeing() +
                    "\n Note:" + checkUpEntity.getNote() +
                    "\n NSP of doctor:" + checkUpEntity.getDoctorNSP() + ".User_id=" + idSession);

            checkUpCRUD.createCheckUp(checkUpEntity, staff, doctors);
        }

        if (req.getParameter("deleteCheckUp") != null) {
            logger.info("User deleted CheckUp note(CheckUp_id=" + Integer.parseInt(req.getParameter("id"))
                    + "). User_id=" + idSession);
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

            logger.info("User updated checkUp note:" +
                    "\n new NSP of patient:" + checkUpEntity.getNSP() +
                    "\n new Pressure:" + checkUpEntity.getPressure() +
                    "\n new Ppm:" + checkUpEntity.getPpm() +
                    "\n new WellBeing:" + checkUpEntity.getWellBeing() +
                    "\n new Note:" + checkUpEntity.getNote() +
                    "\n new NSP of doctor:" + checkUpEntity.getDoctorNSP() + ".User_id=" + idSession);
            checkUpCRUD.updateCheckUp(checkUpEntity, staff, doctors);
            resp.sendRedirect("/checkUp");
            return;
        }

        if (req.getParameter("cancelDeletingCheckUp") != null) {
            logger.info("User canceled deleting/updating person. User_id=" + idSession);
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
