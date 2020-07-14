package services.CRUD_DB;

import org.junit.jupiter.api.Test;
import services.Entity.SignIn_Entity;
import services.Entity.Staff_Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SignInCRUDTest {
    SignInCRUD signInCRUD = new SignInCRUD();

    Connection connection = mock(Connection.class);
    Statement statement = mock(Statement.class);
    ResultSet resultSet = mock(ResultSet.class);
    PreparedStatement preparedStatement = mock(PreparedStatement.class);
    int id = 1;


    @Test
    void getOneSignIn() throws SQLException {
        String sql = "SELECT * FROM ACCESS_TO_WEB WHERE ID="+id;
        String login = "admin";
        String password="1111";

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("LOGIN")).thenReturn(login);
        when(resultSet.getString("PASsWORD")).thenReturn(password);

        when(statement.executeQuery(sql)).thenReturn(resultSet);
        when(connection.createStatement()).thenReturn(statement);

        signInCRUD.setConnection(connection);

        SignIn_Entity signInEntity = signInCRUD.getOneSignIn(id);
        assertEquals(signInEntity.getLogin(),login);
        assertEquals(signInEntity.getPassword(),password);
    }

    @Test
    void createUsersOfSite() throws SQLException {
        String sql = "INSERT INTO ACCESS_TO_WEB VALUES(?,?,?) ";

        SignIn_Entity signInEntity =new SignIn_Entity();
        signInEntity.setPassword("1111");
        signInEntity.setLogin("admin");
        signInEntity.setNSP("Петро Мельник Олексійович");

        List<Staff_Entity> staffEntityList = new ArrayList<>();
        Staff_Entity staffEntity = new Staff_Entity();
        staffEntity.setName("Петро");
        staffEntity.setSurname("Мельник");
        staffEntity.setPatronymic("Олексійович");
        staffEntityList.add(staffEntity);

        staffEntity=new Staff_Entity();
        staffEntity.setName("Олексій");
        staffEntity.setSurname("Мельник");
        staffEntity.setPatronymic("Петрович");
        staffEntityList.add(staffEntity);

        when(preparedStatement.execute()).thenReturn(true);
        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);
        signInCRUD.setConnection(connection);

        try {
            signInCRUD.createUsersOfSite(staffEntityList , signInEntity);
        }catch (Throwable e){
            fail(e.getMessage());
        }

    }

    @Test
    void deleteUserOfSite() throws SQLException {
        String sql = "DELETE ACCESS_TO_WEB WHERE ID=" + id;

        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);

        signInCRUD.setConnection(connection);

        try {
            signInCRUD.deleteUserOfSite(id);
        } catch (Throwable e) {
            fail(e.getMessage());
        }
    }

    @Test
    void updateSignId() throws SQLException {
        SignIn_Entity signInEntity =new SignIn_Entity();
        signInEntity.setId(id);
        signInEntity.setPassword("1111");
        signInEntity.setLogin("admin");

        String sql = "UPDATE ACCESS_TO_WEB SET LOGIN=? , PASSWORD=? WHERE ID="+id;


        when(preparedStatement.execute()).thenReturn(true);

        when(connection.prepareStatement(sql)).thenReturn(preparedStatement);


        signInCRUD.setConnection(connection);
        try {
            signInCRUD.updateSignId(id,signInEntity);
        }catch (Throwable e){
            fail(e.getMessage());
        }
    }


    @Test
    void updateSignIdWithNull(){
        assertThrows(NullPointerException.class ,()->signInCRUD.updateSignId(id,null) );
    }
}