package services.servlets;

import org.junit.jupiter.api.Test;
import services.CRUD_DB.BusCRUD;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

class BusTest {
    Bus bus = new Bus();
    BusCRUD busCRUD = mock(BusCRUD.class);


    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    RequestDispatcher dispatcher = mock(RequestDispatcher.class);
    HttpSession session = mock(HttpSession.class);



    @Test
    void doGet() throws ServletException, IOException {
        when(request.getRequestDispatcher("/Bus.jsp")).thenReturn(dispatcher);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("1");
        bus.doGet(request,response);
        verify(request,times(1)).getRequestDispatcher("/Bus.jsp");
        verify(request,times(2)).setAttribute(anyString(),any());
    }




    @Test
    void doPostNullParameter() throws ServletException, IOException {

        when(request.getSession()).thenReturn(session);
        when(request.getParameter("id")).thenReturn("1");


        when(request.getRequestDispatcher("/Bus.jsp")).thenReturn(dispatcher);


        bus.doPost(request,response);

        verify(request,times(1)).getRequestDispatcher("/Bus.jsp");
        verify(request,times(2)).setAttribute(anyString(),any());

    }




}