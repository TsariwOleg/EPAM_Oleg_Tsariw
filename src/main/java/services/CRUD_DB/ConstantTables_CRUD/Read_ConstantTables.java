package services.CRUD_DB.ConstantTables_CRUD;

import services.Entity.Buses_Entity;
import services.Entity.Department_Entity;
import services.Entity.Position_Entity;
import services.Entity.WorkHours_Entity;

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
                position_entityList.add(position_entity);
            }

        }catch (SQLException e){
            System.out.println(e);
        }


//todo return
        return position_entityList;
    }


    public List<Department_Entity> getDepartments(Statement statement){
        String sql="SELECT * FROM DEPARTMENT";
        List<Department_Entity> department_entityList = new ArrayList<>();

        try {
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Department_Entity department_entity = new Department_Entity();
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


}
