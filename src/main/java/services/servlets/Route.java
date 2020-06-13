package services.servlets;

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
    int id=0;
    BlobToString blobToString = new BlobToString();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId")!=null){
            req.setAttribute("access",req.getSession().getAttribute("departmentId"));
        }


        if (req.getParameter("regime")!=null){
            req.setAttribute("regime",req.getParameter("regime"));
        }
if (req.getParameter("id")!=null) {
    id = Integer.parseInt(req.getParameter("id"));
}
        req.setAttribute("route",oneRouteCRUD.getRoute(id));
        req.setAttribute("buses",oneRouteCRUD.getBuses(id));


       req.getRequestDispatcher("/Route.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("confirmRoute")!=null){
            Route_Entity routeEntity =new Route_Entity();
            routeEntity.setRoute(blobToString.getUTFString(req.getParameter("newRoute")));
            routeEntity.setFullRoute(blobToString.getUTFString(req.getParameter("newFullRoute")));
            routeEntity.setAvarageProfit(Integer.parseInt(req.getParameter("newAvarageProfit")));
            routeEntity.setAvarageFuelConsuption(Integer.parseInt(req.getParameter("newAvarageFuelConsumption")));

            oneRouteCRUD.updateRoute(id,routeEntity);

        }

        if (req.getParameter("deleteRoute")!=null){
            oneRouteCRUD.deleteRoute(id);

        }

        doGet(req,resp);
    }
}
