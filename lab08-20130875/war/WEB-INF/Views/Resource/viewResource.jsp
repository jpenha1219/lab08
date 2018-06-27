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
	Resource resource = (Resource) request.getAttribute("resource");
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
	<h1>DATOS DEL PROYECTO</h1>
	<h2>
		<a href="/proyect">Menu</a>
	</h2>
	<table>
		<tr>
			<th>Nombre</th>
			<th>Estado</th>
		</tr>
		<tr>
			<td><%=resource.getResource()%></td>
			<td><%=resource.getState()%></td>
		</tr>
	</table>
</body>
</html>