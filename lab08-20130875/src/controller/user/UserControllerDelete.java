package controller.user;

import controller.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Project;
import model.Users;

@SuppressWarnings("serial")
public class UserControllerDelete extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PersistenceManager pm =  PMF.get().getPersistenceManager();
		String id=request.getParameter("id");
		Users a = pm.getObjectById(Users.class, Long.parseLong(id));
		try{
			pm.deletePersistent (a);
		} finally{
			pm.close();
		}
		RequestDispatcher dispatcher =  getServletContext().getRequestDispatcher("/user");
		dispatcher.forward(request, response);
	}
}
