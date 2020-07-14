package services.servlets;

import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

class BusParkTest {
    BusPark busPark =new BusPark();


    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    HttpSession session = mock(HttpSession.class);

    @Test
    void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/BusPark.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        busPark.doGet(request,response);
        verify(request,times(1)).getRequestDispatcher("/BusPark.jsp");
        verify(request,times(1)).setAttribute(anyString(),anyList());
    }

    @Test
    void doPostNullParameter() throws ServletException, IOException {
        when(request.getRequestDispatcher("/BusPark.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        busPark.doPost(request,response);
        verify(request,times(1)).getRequestDispatcher("/BusPark.jsp");
        verify(request,times(1)).setAttribute(anyString(),anyList());
    }

    @Test
    void doPostCancelDeletingBus() throws ServletException, IOException {
        when(request.getRequestDispatcher("/BusPark.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);

        when(request.getParameter("cancelDeletingBus")).thenReturn("cancel");

        busPark.doPost(request,response);
        verify(response,times(1)).sendRedirect("/buspark");
    }





}