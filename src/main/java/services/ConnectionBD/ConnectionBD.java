package services.ConnectionBD;

import org.apache.log4j.Logger;

import java.sql.*;

public class ConnectionBD {
    private String URL ="jdbc:h2:~/Bus_Company";
    private String Password ="";
    private String Driver ="org.h2.Driver";
    private String Login ="sa";
    private final Logger logger = Logger.getRootLogger();

    public Connection getConnection(){
        Connection connection = null;

        try{
            Class.forName(Driver);
            connection= DriverManager.getConnection(URL,Login,Password);

        }catch (SQLException e) {
            logger.error("Doesnt create connection.SQLException"+e);
        }catch (ClassNotFoundException e){
            logger.error("Doesnt find class.ClassNotFoundException"+e);
        }

       return connection;
    }

}

