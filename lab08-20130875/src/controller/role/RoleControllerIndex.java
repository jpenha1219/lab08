package controller.role;

import controller.*;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;
@SuppressWarnings("serial")
public class RoleControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + Role.class.getName();
			List<Role> role = (List<Role>) pm.newQuery(query).execute();
			request.setAttribute("role", role);
		} finally {
			pm.close();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Roles/indexRole.jsp");
		dispatcher.forward(request, response);
	}
}
