package controller.role;

import controller.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;

@SuppressWarnings("serial")
public class RoleControllerView extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		Role role = pm.getObjectById(Role.class, Long.parseLong(request.getParameter("id")));
		request.setAttribute("role", role);
		}finally{
		pm.close();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Roles/viewRole.jsp");
		dispatcher.forward(request, response);													
	}
}