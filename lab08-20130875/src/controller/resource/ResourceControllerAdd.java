package controller.resource;

import java.io.IOException;
import controller.*;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Resource;

@SuppressWarnings("serial")
public class ResourceControllerAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nameResource=request.getParameter("nameResource");
		
		if(nameResource!=null){
			System.out.println("Entro if resource");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Resource p = new Resource(nameResource, true);
			try {
				pm.makePersistent(p);
			} finally {
				pm.close();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/resource");
			dispatcher.forward(request, response);

		}else{
			System.out.println("Entro else resource");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Resource/addResource.jsp");
			dispatcher.forward(request, response);
		} 

	}
}
