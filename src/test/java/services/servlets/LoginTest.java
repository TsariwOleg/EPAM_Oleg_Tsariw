package services.servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

class LoginTest {
    Login login = new Login();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Login.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        login.doGet(request,response);
        verify(request,times(1)).getRequestDispatcher("/Login.jsp");
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Login.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        login.doPost(request,response);
        verify(request,times(1)).getRequestDispatcher("/Login.jsp");
    }
}