package services.CRUD_DB;

import services.CRUD_DB.ConstantTables_CRUD.Read_ConstantTables;
import services.ConnectionBD.ConnectionBD;
import services.Entity.Route_Entity;
import services.Entity.SignIn_Entity;
import services.Entity.Staff_Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantTablesCRUD {

    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();

    Read_ConstantTables read_constantTables = new Read_ConstantTables();

    public Map readConstantTable(String person) {
        Map map = new HashMap();


        map.put("departments", read_constantTables.getDepartments());
        map.put("positions", read_constantTables.getPositions());

        if (person.equals("UpdateInfoOther")) {
            map.put("workHours", read_constantTables.getWorkHours());
            map.put("workBuses", read_constantTables.getWorkBus());
        }


        return map;
    }


    public List<Route_Entity> readRoute() {
        String sql = "SELECT ID,ROUTE FROM ROUTE";
        List<Route_Entity> routeEntityList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Route_Entity routeEntity = new Route_Entity();
                routeEntity.setId(resultSet.getInt("id"));
                routeEntity.setRoute(resultSet.getString("ROUTE"));

                routeEntityList.add(routeEntity);
            }

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return routeEntityList;
    }


    public Map readConstantTableH() {

        Map map = new HashMap();


        map.put("bus", read_constantTables.getWorkBus());
        map.put("carmechanics", read_constantTables.getCarMechanics());

        return map;
    }

    public List<Staff_Entity> readStaff(String department) {
        List<Staff_Entity> staffEntityList = new ArrayList<>();
        String sql = "SELECT * FROM STAFF " + department;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Staff_Entity staffEntity = new Staff_Entity(
                        resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("patronymic"),
                        resultSet.getInt("AGE"));
                staffEntity.setId(resultSet.getInt("ID"));
                staffEntityList.add(staffEntity);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return staffEntityList;
    }


    public List<Staff_Entity> readStaffForSignUp(List<SignIn_Entity> newSignInEntityList) {
        List<Staff_Entity> signInEntityList = readStaff("WHERE (DEPARTMENT_ID=1 or DEPARTMENT_ID=4)");
        List<Staff_Entity> newS = new ArrayList<>();
        List idNewSignIn = new ArrayList();
        List idSignIn = new ArrayList();
        for (Staff_Entity s1 : signInEntityList) {
            idSignIn.add(s1.getId());
        }

        for (SignIn_Entity s1 : newSignInEntityList) {
            idNewSignIn.add(s1.getId());
        }

        idSignIn.removeAll(idNewSignIn);

        for (Staff_Entity staff : signInEntityList) {
            if (idSignIn.contains(staff.getId())) {
                newS.add(staff);
            }
        }

        return newS;
    }


}
