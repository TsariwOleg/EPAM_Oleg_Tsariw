package services.Entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

public class BlobToString {
    private String stringBlob;

    public String getStringFromBlob(Blob blob){
        try {
            InputStream inputStream = blob.getBinaryStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = outputStream.toByteArray();
            stringBlob = Base64.getEncoder().encodeToString(imageBytes);
        }catch (SQLException | IOException e) {
            System.out.println("staff entity");


        }


        return stringBlob;
    }
}
