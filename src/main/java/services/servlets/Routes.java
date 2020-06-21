package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import services.BlobToString;
import services.CRUD_DB.RoutesCRUD;
import services.Entity.Route_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/routes")
public class Routes extends HttpServlet {
    RoutesCRUD routesCRUD = new RoutesCRUD();
    int idSession = -1;
    private final Logger logger = Logger.getRootLogger();

    @Override
    public void init() {
        BasicConfigurator.configure();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Route_Entity> routeEntityList = routesCRUD.getRoute();

        if (req.getSession().getAttribute("departmentId")!=null){
            req.setAttribute("access",req.getSession().getAttribute("departmentId"));
            idSession = (Integer) req.getSession().getAttribute("departmentId");
        } else {
            idSession = -1;
        }

        if (req.getParameter("regime")!=null){
            if (req.getParameter("regime").equals("DeleteRoute")){
                if(req.getParameter("id")!=null){
                    req.setAttribute("deleteRouteId",req.getParameter("id"));
                    logger.info("User chose a route(Route_id=" + req.getParameter("id") +
                            ") in order to delete it from database.User_id=" + idSession);
                }else {
                    logger.info("User want to delete a route.User_id=" + idSession);
                }

            }else {
                logger.info("User want to add a new route to database.User_id=" + idSession);
            }

            req.setAttribute("regime",req.getParameter("regime"));

        }else {
            logger.info("User open routes page.User_id=" + idSession);

        }

        req.setAttribute("route",routeEntityList);
        req.getRequestDispatcher("/Routes.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlobToString blobToString = new BlobToString();
        if (req.getParameter("cancelDeletingRoute")!=null){
            logger.info("User canceled deleting route. User_id=" + idSession);
            resp.sendRedirect("/routes");
            return;
        }

        if (req.getParameter("deleteRoute")!=null){
            logger.info("User deleted route(Route_id=" + (req.getParameter("id"))
                    + "). User_id=" + idSession);
            routesCRUD.deleteRoute(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/routes");
            return;
        }

        if (req.getParameter("confirmRoute")!=null){
            Route_Entity routeEntity = new Route_Entity();
            routeEntity.setRoute(blobToString.getUTFString(req.getParameter("newRoute")));
            routeEntity.setAvarageProfit(Integer.parseInt(req.getParameter("newProfit")));
            routeEntity.setAvarageFuelConsuption(Integer.parseInt(req.getParameter("newFuelConsuption")));
            routesCRUD.createRoute(routeEntity);
            logger.info("User added a new route:" +
                    "\n Route:" + routeEntity.getRoute() +
                    "\n AvarageProfit=" + routeEntity.getAvarageProfit() +
                    "\n AvarageFuelConsuption=" + routeEntity.getAvarageFuelConsuption()+".User_id="+idSession);

        }

        doGet(req,resp);
    }
}
