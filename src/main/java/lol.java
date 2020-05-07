import java.sql.*;

public class lol {
    private String URL ="jdbc:h2:~/Bus_Company";
    private String Password ="";
    private String Driver ="org.h2.Driver";
    private String Login ="sa";

public void connect() throws SQLException {
    Connection connection = null;

    try{
        Class.forName(Driver);
        connection= DriverManager.getConnection(URL,Login,Password);
        System.out.println("ok");
    }catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
        System.out.println("((((");
    }

    Statement statement = null;
    ResultSet resultSet = null;
    try{
        statement=connection.createStatement();
        resultSet = statement.executeQuery("select * from STAFF");
        while (resultSet.next()){
            System.out.println(resultSet.getString("NAME"));
            System.out.println(resultSet.getString("SURNAME"));


        }

    } catch (SQLException e) {
        e.printStackTrace();
    }finally {
        resultSet.close();
        statement.close();

    }

}

}
