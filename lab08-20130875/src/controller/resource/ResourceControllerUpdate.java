package controller.resource;

import controller.*;
import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Resource;

@SuppressWarnings("serial")
public class ResourceControllerUpdate extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long id=Long.parseLong(request.getParameter("id"));
		String nombre=request.getParameter("resource");
		Boolean estado=Boolean.valueOf(request.getParameter("estado"));
		PersistenceManager pm = PMF.get().getPersistenceManager();
		if(nombre!=null){
		
			try {
				Resource resource = pm.getObjectById(Resource.class, id);
				resource.setResource(nombre);
				resource.setState(estado);
				
			} finally {
				pm.close();
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/resource");
			dispatcher.forward(request, response);	
		}else{
			try{
				Resource resource = pm.getObjectById(Resource.class, id);
				request.setAttribute("resource", resource);
				}finally{
				pm.close();
				}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Resource/updateResource.jsp");
			dispatcher.forward(request, response);	}
	}
}

