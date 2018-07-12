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
import model.Resource;
import model.Role;

@SuppressWarnings("serial")
public class AccessControllerUpdate extends HttpServlet {
	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id=Long.parseLong(request.getParameter("id"));
		String idRole=request.getParameter("role");
		String idResource=request.getParameter("resource");
		System.out.println(id);
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(idRole!=null){
			System.out.println("entro if updateaccess");
			try {
				Access access = pm.getObjectById(Access.class, id);
				Role role = pm.getObjectById(Role.class, Long.parseLong(idRole));
				Resource resource = pm.getObjectById(Resource.class, Long.parseLong(idResource));
				access.setIdRole( role.getId());
				access.setNameRole(role.getName());
				access.setIdResource(resource.getId());
				access.setNameResource(resource.getResource());
				
			} finally {
				pm.close();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/access");
			dispatcher.forward(request, response);	
		}else{
			System.out.println("entro else updateaccess");
			try{
				Access access = pm.getObjectById(Access.class, id);
				String query = "select from " + Role.class.getName();
				List<Role> role = (List<Role>) pm.newQuery(query).execute();
				String query2= "select from " + Resource.class.getName();
				List<Resource> resource = (List<Resource>) pm.newQuery(query2).execute();
				request.setAttribute("access", access);
				request.setAttribute("role", role);
				request.setAttribute("resource", resource);
				}finally{
				pm.close();
				}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/updateAccess.jsp");
			dispatcher.forward(request, response);	}
	}
}

