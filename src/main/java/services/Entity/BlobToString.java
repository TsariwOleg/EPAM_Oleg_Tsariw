package services.Entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class BlobToString {
    private String stringBlob;

    public String getStringFromBlob(Blob blob){
        InputStream inputStream=null;
        ByteArrayOutputStream outputStream=null;
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
        }catch (SQLException | IOException e) {
            System.out.println("staff entity");
        }finally {
            try{
            inputStream.close();
            outputStream.close();
        }catch (IOException e){
                System.out.println("Stream exception");
            }
        }
        return stringBlob;
    }


    public String getUTFString(String string){
        String UTFString=null;
        try {
            UTFString = new String(string.getBytes("ISO-8859-1"), "UTF-8" );

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return UTFString;
    }
}
