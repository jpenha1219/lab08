<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List"%>
<%@ page import="model.*"%>

<%@ page import="com.google.appengine.api.users.User"%>
<%@ page import="com.google.appengine.api.users.UserService"%>
<%@ page import="com.google.appengine.api.users.UserServiceFactory"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/myStyle.css">
<title>MODIFICAR</title>
<%
	UserService us = UserServiceFactory.getUserService();
	User user = us.getCurrentUser();
	Users users = (Users) request.getAttribute("users");
	List<Role> role=(List<Role>)request.getAttribute("role");
	System.out.println("update usuario jsp");
%>
</head>
<body>


	<ul>
		<li><a class="active" href="#home"><%=user.getEmail()%></a></li>
		<li><a href="/user/login">Inicio</a></li>
		<li><a href="/user">Usuarios</a></li>
		<li><a href="/role">Roles</a></li>
		<li><a href="/resource">Recursos</a></li>
		<li><a href="/access">Acceso</a></li>
		<li><a href="/project">Proyectos</a></li>
		<li><a href="/user/logout">Salir</a></li>
	</ul>



	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		

		<form action="/user/update" method="Get">
			<table>
				<tr>
					<th colspan="2">MODIFICAR</th>
				</tr>
				<tr>
					<td>Nombre:</td>
					<td><input id="name" type="text" value="<%=users.getName()%>"
						name="name"></td>
					<td id="rpta"></td>
				</tr>
				<tr>
					<td>Apellidos</td>
					<td><input id="result" type="text"
						value="<%=users.getSurname()%>" name="surname"></td>
					<td id="rpta2"></td>
				</tr>
				<tr>
					<td>Rol</td>
					<td><select name="idRole">
							<%for(Role a:role) {%>
							<option value="<%=a.getId()%>"><%=a.getName()%></option>
							<%} %>
					</select></td>
				</tr>
				<tr>
					<td colspan="2"><input type="hidden" name="id"
						value="<%=users.getId()%>"> <input type="submit" value="Modificar"></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>