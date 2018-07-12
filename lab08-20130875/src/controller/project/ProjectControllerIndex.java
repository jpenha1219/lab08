package controller.project;

import controller.*;
import java.io.IOException;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.UserServiceFactory;

import model.Access;
import model.Project;
import model.Resource;
import model.Users;
@SuppressWarnings("serial")
public class ProjectControllerIndex extends HttpServlet {
	@SuppressWarnings({ "unchecked"})
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		com.google.appengine.api.users.User uGoogle=UserServiceFactory.getUserService().getCurrentUser();

		String respuesta = null;
		System.out.println("Ingreso index Proyect");
		if(uGoogle==null){
			System.out.println("Ingreso primer if");
			respuesta="Falta loguearse";
			request.setAttribute("respuesta", respuesta);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/erro1.jsp");
			dispatcher.forward(request, response);
		}else{
			System.out.println("Ingreso primer else");
			PersistenceManager pm = PMF.get().getPersistenceManager();
			System.out.println(uGoogle.getEmail());

			String query1 = "select from "+ Users.class.getName() + " WHERE email == '"+ uGoogle.getEmail()+"'"+
					" && status==true";
			List<Users> users = (List<Users>)pm.newQuery(query1).execute();
			System.out.println("listo");
			if(users.isEmpty()){
				System.out.println("Ingreso segundo if");
				respuesta="Usuario no Registrado";
				request.setAttribute("respuesta", respuesta);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/error1.jsp");
				dispatcher.forward(request, response);}
			else{
				System.out.println("Ingreso segundo else");
				System.out.println(request.getServletPath());
				
				String Query2="select from "+ Resource.class.getName() +" where resource=='"+request.getServletPath()+"'"
						+ " && state==true";
				List<Resource> resource = (List<Resource>)pm.newQuery(Query2).execute();	

				if(resource.isEmpty()){
					System.out.println("Ingreso tercer if");
					respuesta="recurso no Registrado";
					request.setAttribute("respuesta", respuesta);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/error1.jsp");
					dispatcher.forward(request, response);
				}else{
					System.out.println("Ingreso tercer else");
					String Query3 = "select from "+ Access.class.getName() +
							" where idRole == "+users.get(0).getIdRole() +
							" && idResource =="+resource.get(0).getId() +
							" && state == true";
					System.out.println(users.get(0).getIdRole());
					System.out.println(resource.get(0).getId());

					List<Access> access = (List<Access>)pm.newQuery(Query3).execute();	
					if(access.isEmpty()){
						System.out.println("Ingreso cuarto if");
						respuesta="Acceso no Registrado";
						request.setAttribute("respuesta", respuesta);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Error/error1.jsp");
						dispatcher.forward(request, response);
					}else{
						System.out.println("Ingreso cuarto else");
						try {
							String query = "select from " + Project.class.getName();
							List<Project> project = (List<Project>) pm.newQuery(query).execute();
							request.setAttribute("project", project);
						} finally {
							pm.close();
						}					
						
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/Project/indexProject.jsp");
						dispatcher.forward(request, response);
					}
				}
			}


		}
	}
}
