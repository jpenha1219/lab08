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
public class RoleControllerUpdate extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String nombre=request.getParameter("nuevoNombre");
		String resultado=request.getParameter("nuevoResultado");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(nombre!=null){
		
			try {
				Project proyect = pm.getObjectById(Project.class, Long.parseLong(id));
				proyect.setName(nombre);
				
			} finally {
				pm.close();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/project");
			dispatcher.forward(request, response);	
		}else{
			try{
				Project project = pm.getObjectById(Project.class, Long.parseLong(request.getParameter("id")));
				request.setAttribute("project", project);
				}finally{
				pm.close();
				}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Project/modificar.jsp");
			dispatcher.forward(request, response);	}
	}
}

