package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import services.BlobToString;
import services.CRUD_DB.ConstantTablesCRUD;
import services.CRUD_DB.Staff_CRUD;
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
    int idSession = -1;

    private final Logger logger = Logger.getRootLogger();

    @Override
    public void init() {
        BasicConfigurator.configure();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        staffEntityList = staff_crud.getStaff();

        if (req.getSession().getAttribute("departmentId") != null) {
            req.setAttribute("access", req.getSession().getAttribute("departmentId"));
            idSession = (Integer) req.getSession().getAttribute("departmentId");
        }else {
            idSession=-1;
        }

        if (req.getParameter("regime") != null) {

            if (req.getParameter("regime").equals("AddPerson")) {
                logger.info("User want to add a new person to database.User_id=" + idSession);

                mapConstantTable = constantTablesCRUD.readConstantTable("");
                req.setAttribute("regime", req.getParameter("regime"));
                req.setAttribute("departments", mapConstantTable.get("departments"));
            }
            if (req.getParameter("regime").equals("DeletePerson")) {

                req.setAttribute("regime", req.getParameter("regime"));
                if (req.getParameter("id") != null) {
                    req.setAttribute("deletePersonId", req.getParameter("id"));

                    logger.info("User chose a person(person_id=" + req.getParameter("id") +
                            ") in order to delete him from database.User_id=" + idSession);
                } else {
                    logger.info("User want to delete a person from database.User_id=" + idSession);
                }
            }
        } else {
            if (idSession != -1) {
                logger.info("User open staff page.User_id=" + idSession);
            }
        }

        req.setAttribute("staff", staffEntityList);


        try {
            req.getRequestDispatcher("/Staff.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("deletePerson") != null) {
            logger.info("User deleted person(person_id=" + req.getParameter("id")
                    + "). User_id=" + idSession);
            staff_crud.deletePerson(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/staff");
            return;
        }
        if (req.getParameter("cancelDeletingPerson") != null) {
            logger.info("User canceled deleting person. User_id=" + idSession);
            resp.sendRedirect("/staff");
            return;
        }


        if (req.getParameter("confirmPerson") != null) {

            BlobToString blobToString = new BlobToString();
            Staff_Entity staffEntity = new Staff_Entity(blobToString.getUTFString(req.getParameter("newName")),
                    blobToString.getUTFString(req.getParameter("newSurname")),
                    blobToString.getUTFString(req.getParameter("newPatronymic")),
                    Integer.parseInt(req.getParameter("newAge"))
            );
            staffEntity.setDepartment(blobToString.getUTFString(req.getParameter("newDepartment")));
            logger.info("User added a new person:" +
                    "\n Name:" + staffEntity.getName() +
                    "\n Surname=" + staffEntity.getSurname() +
                    "\n Patronymic=" + staffEntity.getPatronymic() +
                    "\n Age=" + staffEntity.getAge() +
                    "\n Department=" + staffEntity.getDepartment()+".User_id="+idSession);
            staff_crud.createPerson(staffEntity, mapConstantTable);
        }

        doGet(req, resp);
    }


}
