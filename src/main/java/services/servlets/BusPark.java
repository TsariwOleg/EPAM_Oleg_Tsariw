package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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


        if (req.getParameter("regime")!=null){
            if (req.getParameter("regime").equals("AddBus")){
                logger.info("User want to add a new bus to database.User_id=" + idSession);
                req.setAttribute("regime",req.getParameter("regime"));
                req.setAttribute("route",constantTablesCRUD.readRoute());
            }

            if (req.getParameter("regime").equals("DeleteBus")){
                req.setAttribute("regime",req.getParameter("regime"));
                if (req.getParameter("id")!=null){
                    req.setAttribute("deleteBusId",req.getParameter("id"));
                    logger.info("User chose a bus(bus_id=" + req.getParameter("id") +
                            ") in order to delete it from database.User_id=" + idSession);
                }else {
                    logger.info("User want to delete a bus from database.User_id=" + idSession);
                }
            }

        }else {
            if (idSession != -1) {
                logger.info("User open busPark page .User_id=" + idSession);
            }
        }

        }else {
            idSession = -1;
        }


        req.setAttribute("buses",busParkCRUD.readBusPark());

        req.getRequestDispatcher("/BusPark.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("cancelDeletingBus")!=null){
            logger.info("User canceled deleting bus. User_id=" + idSession);
            resp.sendRedirect("/buspark");
            return;
        }

        if (req.getParameter("deleteBus")!=null){
            logger.info("User deleted bus(bus=" + Integer.parseInt(req.getParameter("id"))
                    + "). User_id=" + idSession);
            busParkCRUD.deleteBus(Integer.parseInt(req.getParameter("id")));
            System.out.println("dfsdf");
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

            logger.info("User added a new bus:" +
                    "\n  BusNo:" + busesEntity.getBusNo() +
                    "\n  Year of issue=" + busesEntity.getYearOfIssue() +
                    "\n  Model=" + busesEntity.getModel() +
                    "\n Route=" + busesEntity.getRoute() +".User_id="+idSession);

            busParkCRUD.createBus(busesEntity,constantTablesCRUD.readRoute());
        }

        doGet(req,resp);
    }
}
