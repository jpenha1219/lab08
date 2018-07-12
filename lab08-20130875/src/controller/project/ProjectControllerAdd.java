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
public class ProjectControllerAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name=request.getParameter("name");
		String area=request.getParameter("area");
		Boolean state=Boolean.valueOf(request.getParameter("state"));
		if(name!=null){
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Project p = new Project(name,Double.parseDouble(area),state);
			try {
				pm.makePersistent(p);
			} finally {
				pm.close();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/project");
			dispatcher.forward(request, response);

		}else{
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Project/addProject.jsp");
			dispatcher.forward(request, response);
		} 

	}
}
