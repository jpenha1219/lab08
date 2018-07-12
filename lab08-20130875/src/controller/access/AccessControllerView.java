package controller.access;

import controller.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Access;

@SuppressWarnings("serial")
public class AccessControllerView extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		Access access = pm.getObjectById(Access.class, Long.parseLong(request.getParameter("id")));
		request.setAttribute("access", access);
		}finally{
		pm.close();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/viewAccess.jsp");
		dispatcher.forward(request, response);													
	}
}