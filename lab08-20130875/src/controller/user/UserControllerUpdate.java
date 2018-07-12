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

import model.Role;
import model.Users;

@SuppressWarnings("serial")
public class UserControllerUpdate extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id=Long.parseLong(request.getParameter("id"));
		String name=request.getParameter("name");
		String surname=request.getParameter("surname");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(name!=null){
			try {
				Long id2=Long.parseLong(request.getParameter("idRole"));
				Users users = pm.getObjectById(Users.class, id);
				Role role = pm.getObjectById(Role.class, id2);
				users.setName(name);
				users.setSurname(surname);
				users.setIdRole(role.getId());
				users.setRole(role.getName());
				
			} finally {
				pm.close();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/user");
			dispatcher.forward(request, response);	
		}else{
			System.out.println("entro else UPdate Users");

			try{
				Users users = pm.getObjectById(Users.class, id);
				String query = "select from " + Role.class.getName();
				List<Role> role = (List<Role>) pm.newQuery(query).execute();
				request.setAttribute("users", users);
				request.setAttribute("role", role);	
				}finally{
				pm.close();
				}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/User/updateUsuario.jsp");
			dispatcher.forward(request, response);	}
	}
}

