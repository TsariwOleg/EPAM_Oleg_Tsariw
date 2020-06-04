package services.servlets;

import services.BlobToString;
import services.Entity.SignIn_Entity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet ("/login")
public class Login extends HttpServlet {
    HttpSession session;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("departmentId")!=null){
        if (req.getParameter("regime")!=null){
            req.setAttribute("regime",req.getParameter("regime"));
        }}

        req.getRequestDispatcher("/Login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlobToString blobToString = new BlobToString();
        if (req.getParameter("signIn")!=null){
            SignIn_Entity signInEntity = new SignIn_Entity();
            signInEntity.setLogin(blobToString.getUTFString(req.getParameter("login")));
            signInEntity.setPassword(blobToString.getUTFString( req.getParameter("password")));
            signInEntity = blobToString.ifExist(signInEntity);
            if (signInEntity.getNSP()!=null){
                session= req.getSession();
                session.setAttribute("departmentId",signInEntity.getDepartmentId());

            }else {
                System.out.println("null");
            }

        }

        if (req.getParameter("cancellogout")!=null){
        resp.sendRedirect("/login");
        return;
        }

        if (req.getParameter("logout")!=null){
            req.getSession().removeAttribute("departmentId");
        resp.sendRedirect("/login");
        return;
        }

        doGet(req,resp);
    }
}
