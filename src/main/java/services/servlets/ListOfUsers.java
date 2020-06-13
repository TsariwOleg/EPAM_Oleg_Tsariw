package services.servlets;

import services.BlobToString;
import services.CRUD_DB.ConstantTablesCRUD;
import services.CRUD_DB.SignInCRUD;
import services.Entity.SignIn_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listofusers")
public class ListOfUsers extends HttpServlet {
    SignInCRUD signInCRUD = new SignInCRUD();
    ConstantTablesCRUD constantTablesCRUD = new ConstantTablesCRUD();
    BlobToString blobToString = new BlobToString();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SignIn_Entity> signInEntityList = signInCRUD.getUsersOfSite("");
        if (req.getSession().getAttribute("departmentId") != null) {
            req.setAttribute("access", req.getSession().getAttribute("departmentId"));
        }

        if (req.getParameter("regime") != null) {


            if (req.getParameter("regime").equals("DeleteUser")) {
                signInEntityList = signInCRUD.getUsersOfSite(" WHERE ATW.id<>0");
                req.setAttribute("changeUser", req.getParameter("id"));
                System.out.println(req.getParameter("id"));
            }

            if (req.getParameter("regime").equals("EditUser") && req.getParameter("id") != null) {
                req.setAttribute("changeCheckUpId", req.getParameter("id"));
                req.setAttribute("changedSignIn",
                        signInCRUD.getOneSignIn(Integer.parseInt(req.getParameter("id"))));

            }
            if (req.getParameter("regime").equals("AddUser")) {
                req.setAttribute("staff", constantTablesCRUD.readStaffForSignUp(signInEntityList));
            }

            req.setAttribute("regime", req.getParameter("regime"));

        } else {

        }

        req.setAttribute("listOfUsers", signInEntityList);
        req.getRequestDispatcher("/ListOfUsers.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("confirmUser") != null) {
            SignIn_Entity signInEntity = new SignIn_Entity();
            signInEntity.setNSP(blobToString.getUTFString(req.getParameter("newPerson")));
            signInEntity.setLogin(blobToString.getUTFString(req.getParameter("newLogin")));
            signInEntity.setPassword(blobToString.getUTFString(req.getParameter("newPassword")));
            signInCRUD.createUsersOfSite(
                    constantTablesCRUD.readStaff("WHERE (DEPARTMENT_ID=1 or DEPARTMENT_ID=4)"), signInEntity);
        }

        if (req.getParameter("deleteUser") != null) {
            signInCRUD.deleteUserOfSite(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/listofusers");
            return;
        }

        if (req.getParameter("cancelDeletingUser") != null) {
            resp.sendRedirect("/listofusers");
            return;
        }

        if (req.getParameter("confirmEdittingUser") != null) {
            SignIn_Entity signInEntity = new SignIn_Entity();
            signInEntity.setLogin(blobToString.getUTFString(req.getParameter("newLogin")));
            signInEntity.setPassword(blobToString.getUTFString(req.getParameter("newPassword")));
            signInCRUD.updateSignId(Integer.parseInt(req.getParameter("id")), signInEntity);
            resp.sendRedirect("/listofusers");
            return;
        }


        doGet(req, resp);
    }
}
