package services.ConnectionBD;




import org.h2.jdbcx.JdbcConnectionPool;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPool {
    private String URL ="jdbc:h2:~/Bus_Company";
    private String Password ="";
    private String Driver ="org.h2.Driver";
    private String Login ="sa";


    private DataSource dataSource;

    public ConnectionPool() {
        try {
            dataSource = JdbcConnectionPool.create(
                    URL, Login, Password);
            System.out.println("--------------------");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch ( SQLException ex) {

            return null;
        }
    }

    private static class ConnectionPoolHolder {
        public static final ConnectionPool INSTANCE = new ConnectionPool();
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }
}
