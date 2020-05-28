/*
package services.ConnectionBD;

import java.sql.*;

public class ConnectionBD {
    private String URL ="jdbc:h2:~/Bus_Company";
    private String Password ="";
    private String Driver ="org.h2.Driver";
    private String Login ="sa";

    public Connection getConnection(){
        System.out.println("saf");
        Connection connection = null;

        try{
            Class.forName(Driver);
            connection= DriverManager.getConnection(URL,Login,Password);

        }catch (SQLException | ClassNotFoundException e) {
            */
/* TODO
            * do log
            * *//*

            e.printStackTrace();
            System.out.println("problem");
        }

       return connection;
    }




    public void closeSPrepareStatement(PreparedStatement preparedStatement, String className){
        try {
            preparedStatement.close();
        }catch (SQLException e){
            System.out.println(className);
            System.out.println("Exeption con. slose");
            System.out.println(e);
        }
    }

    public void closeStatement(Statement statement, String className){
        try {
            statement.close();
        }catch (SQLException e){
            System.out.println(className);
            System.out.println("Exeption con. slose");
            System.out.println(e);
        }
    }

    public void closeResultSet(ResultSet resultSet, String className){
        try {
            resultSet.close();
        }catch (SQLException e){
            System.out.println(className);
            System.out.println("Exeption con. slose");
            System.out.println(e);
        }
    }

}
*/
