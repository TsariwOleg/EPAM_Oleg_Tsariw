package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import services.CRUD_DB.ConstantTablesCRUD;
import services.CRUD_DB.HistoryOfRepairCRUD;
import services.BlobToString;
import services.Entity.HistoryOfRepair_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/historyOfRepair")
public class HistoryOfRepair extends HttpServlet {
    HistoryOfRepairCRUD historyOfRepairCRUD = new HistoryOfRepairCRUD();
    List<HistoryOfRepair_Entity> historyOfRepairEntityList = new ArrayList<>();
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();
    Map map = constantTablesCRUD.readConstantTableH();
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

        } else {
            idSession = -1;
        }


        if (req.getParameter("target") != null) {
            historyOfRepairEntityList = historyOfRepairCRUD.getHistory(Integer.parseInt(req.getParameter("id")),
                    req.getParameter("target"));
            req.setAttribute("id", Integer.parseInt(req.getParameter("id")));
            req.setAttribute("target", req.getParameter("target"));

        } else {
            historyOfRepairEntityList = historyOfRepairCRUD.getHistory(0, "all");

        }

        if (req.getParameter("regime") != null) {
            req.setAttribute("regime", req.getParameter("regime"));


            if (("AddNewHistory").equals(req.getParameter("regime"))) {
                req.setAttribute("bus", map.get("bus"));
                req.setAttribute("carmechanics", map.get("carmechanics"));
                if (idSession != -1) {
                    logger.info("User want to add a new history of repair to database.User_id=" + idSession);
                }
            }

            if (("DeleteHistory").equals(req.getParameter("regime"))) {
                req.setAttribute("DeleteHistory", req.getParameter("DeleteHistory"));
                if (idSession != -1) {
                    logger.info("User want to delete history of repair from database.User_id=" + idSession);
                }
                if (req.getParameter("deleteId") != null) {
                    req.setAttribute("deleteId", req.getParameter("deleteId"));
                    if (idSession != -1) {
                        logger.info("User chose a history of repair(delete_id=" + req.getParameter("deleteId") +
                                ") in order to delete it from database.User_id=" + idSession);
                    }
                }
            }

        } else {
            if (idSession != -1) {
                if (req.getParameter("target") != null) {

                    logger.info("User opened history of repair page.Target=" + req.getParameter("target")
                            + ".Target_id=" + req.getParameter("id")
                            + "User_id=" + idSession);
                } else {
                    logger.info("User opened history of repair page."
                            + "User_id=" + idSession);
                }
            }
        }

        req.setAttribute("history", historyOfRepairEntityList);
        req.getRequestDispatcher("/HistoryOfRepair.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("deleteHistory") != null) {
            String id = req.getParameter("id");
            String target = req.getParameter("target");

            historyOfRepairCRUD.deleteHistory(Integer.parseInt(req.getParameter("deleteId")));
            if (req.getParameter("target") == null) {
                logger.info("User deleted history.User_id=" + idSession);
                resp.sendRedirect("/historyOfRepair");
            } else {
                logger.info("User deleted history.Target=" + target
                        + ".Id=" + id
                        + "User_id=" + idSession);
                resp.sendRedirect("/historyOfRepair?target=" + target + "&id=" + id);
            }

            return;
        }
        if (req.getParameter("confirmHistory") != null) {
            BlobToString blobToString = new BlobToString();
            HistoryOfRepair_Entity historyOfRepairEntity = new HistoryOfRepair_Entity();
           String target="";
            if (req.getParameter("target") != null) {

                if (req.getParameter("target").equals("bus")) {
                    historyOfRepairEntity.setBus_id(Integer.parseInt(req.getParameter("id")));
                    historyOfRepairEntity.setNSP(blobToString.getUTFString(req.getParameter("newCarMechanics")));
                    target="\n Bus_id="+historyOfRepairEntity.getBus_id()
                            +"\n NSP="+historyOfRepairEntity.getNSP();
                }

                if (req.getParameter("target").equals("carMechanic")) {
                    historyOfRepairEntity.setMechanic_id(Integer.parseInt(req.getParameter("id")));
                    historyOfRepairEntity.setBus(blobToString.getUTFString(req.getParameter("newBus")));
                    target="\n Mechanic_id="+historyOfRepairEntity.getMechanic_id()
                            +"\n BusNo="+historyOfRepairEntity.getBus();
                }

            }else  {
                historyOfRepairEntity.setBus(blobToString.getUTFString(req.getParameter("newBus")));
                historyOfRepairEntity.setNSP(blobToString.getUTFString(req.getParameter("newCarMechanics")));
                target="\n Bus="+historyOfRepairEntity.getBus()
                        +"\n NSP="+historyOfRepairEntity.getNSP();
            }

            historyOfRepairEntity.setCostOfRepair(Integer.parseInt(req.getParameter("newCostOfRepair")));
            historyOfRepairEntity.setNote(blobToString.getUTFString(req.getParameter("newNote")));
            historyOfRepairEntity.setMalfunction(blobToString.getUTFString(req.getParameter("newMalfunction")));

            logger.info("User added a new history of repair:" +target+
                    "\n Cost of repair:" + historyOfRepairEntity.getCostOfRepair() +
                    "\n Note=" + historyOfRepairEntity.getNote() +
                    "\n Malfunction=" + historyOfRepairEntity.getMalfunction() +
                    ".User_id="+idSession);

            historyOfRepairCRUD.createHistory(historyOfRepairEntity, map);

        }

        if (req.getParameter("cancelDeletinfHistory") != null) {
            String id = req.getParameter("id");
            String target = req.getParameter("target");
            logger.info("User canceled deleting history of repair. User_id=" + idSession);

            if (req.getParameter("target") == null) {
                resp.sendRedirect("/historyOfRepair");
            } else {
                resp.sendRedirect("/historyOfRepair?target=" + target + "&id=" + id);
            }
            return;
        }

        doGet(req, resp);
    }
}
