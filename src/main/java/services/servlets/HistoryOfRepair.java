package services.servlets;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId")!=null){
            req.setAttribute("access",req.getSession().getAttribute("departmentId"));
        }

        if (("AddNewHistory").equals(req.getParameter("regime"))){
            req.setAttribute("bus",map.get("bus"));
            req.setAttribute("carmechanics",map.get("carmechanics"));
        }


        if (req.getParameter("target") != null) {
            historyOfRepairEntityList = historyOfRepairCRUD.getHistory(Integer.parseInt(req.getParameter("id")), req.getParameter("target"));

            req.setAttribute("id", Integer.parseInt(req.getParameter("id")));
            req.setAttribute("target", req.getParameter("target"));

        }else {
            historyOfRepairEntityList=historyOfRepairCRUD.getHistory(0,"all");

        }

        if (req.getParameter("regime") != null) {
            req.setAttribute("regime", req.getParameter("regime"));
        }
        if (req.getParameter("DeleteHistory") != null) {
            req.setAttribute("DeleteHistory",req.getParameter("DeleteHistory"));

        }
        if (req.getParameter("deleteId") != null) {
            req.setAttribute("deleteId",req.getParameter("deleteId"));
        }

        req.setAttribute("history", historyOfRepairEntityList);
        req.getRequestDispatcher("/HistoryOfRepair.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("deleteHistory")!=null){
            String id=req.getParameter("id");
            String target=req.getParameter("target");
            historyOfRepairCRUD.deleteHistory(Integer.parseInt(req.getParameter("deleteId")));
            if (req.getParameter("target")==null){
            resp.sendRedirect("/historyOfRepair");
            }else {
                resp.sendRedirect("/historyOfRepair?target="+target+"&id="+id);
            }

            return;
        }
        if (req.getParameter("confirmHistory")!=null){
            BlobToString blobToString = new BlobToString();
            HistoryOfRepair_Entity historyOfRepairEntity = new HistoryOfRepair_Entity();
            if(req.getParameter("target")!=null){

                if (req.getParameter("target").equals("bus")){
                    historyOfRepairEntity.setBus_id(Integer.parseInt(req.getParameter("id")));
                    System.out.println(historyOfRepairEntity.getBus_id());
                    historyOfRepairEntity.setNSP(blobToString.getUTFString(req.getParameter("newCarMechanics")));
                }
                if (req.getParameter("target").equals("carMechanic")){
                    historyOfRepairEntity.setMechanic_id(Integer.parseInt(req.getParameter("id")));
                    historyOfRepairEntity.setBus(blobToString.getUTFString(req.getParameter("newBus")));
                }
            }

            if (req.getParameter("target")==null){
                historyOfRepairEntity.setBus(blobToString.getUTFString(req.getParameter("newBus")));
                historyOfRepairEntity.setNSP(blobToString.getUTFString(req.getParameter("newCarMechanics")));
            }
            historyOfRepairEntity.setCostOfRepair(Integer.parseInt(req.getParameter("newCostOfRepair")));
            historyOfRepairEntity.setNote(blobToString.getUTFString(req.getParameter("newNote")));
            historyOfRepairEntity.setMalfunction(blobToString.getUTFString(req.getParameter("newMalfunction")));


            historyOfRepairCRUD.createHistory(historyOfRepairEntity,map);

        }

        if (req.getParameter("cancelDeletinfHistory")!=null){
            String id=req.getParameter("id");
            String target=req.getParameter("target");

            if (req.getParameter("target")==null){
                resp.sendRedirect("/historyOfRepair");
            }else {
                resp.sendRedirect("/historyOfRepair?target="+target+"&id="+id);
            }
            return;
        }

        doGet(req, resp);
    }
}
