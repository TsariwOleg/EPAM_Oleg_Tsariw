package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import services.CRUD_DB.BusCRUD;
import services.CRUD_DB.ConstantTablesCRUD;
import services.BlobToString;
import services.Entity.Buses_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/bus")
public class Bus extends HttpServlet {
    BusCRUD busCRUD = new BusCRUD();
    private Map mapBus;
    private ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();
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
                if (req.getParameter("regime").equals("UpdateInfoBus")) {
                    req.setAttribute("constRoute", constantTablesCRUD.readRoute());
                    logger.info("User want to update information about bus (bus_id="
                            + req.getParameter("id") + ").User_id=" + idSession);
                }
                req.setAttribute("regime", req.getParameter("regime"));
            } else {
                if (idSession != -1) {
                    logger.info("User open bus page(bus_id=" + req.getParameter("id") + ").User_id=" + idSession);
                }
            }

        } else {
            idSession = -1;
        }

        mapBus = busCRUD.getBus(Integer.parseInt(req.getParameter("id")));

        req.setAttribute("bus", mapBus.get("bus"));
        req.setAttribute("route", mapBus.get("route"));

        req.getRequestDispatcher("/Bus.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlobToString blobToString = new BlobToString();
        if (req.getParameter("deleteBus") != null) {
            logger.info("User cleared information about bus (bus_id="
                    + req.getParameter("id") + ").User_id=" + idSession);
            busCRUD.deleteBus(Integer.parseInt(req.getParameter("id")));
        }

        if (req.getParameter("confirmBus") != null) {
            Buses_Entity busesEntity = new Buses_Entity();
            busesEntity.setBusNo(blobToString.getUTFString(req.getParameter("newBusNo")));
            busesEntity.setModel(blobToString.getUTFString(req.getParameter("newModel")));
            busesEntity.setYearOfIssue(Integer.parseInt(req.getParameter("newYearOfIssue")));
            busesEntity.setFuelConsumption(blobToString.getUTFString(req.getParameter("newFuelConsumption")));
            System.out.println(blobToString.getUTFString(req.getParameter("newFuelConsumption")));
            busesEntity.setRoute(blobToString.getUTFString(req.getParameter("newRoute")));

            logger.info("User updated information about bus(bus=" + req.getParameter("id") + "):" +
                    "\n New busNo:" + busesEntity.getBusNo() +
                    "\n New model of bus:" + busesEntity.getModel() +
                    "\n New year of issue:" + busesEntity.getYearOfIssue() +
                    "\n New fuel consumption:" + busesEntity.getFuelConsumption() + ".User_id=" + idSession);

            busCRUD.updateBus(Integer.parseInt(req.getParameter("id")), busesEntity,
                    constantTablesCRUD.readRoute());

        }
        doGet(req, resp);
    }
}
