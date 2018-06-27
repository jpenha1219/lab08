package controller.access;

import java.io.IOException;
import java.util.List;

import controller.*;
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
public class AccessControllerAdd extends HttpServlet {

	@SuppressWarnings("unchecked")
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idRole=request.getParameter("role");
		String idResource=request.getParameter("resource");
		PersistenceManager pm = PMF.get().getPersistenceManager();

		if(idRole!=null){

			System.out.println("if add access");
			try {
				System.out.println("try add access");
				Role role = pm.getObjectById(Role.class, Long.parseLong(idRole));
				Resource resource = pm.getObjectById(Resource.class, Long.parseLong(idResource));
				Access p = new Access(role.getId(),role.getName(),resource.getId(),resource.getResource(),true);
				pm.makePersistent(p);
			} finally {
				pm.close();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/access");
			
			dispatcher.forward(request, response);

		}else{
			System.out.println("else add access");
			String query = "select from " + Role.class.getName();
			List<Role> role = (List<Role>) pm.newQuery(query).execute();
			String query2= "select from " + Resource.class.getName();
			List<Resource> resource = (List<Resource>) pm.newQuery(query2).execute();
			request.setAttribute("role", role);
			request.setAttribute("resource", resource);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Access/addAccess.jsp");
			dispatcher.forward(request, response);
		} 

	}
}
