package controller.access;

import controller.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Access;

@SuppressWarnings("serial")
public class AccessControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PersistenceManager pm =  PMF.get().getPersistenceManager();
		String id=request.getParameter("id");
		Access a = pm.getObjectById(Access.class, Long.parseLong(id));
		try{
			pm.deletePersistent (a);
		} finally{
			pm.close();
		}
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/access");
		dispatcher.forward(request, response);
	}
}
