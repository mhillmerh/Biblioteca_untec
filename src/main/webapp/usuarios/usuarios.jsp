<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biblioteca.model.Usuario" %>

<html>

<head>
<title>Usuarios</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<div class="page-header">

<h2>Gestión de Usuarios</h2>

<a href="<%= request.getContextPath() %>/usuarios/agregar_usuario.jsp"
class="btn btn-primary">
➕ Agregar Usuario
</a>

</div>

<table class="table">

<thead>
<tr>
<th>ID</th>
<th>Nombre</th>
<th>Email</th>
<th>Rol</th>
<th>Acciones</th>
</tr>
</thead>

<tbody>

<%
List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

if(usuarios != null){
for(Usuario u : usuarios){
%>

<tr>

<td><%= u.getId() %></td>
<td><%= u.getNombre() %></td>
<td><%= u.getEmail() %></td>

<td>

<% if("Administrador".equals(u.getRol())) { %>

<span class="badge badge-orange">Administrador</span>

<% } else { %>

<span class="badge badge-green">Usuario</span>

<% } %>

</td>

<td>

<a class="btn btn-outline btn-sm"
href="<%= request.getContextPath() %>/usuarios?action=edit&id=<%= u.getId() %>">
Editar
</a>

<a class="btn btn-danger btn-sm"
href="<%= request.getContextPath() %>/usuarios?action=delete&id=<%= u.getId() %>"
onclick="return confirm('¿Eliminar usuario?')">
Eliminar
</a>

</td>

</tr>

<%
}
}
%>

</tbody>

</table>

<br>

<a href="<%= request.getContextPath() %>/dashboard"
class="btn btn-secondary">
← Volver al Dashboard
</a>

</div>

</body>

</html>