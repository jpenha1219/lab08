package controller.role;

import controller.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;

@SuppressWarnings("serial")
public class RoleControllerUpdate extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id=Long.parseLong(request.getParameter("id"));
		String nombre=request.getParameter("role");
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(nombre!=null){
		
			try {
				Role role = pm.getObjectById(Role.class, id);
				role.setName(nombre);
				
			} finally {
				pm.close();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/role");
			dispatcher.forward(request, response);	
		}else{
			try{
				Role role = pm.getObjectById(Role.class, id);
				request.setAttribute("role", role);
				}finally{
				pm.close();
				}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Roles/updateRole.jsp");
			dispatcher.forward(request, response);	}
	}
}

