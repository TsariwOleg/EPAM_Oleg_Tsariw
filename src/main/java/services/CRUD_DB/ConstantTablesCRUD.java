package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstantTablesCRUD {
    private final Logger logger = Logger.getRootLogger();
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();


    public Map readConstantTable(String person) {
        Map map = new HashMap();
        map.put("departments", getDepartments());
        map.put("positions", getPositions(""));
        if (person.equals("UpdateInfoOther")) {
            map.put("workHours", getWorkHours(""));
            map.put("workBuses", getWorkBus());
        }
        return map;
    }

    public Map readConstantTableH() {
        Map map = new HashMap();
        map.put("bus", getWorkBus());
        map.put("carmechanics", getCarMechanics());
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
            logger.error("SQLException in readRoute block:"+e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in readRoute block(close statement):"+e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in readRoute block(close resultSet):"+e);
            }
        }
        return routeEntityList;
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
                Staff_Entity staffEntity = new Staff_Entity();
                staffEntity.setName(resultSet.getString("NAME"));
                staffEntity.setSurname(resultSet.getString("SURNAME"));
                staffEntity.setPatronymic(resultSet.getString("PATRONYMIC"));
                staffEntity.setAge(resultSet.getInt("AGE"));
                staffEntity.setId(resultSet.getInt("ID"));
                staffEntityList.add(staffEntity);

            }
        } catch (SQLException e) {
            logger.error("SQLException in readConstantTableH block:"+e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in readConstantTableH block(close statement):"+e);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in readConstantTableH block(close resultSet):"+e);
            }
        }
        return staffEntityList;
    }


    //This method we use for getting users that arent registering on the website and belong to Administration and Doctors
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

    public List<WorkHours_Entity> getWorkHours(String string){
        String sql = "SELECT * FROM WORK_HOURS "+string;
        List<WorkHours_Entity> workHoursEntityList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                WorkHours_Entity workHoursEntity = new WorkHours_Entity();
                workHoursEntity.setId(resultSet.getInt("WORK_HOUR_ID"));
                workHoursEntity.setStartWorkHour(resultSet.getString("START_WORK_HOURS"));
                workHoursEntity.setEndWorkHour(resultSet.getString("END_WORK_HOURS"));
                workHoursEntityList.add(workHoursEntity);
            }

        }catch (SQLException e){
            logger.error("SQLException in getWorkHours block:"+e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getWorkHours block(close statement):"+e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getWorkHours block(close resultSet):"+e);

            }
        }

        return workHoursEntityList;
    }


    public void createWorkHours(Object startWorkHours,Object endWorkHours ){
        String sql = "INSERT INTO WORK_HOURS VALUES(?,?,?)";
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(WORK_HOUR_ID) FROM WORK_HOURS");
            int max = 0;
            if (resultSet.next()) {
                max = resultSet.getInt(1)+1;
            }

            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,max);
            preparedStatement.setString(2,(String) startWorkHours);
            preparedStatement.setString(3,(String)endWorkHours);
            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("SQLException in createWorkHours block:"+e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createWorkHours block(close statement):"+e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createWorkHours block(close resultSet):"+e);
            }

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createWorkHours block(close preparedStatement):"+e);
            }
        }
    }


    public void deleteWorkHours(int id){
        String sql = "DELETE FROM WORK_HOURS WHERE WORK_HOUR_ID="+id;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement=connection.prepareStatement("UPDATE BUS_DRIVERS SET " +
                    "WORK_HOUR_ID=NULL WHERE WORK_HOUR_ID="+id);
            preparedStatement.execute();
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.execute();

        }catch (SQLException e){
            logger.error("SQLException in deleteWorkHours block:"+e);
        }finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteWorkHours block(close statement):"+e);
            }
        }
    }




    public void updateWorkHours(WorkHours_Entity workHoursEntity){
        String sql = "UPDATE WORK_HOURS SET START_WORK_HOURS=? , END_WORK_HOURS=?  " +
                "WHERE WORK_HOUR_ID="+workHoursEntity.getId();
        PreparedStatement preparedStatement = null;
        try {
           preparedStatement = connection.prepareStatement(sql);
           preparedStatement.setString(1,workHoursEntity.getStartWorkHour());
           preparedStatement.setString(2,workHoursEntity.getEndWorkHour());
           preparedStatement.execute();

        }catch (SQLException e){
            logger.error("SQLException in updateWorkHours block:"+e);
        }finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateWorkHours block(close preparedStatement):"+e);
            }
        }
    }

    public List<Position_Entity> getPositions(String string){
        String sql = "Select * from POSITION "+string;
        List<Position_Entity> position_entityList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Position_Entity position_entity =new Position_Entity();
                position_entity.setPosition(resultSet.getString("POSITION"));
                position_entity.setSalary(resultSet.getInt("salary"));
                position_entity.setId(resultSet.getInt("POSITION_ID"));
                position_entityList.add(position_entity);
            }

        }catch (SQLException e){
            logger.error("SQLException in getPositions block:"+e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getPositions block(close statement):"+e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getPositions block(close resultSet):"+e);

            }
        }

        return position_entityList;
    }

    public void createPosition(Object position,int salary ){
        String sql = "INSERT INTO POSITION VALUES(?,?,?)";
        PreparedStatement preparedStatement = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(POSITION_ID) FROM POSITION");
            int max = 0;
            if (resultSet.next()) {
                max = resultSet.getInt(1)+1;
            }
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setInt(1,max);
            preparedStatement.setString(2,(String) position);
            preparedStatement.setInt(3,salary);
            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("SQLException in createPosition block:"+e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createPosition block(close statement):"+e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createPosition block(close resultSet):"+e);

            }

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createPosition block(close preparedStatement):"+e);
            }
        }
    }


    public void deletePosition(int id){
        String sql ="UPDATE BUS_DRIVERS SET POSITION_ID=NULL WHERE POSITION_ID="+id+
                ";UPDATE ADMINISTRATION SET POSITION_ID=NULL WHERE POSITION_ID="+id+
                ";UPDATE DOCTORS SET POSITION_ID=NULL WHERE POSITION_ID="+id+
                ";UPDATE CAR_MECHANICS SET POSITION_ID=NULL WHERE POSITION_ID="+id+
                ";DELETE FROM POSITION WHERE POSITION_ID="+id;


        PreparedStatement preparedStatement = null;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("SQLException in deletePosition block:"+e);
        }finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deletePosition block(close preparedStatement):"+e);
            }
        }
    }

    public void updatePosition(Position_Entity positionEntity){
        String sql = "UPDATE POSITION SET POSITION=? , SALARY=?  " +
                "WHERE POSITION_ID="+positionEntity.getId();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,positionEntity.getPosition());
            preparedStatement.setInt(2,positionEntity.getSalary());
            preparedStatement.execute();

        }catch (SQLException e){
            logger.error("SQLException in updatePosition block:"+e);
        }finally {

            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updatePosition block(close preparedStatement):"+e);
            }
        }
    }






    public List<Department_Entity> getDepartments(){
        String sql="SELECT * FROM DEPARTMENT";
        List<Department_Entity> department_entityList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Department_Entity department_entity = new Department_Entity();
                department_entity.setDepartmentId(resultSet.getInt("id"));
                department_entity.setDepartment(resultSet.getString("department"));
                department_entityList.add(department_entity);
            }

        }catch (SQLException e){
            logger.error("SQLException in getDepartments block:"+e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getDepartments block(close statement):"+e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getDepartments block(close resultSet):"+e);
            }
        }


        return department_entityList;
    }




    public List<Buses_Entity> getWorkBus(){
        List<Buses_Entity> busesEntityList = new ArrayList<>();
        String sql="SELECT bus_id,BUS FROM BUS_PARK";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Buses_Entity busesEntity= new Buses_Entity();
                busesEntity.setBusNo(resultSet.getString("BUS"));
                busesEntity.setId(resultSet.getInt("bus_id"));
                busesEntityList.add(busesEntity);
            }

        }catch (SQLException e){
            logger.error("SQLException in getWorkBus block:"+e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getWorkBus block(close statement):"+e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getWorkBus block(close resultSet):"+e);
            }
        }

        return busesEntityList;
    }


    public List<Staff_Entity> getCarMechanics(){
        List<Staff_Entity> staffEntityList = new ArrayList<>();
        String sql = "SELECT S.ID,NAME,SURNAME,PATRONYMIC FROM CAR_MECHANICS cm INNER JOIN STAFF S ON (cm.ID = S.ID )";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Staff_Entity staffEntity = new Staff_Entity();
                staffEntity.setId(resultSet.getInt("id"));
                staffEntity.setName(resultSet.getString("NAME"));
                staffEntity.setSurname(resultSet.getString("SURNAME"));
                staffEntity.setPatronymic(resultSet.getString("PATRONYMIC"));

                staffEntityList.add(staffEntity);
            }


        }catch (SQLException e){
            logger.error("SQLException in getCarMechanics block:"+e);
        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getCarMechanics block(close statement):"+e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getCarMechanics block(close resultSet):"+e);
            }
        }
        return staffEntityList;
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
