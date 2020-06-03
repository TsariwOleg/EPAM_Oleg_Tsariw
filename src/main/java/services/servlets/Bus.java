package services.servlets;

import services.CRUD_DB.BusCRUD;
import services.CRUD_DB.ConstantTablesCRUD;
import services.Entity.BlobToString;
import services.Entity.Buses_Entity;
import services.Entity.Route_Entity;

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
 private    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mapBus= busCRUD.getBus(Integer.parseInt(req.getParameter("id")));

        req.setAttribute("bus",mapBus.get("bus"));
        req.setAttribute("route",mapBus.get("route"));

        if(req.getParameter("regime")!=null){
            if(req.getParameter("regime").equals("UpdateInfoBus")){
                req.setAttribute("constRoute",constantTablesCRUD.readConstantTable());
                System.out.println("asfsaf");
                for (Route_Entity rou:constantTablesCRUD.readConstantTable()) {
                    System.out.println(rou);
                }
            }
           req.setAttribute("regime",req.getParameter("regime"));
        }

        req.getRequestDispatcher("/Bus.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlobToString blobToString = new BlobToString();
        if (req.getParameter("deleteBus")!=null){
            busCRUD.deleteBus(Integer.parseInt(req.getParameter("id")));
        }

        if (req.getParameter("confirmBus")!=null){
            Buses_Entity busesEntity = new Buses_Entity();
            busesEntity.setBusNo(blobToString.getUTFString(req.getParameter("newBusNo")));
            busesEntity.setModel(blobToString.getUTFString(req.getParameter("newModel")));
            busesEntity.setYearOfIssue(Integer.parseInt(req.getParameter("newYearOfIssue")));
            busesEntity.setFuelConsumption(blobToString.getUTFString(req.getParameter("newFuelConsumption")));
            busesEntity.setRoute(blobToString.getUTFString(req.getParameter("newRoute")));

            busCRUD.updateBus(Integer.parseInt(req.getParameter("id")),busesEntity,
                    constantTablesCRUD.readConstantTable()     );

        }
        doGet(req,resp);
    }
}
