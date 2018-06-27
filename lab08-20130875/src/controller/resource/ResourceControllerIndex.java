package controller.resource;

import controller.*;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Resource;
@SuppressWarnings("serial")
public class ResourceControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		System.out.println("Entro indexResource");
		try {
			System.out.println("Entro try resource");
			String query = "select from " + Resource.class.getName();
			List<Resource> resource = (List<Resource>) pm.newQuery(query).execute();
			request.setAttribute("resource", resource);
			
		} finally {
			pm.close();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Resource/indexResource.jsp");
		dispatcher.forward(request, response);
	}
}
