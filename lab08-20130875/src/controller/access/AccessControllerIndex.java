package controller.access;

import controller.*;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Access;
@SuppressWarnings("serial")
public class AccessControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + Access.class.getName();
			List<Access> access = (List<Access>) pm.newQuery(query).execute();
			request.setAttribute("access", access);
		} finally {
			pm.close();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/indexAccess.jsp");
		dispatcher.forward(request, response);
	}
}
