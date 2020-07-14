package services.servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

class CheckUpTest {
    CheckUp checkUp = new CheckUp();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/CheckUp.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        checkUp.doGet(request,response);
        verify(request,times(1)).getRequestDispatcher("/CheckUp.jsp");
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(request.getRequestDispatcher("/CheckUp.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        checkUp.doPost(request,response);
        verify(request,times(1)).getRequestDispatcher("/CheckUp.jsp");
    }
}