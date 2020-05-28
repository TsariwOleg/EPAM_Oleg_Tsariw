

//import services.ConnectionBD.ConnectionBD;

import services.ConnectionBD.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.util.Base64;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)	// upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet {
	/*private ConnectionBD connectionBD = new ConnectionBD();
	Connection conn=connectionBD.getConnection();*/
	ConnectionPool pool = ConnectionPool.getInstance();
	Connection connection = pool.getConnection();

	public String getEncode(Blob student){
		String test="";
		try {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(student);
			oos.flush();
			byte [] data = bos.toByteArray();
			test = Base64.getEncoder().encodeToString(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return test;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String string = "select * from image where id=3";
		String test ="";

	/*	try {
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(string);

			if (resultSet.next()){
				Blob blob = resultSet.getBlob("img");

				InputStream inputStream = blob.getBinaryStream();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int bytesRead = -1;

				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
				byte[] imageBytes = outputStream.toByteArray();
				test = Base64.getEncoder().encodeToString(imageBytes);


				System.out.println(test);

			}


		}catch (SQLException | IOException e){
			System.out.println(e);
		}*/
		req.setAttribute("w",test);
//		req.getRequestDispatcher("/Person.jsp").forward(req,resp);
		req.getRequestDispatcher("/Upload.jsp").forward(req,resp);
	}


	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) throws ServletException, IOException {

		InputStream inputStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if (filePart != null) {
			// prints out some information for debugging
			System.out.println(filePart.getName());
			System.out.println(filePart.getSize());
			System.out.println(filePart.getContentType());

			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
			if (inputStream!=null){
				System.out.println("not null");
			}else {
				System.out.println("null");
			}
		}

			// sets the message in request scope

			
			// forwards to the message page
			doGet(request, response);

	}
}




/*

        try {

            Part part = null;
            if (req.getParameter("staff-button") != null) {
                part=req.getPart("staff-img");
                System.out.println("rere");
                if (part != null) {
                    person_crud.updatePersonImg(id, part, "staff");
                    System.out.println("ee boi");
                }else {
                    System.out.println("null");
                }
            }
            if (req.getParameter("passport-button") != null) {
                part = req.getPart("passport-img");
                if (part != null) {
                    person_crud.updatePersonImg(id, part, "passport");
                }
            }

            if (req.getParameter("taxpayerCard-button") != null) {
                part = req.getPart("taxpayerCard-img");
                if (part != null) {
                    person_crud.updatePersonImg(id, part, "taxpayerCard");
                }
            }

        } catch (ServletException | IOException e) {
            System.out.println(e);
        }
 */