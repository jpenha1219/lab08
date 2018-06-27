package controller.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;


@SuppressWarnings("serial")
public class UserControllerLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		System.out.print("entro a login");
		if(user==null){

			System.out.print("entro a if");
			response.sendRedirect(us.createLoginURL("/user/login"));	
		}else{
			System.out.print("entro a else");
			request.setAttribute("user", user);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/Views/User/index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
