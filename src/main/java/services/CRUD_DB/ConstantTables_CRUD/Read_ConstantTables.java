package services.CRUD_DB.ConstantTables_CRUD;

import services.Entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Read_ConstantTables {


    public List<Position_Entity> getPositions(Statement statement){
        String sql = "Select * from POSITION";
        List<Position_Entity> position_entityList = new ArrayList<>();

        try{
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Position_Entity position_entity =new Position_Entity();
                position_entity.setPosition(resultSet.getString("POSITION"));
                position_entity.setSalary(resultSet.getInt("salary"));
                position_entity.setId(resultSet.getInt("POSITION_ID"));
                position_entityList.add(position_entity);
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return position_entityList;
    }


    public List<Department_Entity> getDepartments(Statement statement){
        String sql="SELECT * FROM DEPARTMENT";
        List<Department_Entity> department_entityList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Department_Entity department_entity = new Department_Entity();
                department_entity.setId(resultSet.getInt("id"));
                department_entity.setDepartment(resultSet.getString("department"));
                department_entityList.add(department_entity);
            }

        }catch (SQLException e){
            System.out.println(e);
        }


        return department_entityList;
    }


    public List<WorkHours_Entity> getWorkHours(Statement statement){
        String sql = "SELECT * FROM WORK_HOURS";
        List<WorkHours_Entity> workHoursEntityList = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                WorkHours_Entity workHoursEntity = new WorkHours_Entity();
                workHoursEntity.setId(resultSet.getInt("WORK_HOUR_ID"));
                workHoursEntity.setStartWorkHour(resultSet.getString("START_WORK_HOURS"));
                workHoursEntity.setEndWorkHour(resultSet.getString("END_WORK_HOURS"));
                workHoursEntityList.add(workHoursEntity);
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return workHoursEntityList;
    }


    public List<Buses_Entity> getWorkBus(Statement statement){
        List<Buses_Entity> busesEntityList = new ArrayList<>();
        String sql="SELECT bus_id,BUS FROM BUS_PARK";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Buses_Entity busesEntity= new Buses_Entity();
                busesEntity.setBusNo(resultSet.getString("BUS"));
                busesEntity.setId(resultSet.getInt("bus_id"));
                busesEntityList.add(busesEntity);
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return busesEntityList;
    }

    public List<Route_Entity> getRoute(Statement statement){
        String sql = "SELECT ID,ROUTE FROM ROUTE";
        List<Route_Entity> routeEntityList = new ArrayList<>();
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Route_Entity routeEntity = new Route_Entity();
                routeEntity.setId(resultSet.getInt("id"));
                routeEntity.setRoute(resultSet.getString("ROUTE"));

                routeEntityList.add(routeEntity);
            }

        }catch (SQLException e){
            System.out.println(e);
        }

        return routeEntityList;
    }

    public List<CarMechanics_Entity> getCarMechanics(Statement statement){
        List<CarMechanics_Entity> carMechanicsEntityList = new ArrayList<>();
        String sql = "SELECT S.ID,NAME,SURNAME,PATRONYMIC FROM CAR_MECHANICS cm INNER JOIN STAFF S ON (cm.ID = S.ID )";
        try{
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                CarMechanics_Entity carMechanicsEntity = new CarMechanics_Entity();
                carMechanicsEntity.setId(resultSet.getInt("id"));
                String nsp="";
                nsp+=resultSet.getString("NAME")+" ";
                nsp+=resultSet.getString("SURNAME")+" ";
                nsp+=resultSet.getString("PATRONYMIC");
                carMechanicsEntity.setNSP(nsp);
                carMechanicsEntityList.add(carMechanicsEntity);
            }


        }catch (SQLException e){
            System.out.println(e);
        }
        return carMechanicsEntityList;
    }


}
