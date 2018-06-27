package controller.user;

import controller.*;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Users;
@SuppressWarnings("serial")
public class UserControllerIndex extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			String query = "select from " + Users.class.getName();
			List<Users> users = (List<Users>) pm.newQuery(query).execute();
			
			request.setAttribute("users", users);
		} finally {
			pm.close();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/User/indexUsuario.jsp");
		dispatcher.forward(request, response);
	}
}
