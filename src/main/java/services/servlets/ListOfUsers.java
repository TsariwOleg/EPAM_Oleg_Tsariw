package services.servlets;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
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
    int idSession = -1;
    private final Logger logger = Logger.getRootLogger();

    @Override
    public void init() {
        BasicConfigurator.configure();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SignIn_Entity> signInEntityList = signInCRUD.getUsersOfSite("");
        if (req.getSession().getAttribute("departmentId") != null) {
            req.setAttribute("access", req.getSession().getAttribute("departmentId"));
            idSession = (Integer) req.getSession().getAttribute("departmentId");

        } else {
            idSession = -1;
        }


        if (req.getParameter("regime") != null) {


            if (req.getParameter("regime").equals("DeleteUser")) {
                signInEntityList = signInCRUD.getUsersOfSite(" WHERE ATW.id<>0");
                req.setAttribute("changeUser", req.getParameter("id"));
                logger.info("User want to delete person from list of users.User_id=" + idSession);
            }

            if (req.getParameter("regime").equals("EditUser")) {
                if (req.getParameter("id") != null) {
                    req.setAttribute("changeCheckUpId", req.getParameter("id"));
                    req.setAttribute("changedSignIn",
                            signInCRUD.getOneSignIn(Integer.parseInt(req.getParameter("id"))));
                    logger.info("User chose a person(person_id=" + req.getParameter("id") +
                            ") in order to edit him.User_id=" + idSession);
                } else {
                    logger.info("User want to edit user of site.User_id=" + idSession);
                }
            }
            if (req.getParameter("regime").equals("AddUser")) {
                req.setAttribute("staff", constantTablesCRUD.readStaffForSignUp(signInEntityList));
                logger.info("User want to add a new user of site.User_id=" + idSession);
            }

            req.setAttribute("regime", req.getParameter("regime"));

        } else {
            logger.info("User opened ListOfUser page.User_id=" + idSession);
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

            logger.info("User added a new user of site: " +
                    " \n  NSP:" + signInEntity.getNSP() +
                    "\n  Login:" + signInEntity.getLogin() +
                    "\n  Model:" + signInEntity.getPassword() +
                    ".User_id=" + idSession);
        }

        if (req.getParameter("deleteUser") != null) {
            logger.info("User deleted user of site(user_id=" + req.getParameter("id")
                    + "). User_id=" + idSession);

            signInCRUD.deleteUserOfSite(Integer.parseInt(req.getParameter("id")));
            resp.sendRedirect("/listofusers");
            return;
        }

        if (req.getParameter("cancelDeletingUser") != null) {
            logger.info("User canceled deleting/editing user. User_id=" + idSession);

            resp.sendRedirect("/listofusers");
            return;
        }

        if (req.getParameter("confirmEdittingUser") != null) {
            SignIn_Entity signInEntity = new SignIn_Entity();
            signInEntity.setLogin(blobToString.getUTFString(req.getParameter("newLogin")));
            signInEntity.setPassword(blobToString.getUTFString(req.getParameter("newPassword")));
            signInCRUD.updateSignId(Integer.parseInt(req.getParameter("id")), signInEntity);

            logger.info("User edited  user of site: " +
                    " \n  new NSP:" + signInEntity.getNSP() +
                    "\n  new Login:" + signInEntity.getLogin() +
                    "\n new Model:" + signInEntity.getPassword() +
                    ".User_id=" + idSession);
            resp.sendRedirect("/listofusers");
            return;
        }


        doGet(req, resp);
    }
}
