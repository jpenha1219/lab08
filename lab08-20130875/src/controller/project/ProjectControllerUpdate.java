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
public class ProjectControllerUpdate extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		String nombre = request.getParameter("name");
		String area = request.getParameter("area");
		
		Boolean estado = Boolean.valueOf(request.getParameter("state"));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if (nombre != null) {

			try {
				Project project = pm.getObjectById(Project.class, id);
				project.setName(nombre);
				project.setName(area);
				project.setState(estado);

			} finally {
				pm.close();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/project");
			dispatcher.forward(request, response);
		} else {
			try {
				Project project = pm.getObjectById(Project.class, id);
				request.setAttribute("proyect", project);
			} finally {
				pm.close();
			}
			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/Views/Project/updateProject.jsp");
			dispatcher.forward(request, response);
		}
	}
}