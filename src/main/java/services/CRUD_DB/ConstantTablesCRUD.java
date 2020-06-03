package services.CRUD_DB;

import services.CRUD_DB.ConstantTables_CRUD.Read_ConstantTables;
import services.ConnectionBD.ConnectionBD;
import services.Entity.Route_Entity;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantTablesCRUD /*extends ConnectionBD*/ {
//    Connection connection = getConnection();
ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();

    Read_ConstantTables read_constantTables = new Read_ConstantTables();
    public Map readConstantTable (String person){
        Map map = new HashMap();
        Statement statement;

        try{
            statement= connection.createStatement();


            map.put("departments",read_constantTables.getDepartments(statement));
            map.put("positions",read_constantTables.getPositions(statement));

            if(person.equals("UpdateInfoOther")){
                map.put("workHours",read_constantTables.getWorkHours(statement));
                map.put("workBuses" , read_constantTables.getWorkBus(statement));
            }

        }catch (SQLException e){
            System.out.println(e);
        }
        return map;
    }


    public List<Route_Entity> readConstantTable(){
        List<Route_Entity> routeEntityList = null;
        Statement statement;
        try{
            statement= connection.createStatement();
            routeEntityList=read_constantTables.getRoute(statement);

        }catch (SQLException e){
            System.out.println(e);
        }


        return routeEntityList;
    }

    public Map readConstantTableH(){
        Statement statement;
        Map map = new HashMap();

        try {
            statement= connection.createStatement();
            map.put("bus", read_constantTables.getWorkBus(statement));
            map.put("carmechanics",read_constantTables.getCarMechanics(statement));
        }catch (SQLException e){
            System.out.println(e);
        }
        return map;
    }

}
