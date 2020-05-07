package services.ConnectionBD;

import java.sql.*;

public class ConnectionBD {
    private String URL ="jdbc:h2:~/Bus_Company";
    private String Password ="";
    private String Driver ="org.h2.Driver";
    private String Login ="sa";

    public Connection getConnection(){

        Connection connection = null;

        try{
            Class.forName(Driver);
            connection= DriverManager.getConnection(URL,Login,Password);
            System.out.println("ok");
        }catch (SQLException | ClassNotFoundException e) {
            /* TODO
            * do log
            * */
            e.printStackTrace();
            System.out.println("problem");
        }

       return connection;

    }

}
