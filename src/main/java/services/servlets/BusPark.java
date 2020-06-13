package services.servlets;

import services.CRUD_DB.BusParkCRUD;
import services.CRUD_DB.ConstantTablesCRUD;
import services.BlobToString;
import services.Entity.Buses_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/buspark")
public class BusPark extends HttpServlet {
    BusParkCRUD busParkCRUD = new BusParkCRUD();
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId")!=null){
            req.setAttribute("access",req.getSession().getAttribute("departmentId"));
        }
        req.setAttribute("buses",busParkCRUD.readBusPark());

        if (req.getParameter("regime")!=null){
            if (req.getParameter("regime").equals("AddBus")){
                req.setAttribute("regime",req.getParameter("regime"));
                req.setAttribute("route",constantTablesCRUD.readRoute());
            }

            if (req.getParameter("regime").equals("DeleteBus")){
                req.setAttribute("regime",req.getParameter("regime"));
                if (req.getParameter("id")!=null){
                    req.setAttribute("deleteBusId",req.getParameter("id"));
                }
            }

        }



        req.getRequestDispatcher("/BusPark.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("cancelDeletingBus")!=null){
            resp.sendRedirect("/buspark");
            return;
        }

        if (req.getParameter("deleteBus")!=null){
            busParkCRUD.deleteBus(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/buspark");
            return;
        }

        if (req.getParameter("confirmBus")!=null){
            BlobToString blobToString = new BlobToString();



            Buses_Entity busesEntity = new Buses_Entity();
            busesEntity.setBusNo(blobToString.getUTFString(req.getParameter("newBusNo")));
            busesEntity.setYearOfIssue(Integer.parseInt(req.getParameter("newYear")));
            busesEntity.setModel(blobToString.getUTFString(req.getParameter("newModel")));
            busesEntity.setRoute(blobToString.getUTFString(req.getParameter("newRoute")));

            BusParkCRUD busParkCRUD = new BusParkCRUD();
            busParkCRUD.createBus(busesEntity,constantTablesCRUD.readRoute());
        }

        doGet(req,resp);
    }
}
