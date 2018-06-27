package controller.role;

import java.io.IOException;
import controller.*;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;

@SuppressWarnings("serial")
public class RoleControllerAdd extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String namerole=request.getParameter("nameRole");
		if(namerole!=null){
			System.out.println("Entro if add");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			Role role = new Role(namerole, true);
			try {
				pm.makePersistent(role);
			} finally {
				pm.close();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/role");
			dispatcher.forward(request, response);
		}else{
			System.out.println("Entro else add");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Roles/addRole.jsp");
			dispatcher.forward(request, response);
		} 
	}
}
