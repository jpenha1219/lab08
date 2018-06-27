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
public class ResourceControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PersistenceManager pm =  PMF.get().getPersistenceManager();
		String id=request.getParameter("id");
		Resource a = pm.getObjectById(Resource.class, Long.parseLong(id));
		try{
			pm.deletePersistent (a);
		} finally{
			pm.close();
		}
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/resource");
		dispatcher.forward(request, response);
	}
}
