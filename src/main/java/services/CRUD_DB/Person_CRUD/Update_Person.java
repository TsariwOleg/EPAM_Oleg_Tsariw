package services.CRUD_DB.Person_CRUD;

import services.ConnectionBD.ConnectionBD;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update_Person extends ConnectionBD {
    Connection connection = getConnection();

    public void updateStaffImg(int id, InputStream inputStream) {
        String sql = "update staff set photo= ? where id=" + id;
        PreparedStatement preparedStatement ;
        try {
          preparedStatement=connection.prepareStatement(sql);
          preparedStatement.setBlob(1,inputStream);
          preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }



    public void updatePassportImg(int id, InputStream inputStream) {
        String sql = "update passport set Scanned_passport= ? where id=" + id;
        PreparedStatement preparedStatement ;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setBlob(1,inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void updateTaxPayerCardImg(int id, InputStream inputStream) {
        String sql = "update taxpayer_card set scanned_taxpayer_card= ? where id=" + id;
        PreparedStatement preparedStatement ;
        try {
            preparedStatement=connection.prepareStatement(sql);
            preparedStatement.setBlob(1,inputStream);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
