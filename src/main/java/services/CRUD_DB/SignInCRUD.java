package services.CRUD_DB;

import org.apache.log4j.Logger;
import services.ConnectionBD.ConnectionBD;
import services.Entity.SignIn_Entity;
import services.Entity.Staff_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SignInCRUD {
    ConnectionBD connectionBD = new ConnectionBD();
    Connection connection = connectionBD.getConnection();
    private final Logger logger = Logger.getRootLogger();

    public List<SignIn_Entity> getUsersOfSite(String string) {
        String sql = "SELECT atw.ID ,ATW.LOGIN ,ATW.PASSWORD ,s1.NAME , s1.SURNAME , s1.PATRONYMIC  ,s1.DEPARTMENT_ID ," +
                "s2.NAME , s2.SURNAME , s2.PATRONYMIC , s2.DEPARTMENT_ID " +
                "FROM ACCESS_TO_WEB atw LEFT JOIN STAFF s1 ON (atw.id=s1.ID " +
                "AND s1.DEPARTMENT_ID =1) LEFT JOIN staff s2 ON (atw.id=s2.ID AND s2.DEPARTMENT_ID =4)"+string;
      
        List<SignIn_Entity> signInEntityList = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        try {
             statement = connection.createStatement();
             resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (resultSet.getInt(7) == 1
                        || resultSet.getInt(11) == 4
                        || resultSet.getInt(1) == 0) {

                    SignIn_Entity signInEntity = new SignIn_Entity();
                    signInEntity.setId(resultSet.getInt("ID"));
                    signInEntity.setLogin(resultSet.getString("LOGIN"));
                    signInEntity.setPassword(resultSet.getString("PASSWORD"));



                    signInEntity.setId(resultSet.getInt("DEPARTMENT_ID"));
                    if (resultSet.getInt(11) == 4){
                        String NSP = resultSet.getString(8) +
                                " " + resultSet.getString(9) +
                                " " + resultSet.getString(10);
                        signInEntity.setNSP(NSP);
                        signInEntity.setId(resultSet.getInt(11));
                        signInEntity.setDepartment("Медики");
                    }

                    if (resultSet.getInt(7) == 1){
                        String NSP = resultSet.getString(4) +
                                " " + resultSet.getString(5) +
                                " " + resultSet.getString(6);
                        signInEntity.setNSP(NSP);
                        signInEntity.setId(resultSet.getInt(7));
                        signInEntity.setDepartment("Адміністрація");

                    }

                    if (resultSet.getInt("ID") == 0) {
                        signInEntity.setDepartmentId(0);
                    }
                    signInEntityList.add(signInEntity);
                }

            }

        } catch (SQLException e) {
            logger.error("SQLException in getUsersOfSite block:" + e);

        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getUsersOfSite block(close statement):" + e);
            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getUsersOfSite block(close resultSet):" + e);
            }
        }

        return signInEntityList;
    }

    public SignIn_Entity getOneSignIn(int id){
        String sql = "SELECT * FROM ACCESS_TO_WEB WHERE ID="+id;
        SignIn_Entity signInEntity = new SignIn_Entity();
        Statement statement = null  ;
        ResultSet resultSet = null;
        try {
             statement = connection.createStatement();
             resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                signInEntity.setLogin(resultSet.getString("LOGIN"));
                signInEntity.setPassword(resultSet.getString("PASsWORD"));
            }


        }catch (SQLException e){
            logger.error("SQLException in getOneSignIn block:" + e);

        }finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getOneSignIn block(close statement):" + e);

            }

            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in getOneSignIn block(close resultSet):" + e);

            }
        }

        return signInEntity;
    }

    public void createUsersOfSite(List<Staff_Entity> staffEntityList , SignIn_Entity newUser){
        String sql = "INSERT INTO ACCESS_TO_WEB VALUES(?,?,?) ";
        int id=0;
        for (Staff_Entity staff:staffEntityList) {
            String NSP = staff.getName()+" "+staff.getSurname()+" "+staff.getPatronymic();
            if (NSP.equals(newUser.getNSP())){
                id=staff.getId();
            }
        }

        PreparedStatement preparedStatement = null;
        try {
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,newUser.getLogin());
            preparedStatement.setString(3,newUser.getPassword());
            preparedStatement.execute();
        }catch (SQLException e){
            logger.error("SQLException in createUsersOfSite block:" + e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in createUsersOfSite block(close preparedStatement):" + e);
            }
        }
    }

    public void deleteUserOfSite(int id){
        String sql ="DELETE ACCESS_TO_WEB WHERE ID="+id;
        PreparedStatement preparedStatement = null;
        try{
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

        } catch (SQLException e){
            logger.error("SQLException in deleteUserOfSite block:" + e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in deleteUserOfSite block(close preparedStatement):" + e);
            }
        }
    }

    public void updateSignId(int id , SignIn_Entity newSignIn){
        String sql = "UPDATE ACCESS_TO_WEB SET LOGIN=? , PASSWORD=? WHERE ID="+id;
        PreparedStatement preparedStatement = null;
        try{
             preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newSignIn.getLogin());
            preparedStatement.setString(2,newSignIn.getPassword());
            preparedStatement.execute();
        } catch (SQLException e){
            logger.error("SQLException in updateSignId block:" + e);
        }finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                logger.error("SQLException in updateSignId block(close preparedStatement):" + e);
            }
        }
    }


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
