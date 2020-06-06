package services.servlets;

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



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Route_Entity> routeEntityList = routesCRUD.getRoute();

        if (req.getSession().getAttribute("departmentId")!=null){
            req.setAttribute("access",req.getSession().getAttribute("departmentId"));
        }
        if (req.getParameter("regime")!=null){
            if (req.getParameter("regime").equals("DeleteRoute")){
                if(req.getParameter("id")!=null){
                    req.setAttribute("deleteRouteId",req.getParameter("id"));
                }

            }
            req.setAttribute("regime",req.getParameter("regime"));

        }

        req.setAttribute("route",routeEntityList);
        req.getRequestDispatcher("/Routes.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlobToString blobToString = new BlobToString();
        if (req.getParameter("cancelDeletingRoute")!=null){
            resp.sendRedirect("/routes");
            return;
        }

        if (req.getParameter("deleteRoute")!=null){
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


        }

        doGet(req,resp);
    }
}
