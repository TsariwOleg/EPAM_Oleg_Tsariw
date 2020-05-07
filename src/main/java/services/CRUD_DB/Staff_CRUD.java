package services.CRUD_DB;

import services.ConnectionBD.ConnectionBD;
import services.DAO.DAO_CRUD;
import services.Entity.StaffEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Staff_CRUD extends ConnectionBD implements DAO_CRUD {
    Connection connection = getConnection();


    @Override
    public List<StaffEntity> getStaff() {
        /* TODO
         * rename sql
         * */
        String sql = "SELECT * FROM STAFF";
        List<StaffEntity> staffEntityList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                StaffEntity staffEntity = new StaffEntity(resultSet.getInt("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getString("SURNAME"),
                        resultSet.getString("PATRONYMIC"),
                        resultSet.getInt("AGE"),
                        resultSet.getInt("ID_DEPARTMENT")
                );
                staffEntityList.add(staffEntity);
            }

        } catch (SQLException e) {
            System.out.println("dsfdsfsdf");
        }
        return staffEntityList;
    }
}
