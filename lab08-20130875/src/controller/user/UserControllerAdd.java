package controller.user;

import java.io.IOException;
import java.util.List;

import controller.*;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;
import model.Users;

@SuppressWarnings("serial")
public class UserControllerAdd extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		String email=request.getParameter("email");
		String id=request.getParameter("role");
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(email!=null){
			System.out.println("Entro if add");
			try{
				Role role = pm.getObjectById(Role.class, Long.parseLong(id));
				Users user = new Users(name, surname, email,role.getId(),role.getName(),true);
				pm.makePersistent(user);
			} finally {
				pm.close();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user");
			dispatcher.forward(request, response);
		}else{
			try{
				String query = "select from " + Role.class.getName();
				List<Role> role = (List<Role>) pm.newQuery(query).execute();
				request.setAttribute("role", role);
			}finally{
				pm.close();
			}
			System.out.println("Entro else add");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/User/addUsuario.jsp");
			dispatcher.forward(request, response);	

		} 
	}
}
