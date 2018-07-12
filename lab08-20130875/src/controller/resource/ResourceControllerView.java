package controller.resource;

import controller.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Resource;

@SuppressWarnings("serial")
public class ResourceControllerView extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try{
		Resource resource = pm.getObjectById(Resource.class, Long.parseLong(request.getParameter("id")));
		request.setAttribute("resource", resource);
		}finally{
		pm.close();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Resource/viewResource.jsp");
		dispatcher.forward(request, response);													
	}
}