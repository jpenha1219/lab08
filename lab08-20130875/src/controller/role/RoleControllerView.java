package controller.role;

import controller.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;

@SuppressWarnings("serial")
public class RoleControllerView extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		Project project = pm.getObjectById(Project.class, Long.parseLong(request.getParameter("id")));
		request.setAttribute("project", project);
		}finally{
		pm.close();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Project/view.jsp");
		dispatcher.forward(request, response);													
	}
}