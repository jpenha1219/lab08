package controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@SuppressWarnings("serial")
public class UserControllerLogout extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService us = UserServiceFactory.getUserService();
		User project = us.getCurrentUser();
		if(project==null){
			System.out.print("entro a if logout");
			response.sendRedirect(us.createLoginURL("/user/login"));
			
		}else{
			System.out.print("entro a if logout");
			response.sendRedirect(us.createLoginURL("/user/login"));
		}

	}
}
