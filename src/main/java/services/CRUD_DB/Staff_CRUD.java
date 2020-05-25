package services.CRUD_DB;

import services.ConnectionBD.ConnectionBD;
import services.DAO.DAO_CRUD;
import services.Entity.Department_Entity;
import services.Entity.Staff_Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Staff_CRUD extends ConnectionBD implements DAO_CRUD {
    Connection connection = getConnection();
    private String className = this.getClass().getSimpleName();

    @Override
    public List<Staff_Entity> getStaff() {
        /* TODO
         * rename sql
         * */
        String sql = "SELECT * FROM STAFF s INNER JOIN DEPARTMENT d ON s.DEPARTMENT_ID = d.ID";
        List<Staff_Entity> staffEntityList = new ArrayList<>();
        Statement statement =null;
        ResultSet resultSet =null;
        try {
            statement=connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Staff_Entity staffEntity = new Staff_Entity(
                        resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("PATRONYMIC"),
                        resultSet.getInt("AGE")
                );
                staffEntity.setId(resultSet.getInt("id"));
                staffEntity.setDepartment(resultSet.getString("DEPARTMENT"));
                staffEntityList.add(staffEntity);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            if (resultSet!=null){
               closeResultSet(resultSet,className);
            }
            if (statement!=null){
                closeStatement(statement,className);
            }

        }

        return staffEntityList;
    }



}
