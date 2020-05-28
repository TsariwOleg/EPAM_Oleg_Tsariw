package services.CRUD_DB;

import services.CRUD_DB.ConstantTables_CRUD.Read_ConstantTables;
import services.ConnectionBD.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class ConstantTablesCRUD /*extends ConnectionBD*/ {
//    Connection connection = getConnection();
ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    public Map readConstantTable (String person){
        Map map = new HashMap();
        Statement statement;

        try{
            statement= connection.createStatement();
            Read_ConstantTables read_constantTables = new Read_ConstantTables();

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

}
