package services;

import org.apache.log4j.Logger;
import services.CRUD_DB.SignInCRUD;
import services.Entity.SignIn_Entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class BlobToString {
    private String stringBlob;
    private final Logger logger = Logger.getRootLogger();

    public String getStringFromBlob(Blob blob) {
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = null;
        try {
            inputStream = blob.getBinaryStream();
            outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            stringBlob = Base64.getEncoder().encodeToString(imageBytes);
        } catch (SQLException e) {
            logger.error("SQLException in converting blob to string "+e);
        } catch (IOException e){
            logger.error("IOException in converting blob to string "+e);

        }
        finally {
            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                logger.error("IOException.Stream close exception "+e);
            }
        }
        return stringBlob;
    }


    public String getUTFString(String string) {
        String UTFString = null;
        try {
            UTFString = new String(string.getBytes("ISO-8859-1"), "UTF-8");

        } catch (UnsupportedEncodingException e) {
            logger.error("UnsupportedEncodingException "+e);
        }

        return UTFString;
    }

    public SignIn_Entity ifExist(SignIn_Entity signInEntity) {
        List<SignIn_Entity> signInEntityList = new SignInCRUD().getUsersOfSite("");
        SignIn_Entity existingSignIn = new SignIn_Entity();
        for (SignIn_Entity sign : signInEntityList) {

            if (signInEntity.getLogin().equals(sign.getLogin()) &&
                    signInEntity.getPassword().equals(sign.getPassword())) {


                existingSignIn=sign;


                if (existingSignIn.getNSP()==null){
                    existingSignIn.setNSP("admin");
                    existingSignIn.setDepartment("admin");
                    existingSignIn.setDepartmentId(0);
                }
                break;
            }

        }

return existingSignIn;
    }
}
