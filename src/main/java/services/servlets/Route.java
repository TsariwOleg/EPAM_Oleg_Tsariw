package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import services.BlobToString;
import services.CRUD_DB.OneRouteCRUD;
import services.Entity.Route_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route")
public class Route extends HttpServlet {
    OneRouteCRUD oneRouteCRUD = new OneRouteCRUD();
    int id = 0;
    BlobToString blobToString = new BlobToString();
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


        if (req.getParameter("id") != null) {
            id = Integer.parseInt(req.getParameter("id"));
        }

        if (idSession!=-1){
        if (req.getParameter("regime") != null) {
            req.setAttribute("regime", req.getParameter("regime"));
            logger.info("User want to edit route.Route_id=" + req.getParameter("id") + ".User_id=" + idSession);
        } else {
            logger.info("User open route page(Route_id=" + id +").User_id=" + idSession);
        }
        }

        req.setAttribute("route", oneRouteCRUD.getRoute(id));
        req.setAttribute("buses", oneRouteCRUD.getBuses(id));


        req.getRequestDispatcher("/Route.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("confirmRoute") != null) {
            Route_Entity routeEntity = new Route_Entity();
            routeEntity.setRoute(blobToString.getUTFString(req.getParameter("newRoute")));
            routeEntity.setFullRoute(blobToString.getUTFString(req.getParameter("newFullRoute")));
            routeEntity.setAvarageProfit(Integer.parseInt(req.getParameter("newAvarageProfit")));
            routeEntity.setAvarageFuelConsuption(Integer.parseInt(req.getParameter("newAvarageFuelConsumption")));

            oneRouteCRUD.updateRoute(id, routeEntity);
            logger.info("User edited a route(Route_id=" + id + "): " +
                    " \n new Route:" + routeEntity.getRoute() +
                    "\n  new FullRoute:" + routeEntity.getFullRoute() +
                    "\n  new AvarageProfit:" + routeEntity.getAvarageProfit() +
                    "\n  new AvarageFuelConsuption:" + routeEntity.getAvarageFuelConsuption() +
                    ".User_id=" + idSession);
        }

        if (req.getParameter("deleteRoute") != null) {
            oneRouteCRUD.deleteRoute(id);
            logger.info("User cleared information about route(Route_id=" + id + ").User_id=" + idSession);
        }

        doGet(req, resp);
    }
}
