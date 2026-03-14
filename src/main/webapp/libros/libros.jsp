<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biblioteca.model.Libro" %>

<html>

<head>
<title>Libros</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<div class="page-header">

<h2>Gestión de Libros</h2>

<a href="<%= request.getContextPath() %>/libros/agregar_libro.jsp"
class="btn btn-primary">
➕ Agregar libro
</a>

</div>

<table class="table">

<thead>
<tr>
<th>ID</th>
<th>Titulo</th>
<th>Autor</th>
<th>Disponible</th>
<th>Acciones</th>
</tr>
</thead>

<tbody>

<%
List<Libro> libros = (List<Libro>) request.getAttribute("libros");

if(libros != null){
for(Libro u : libros){
%>

<tr>

<td><%= u.getId() %></td>
<td><%= u.getTitulo() %></td>
<td><%= u.getAutor() %></td>

<td>
<% if(u.isDisponible()) { %>
<span class="badge badge-green">Disponible</span>
<% } else { %>
<span class="badge badge-orange">Prestado</span>
<% } %>
</td>

<td>

<a class="btn btn-outline btn-sm"
href="<%= request.getContextPath() %>/libros?action=edit&id=<%= u.getId() %>">
Editar
</a>

<a class="btn btn-danger btn-sm"
href="<%= request.getContextPath() %>/libros?action=delete&id=<%= u.getId() %>"
onclick="return confirm('¿Eliminar libro?')">
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