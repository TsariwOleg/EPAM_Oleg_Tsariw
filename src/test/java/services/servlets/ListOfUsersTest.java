package services.servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class ListOfUsersTest {
    ListOfUsers listOfUsers = new ListOfUsers();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/ListOfUsers.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        listOfUsers.doGet(request,response);
        verify(request,times(1)).setAttribute(anyString(),any());
        verify(request,times(1)).getRequestDispatcher("/ListOfUsers.jsp");
    }

    @Test
    void doPost() throws ServletException, IOException {
        when(request.getRequestDispatcher("/ListOfUsers.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        listOfUsers.doPost(request,response);
        verify(request,times(1)).getRequestDispatcher("/ListOfUsers.jsp");
    }
}