package services.CRUD_DB;

import services.ConnectionBD.ConnectionBD;
import services.Entity.SignIn_Entity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SignInCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();

    public List<SignIn_Entity> getSignIn() {
        String sql = "SELECT atw.ID ,ATW.LOGIN ,ATW.PASSWORD ,s1.NAME , s1.SURNAME , s1.PATRONYMIC  ,s1.DEPARTMENT_ID ," +
                "s2.NAME , s2.SURNAME , s2.PATRONYMIC , s2.DEPARTMENT_ID " +
                "FROM ACCESS_TO_WEB atw LEFT JOIN STAFF s1 ON (atw.id=s1.ID " +
                "AND s1.DEPARTMENT_ID =1) LEFT JOIN staff s2 ON (atw.id=s2.ID AND s2.DEPARTMENT_ID =4)";
        List<SignIn_Entity> signInEntityList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getInt(7) == 1
                        || resultSet.getInt(11) == 4
                        || resultSet.getInt(1) == 0) {

                    SignIn_Entity signInEntity = new SignIn_Entity();
                    signInEntity.setId(resultSet.getInt("ID"));
                    signInEntity.setLogin(resultSet.getString("LOGIN"));
                    signInEntity.setPassword(resultSet.getString("PASSWORD"));


                    signInEntity.setDepartmentId(resultSet.getInt("DEPARTMENT_ID"));
                    if (resultSet.getInt(11) == 4){
                        String NSP = resultSet.getString(8) +
                                " " + resultSet.getString(9) +
                                " " + resultSet.getString(10);
                        signInEntity.setNSP(NSP);
                        signInEntity.setDepartmentId(resultSet.getInt(11));
                    }

                    if (resultSet.getInt(7) == 1){
                        String NSP = resultSet.getString(4) +
                                " " + resultSet.getString(5) +
                                " " + resultSet.getString(6);
                        signInEntity.setNSP(NSP);
                        signInEntity.setDepartmentId(resultSet.getInt(7));
                    }

                    if (resultSet.getInt("ID") == 0) {
                        signInEntity.setDepartmentId(0);
                    }
                    signInEntityList.add(signInEntity);
                }

            }

        } catch (SQLException e) {
            System.out.println(e);
        }

        return signInEntityList;
    }

}
