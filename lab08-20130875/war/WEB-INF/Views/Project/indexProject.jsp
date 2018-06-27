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
<title>Index</title>

</head>
<body>
	<%
		UserService us = UserServiceFactory.getUserService();
		User user = us.getCurrentUser();
		List<Project> project = (List<Project>) request.getAttribute("project");
	%>

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

	<%
		if (project.size() > 0) {
	%>
	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<table border="0" cellspacing="1" cellpadding="5">
			<tr>
				<td colspan="3" id="t1"><a href="/project/add">Crear</a></td>
			</tr>
			<tr>
				<th id="t2">Name</th>
				<th id="t2">Area</th>
				<th id="t2">Resultado</th>
			</tr>
			<%
				for (Project p : project) {
			%>

			<tr>
				<td nowrap><%=p.getName()%></td>
				<td><%=p.getArea()%></td>
				<td><%=p.getState()%></td>
				<td><a href="/project/update?id=<%=p.getId()%>">Modificar</a>-<a
					href="/project/delete?id=<%=p.getId()%>">Eliminar</a>- <a
					href="/project/view?id=<%=p.getId()%>">Ver</a></td>

			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%
		} else {
	%>
	<div style="margin-left: 25%; padding: 1px 16px; height: 1000px;">
		<h3>No hay Proyectos</h3>
		<h4>
			<a href="/project/add">Crear Proyecto</a>
		</h4>
	</div>
	<%
		}
	%>

</body>
</html>