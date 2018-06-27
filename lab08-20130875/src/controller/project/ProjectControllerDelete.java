package controller.project;

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
public class ProjectControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PersistenceManager pm =  PMF.get().getPersistenceManager();
		String id=request.getParameter("id");
		Project a = pm.getObjectById(Project.class, Long.parseLong(id));
		try{
			pm.deletePersistent (a);
		} finally{
			pm.close();
		}
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/proyect");
		dispatcher.forward(request, response);
	}
}
