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
<title>VISTA</title>
<%
	UserService us = UserServiceFactory.getUserService();
	User user = us.getCurrentUser();
	Users users = (Users) request.getAttribute("users");
%>
</head>
<body>
	<ul>
		<li><a class="active" href="/user/view"><%=user.getEmail()%></a></li>
		<li><a href="/user/login">Inicio</a></li>
		<li><a href="/user">Usuarios</a></li>
		<li><a href="/role">Roles</a></li>
		<li><a href="/resource">Recursos</a></li>
		<li><a href="/access">Acceso</a></li>
		<li><a href="/project">Proyectos</a></li>
		<li><a href="/user/logout">Salir</a></li>
	</ul>
	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">

		<table>
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Rol</th>
				<th>estado</th>
			</tr>
			<tr>
				<td><%=users.getName()%></td>
				<td><%=users.getSurname()%></td>
				<td><%=users.getRole()%>
				<td><%=users.getStatus()%>
			</tr>
		</table>
	</div>
</body>
</html>