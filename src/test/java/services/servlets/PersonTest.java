package services.servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

class PersonTest {
    Person person = new Person();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Person.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        when(request.getParameter("id")).thenReturn("1");

        person.doGet(request,response);
        verify(request,times(1)).getRequestDispatcher("/Person.jsp");
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Person.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        when(request.getParameter("id")).thenReturn("1");


        person.doPost(request,response);
        verify(request,times(1)).getRequestDispatcher("/Person.jsp");
    }
}