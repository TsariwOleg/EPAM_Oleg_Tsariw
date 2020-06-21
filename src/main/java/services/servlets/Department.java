package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import services.BlobToString;
import services.CRUD_DB.ConstantTablesCRUD;
import services.CRUD_DB.DepartmentCRUD;
import services.Entity.Position_Entity;
import services.Entity.WorkHours_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/department")
public class Department extends HttpServlet {
    DepartmentCRUD departmentCRUD = new DepartmentCRUD();
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();
    int idSession = -1;
    private final Logger logger = Logger.getRootLogger();

    @Override
    public void init() {
        BasicConfigurator.configure();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId") != null) {
            req.setAttribute("access", req.getSession().getAttribute("departmentId"));
            idSession = (Integer) req.getSession().getAttribute("departmentId");

            if (req.getParameter("regime") != null) {

                if (req.getParameter("regime").equals("EditWorkHours")) {
                    if (req.getParameter("id") != null) {
                        int id = Integer.parseInt(req.getParameter("id"));
                        logger.info("User chose a work hours(workHours_id=" + id +
                                ") in order to edit it.User_id=" + idSession);
                        req.setAttribute("changeID", id);
                        req.setAttribute("workHours",
                                constantTablesCRUD.getWorkHours("where WORK_HOUR_ID=" + id));
                    } else {
                        logger.info("User want to edit work hours.User_id=" + idSession);
                        req.setAttribute("workHours", constantTablesCRUD.getWorkHours(""));
                    }
                }


                if (req.getParameter("regime").equals("EditPosition")) {

                    if (req.getParameter("id") != null) {
                        int id = Integer.parseInt(req.getParameter("id"));
                        logger.info("User chose a position(workHours_id=" + id +
                                ") in order to edit it.User_id=" + idSession);
                        req.setAttribute("changeID", id);
                        req.setAttribute("position",
                                constantTablesCRUD.getPositions(" where POSITION_ID=" + id));

                    } else {
                        logger.info("User want to edit position.User_id=" + idSession);
                        req.setAttribute("position", constantTablesCRUD.getPositions(""));

                    }
                }


                req.setAttribute("regime", req.getParameter("regime"));
            }else {
                if (idSession != -1) {
                    logger.info("User open department page.User_id=" + idSession);
                }
            }
        } else {
            idSession = -1;
        }


        req.setAttribute("department", departmentCRUD.getForDepartmentPage());
        req.getRequestDispatcher("/Department.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("confirmWorkHours") != null) {
            constantTablesCRUD.createWorkHours(req.getParameter("newStartWorkHours"),
                    req.getParameter("newEndWorkHours"));
            logger.info("User created new work hours" +
                    "\n Start work hours:" + req.getParameter("newStartWorkHours") +
                    "\n End work hours:" +  req.getParameter("newEndWorkHours") + ".User_id=" + idSession);
        }


        if (req.getParameter("deleteWorkHours") != null) {
            logger.info("User deleted work hours(WorkHours_id=" +(req.getParameter("id"))
                    + "). User_id=" + idSession);
            constantTablesCRUD.deleteWorkHours(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/department");
            return;
        }


        if (req.getParameter("updateWorkHours") != null) {
            WorkHours_Entity workHoursEntity = new WorkHours_Entity();
            workHoursEntity.setId(Integer.parseInt(req.getParameter("id")));
            workHoursEntity.setStartWorkHour(req.getParameter("newStartWorkHours"));
            workHoursEntity.setEndWorkHour(req.getParameter("newEndWorkHours"));

            logger.info("User updated information about work hours(WorkHours_id=" + req.getParameter("id") + "):" +
                    "\n New start work hour:" + workHoursEntity.getStartWorkHour() +
                    "\n New end work hour:" + workHoursEntity.getEndWorkHour() + ".User_id=" + idSession);
            constantTablesCRUD.updateWorkHours(workHoursEntity);
            resp.sendRedirect("/department");
            return;
        }

        if (req.getParameter("cancelEdittingWorkHours") != null) {
            logger.info("User canceled deleting/updating WorkHours/Position. User_id=" + idSession);
            resp.sendRedirect("/department");
            return;
        }



        if (req.getParameter("confirmPosition") != null) {

            constantTablesCRUD.createPosition(req.getParameter("newPosition"),
                    Integer.parseInt(req.getParameter("newSalary")));
            logger.info("User created new position" +
                    "\n Position:" + req.getParameter("newPosition") +
                    "\n Salary:" +  req.getParameter("newSalary" + ".User_id=" + idSession));
        }

        if (req.getParameter("deletePosition") != null) {
            constantTablesCRUD.deletePosition(Integer.parseInt(req.getParameter("id")));
            logger.info("User deleted position(Position_id=" +(req.getParameter("id"))
                    + "). User_id=" + idSession);
            resp.sendRedirect("/department");
            return;
        }


        if (req.getParameter("updatePosition") != null) {
            Position_Entity positionEntity = new Position_Entity();
            positionEntity.setId(Integer.parseInt(req.getParameter("id")));
            positionEntity.setPosition(new BlobToString().getUTFString(req.getParameter("newPosition")));
            positionEntity.setSalary(Integer.parseInt(req.getParameter("newSalary")));
            logger.info("User updated information about position(position_id=" + positionEntity.getId() + "):" +
                    "\n New position:" + positionEntity.getPosition() +
                    "\n New salary:" + positionEntity.getSalary() + ".User_id=" + idSession);
            constantTablesCRUD.updatePosition(positionEntity);
            resp.sendRedirect("/department");
            return;
        }

        doGet(req, resp);
    }
}
