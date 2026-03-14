<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biblioteca.model.Libro" %>
<%@ page import="com.biblioteca.model.Usuario" %>

<html>

<head>

<title>Préstamos</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<h2>Gestión de Préstamos</h2>

<table class="table">

<thead>

<tr>
<th>ID</th>
<th>Titulo</th>
<th>Autor</th>
<th>Estado</th>
<th>Acción</th>
</tr>

</thead>

<tbody>

<%

List<Libro> libros = (List<Libro>) request.getAttribute("libros");
List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");

if(libros != null){

for(Libro libro : libros){

%>

<tr>

<td><%= libro.getId() %></td>
<td><%= libro.getTitulo() %></td>
<td><%= libro.getAutor() %></td>

<td>

<% if(libro.isDisponible()){ %>

<span class="badge badge-green">Disponible</span>

<% } else { %>

<span class="badge badge-orange">Prestado</span>

<% } %>

</td>

<td>

<% if(libro.isDisponible()){ %>

<form action="<%= request.getContextPath() %>/prestamos" method="post">

<input type="hidden" name="action" value="prestar">
<input type="hidden" name="libroId" value="<%= libro.getId() %>">

<select name="usuarioId" required>

<option value="">Seleccionar usuario</option>

<%

if(usuarios != null){
for(Usuario u : usuarios){

%>

<option value="<%= u.getId() %>">
<%= u.getNombre() %>
</option>

<%

}
}

%>

</select>

<button type="submit" class="btn btn-primary btn-sm">
Prestar
</button>

</form>

<% } else { %>

<a class="btn btn-secondary btn-sm"
href="<%= request.getContextPath() %>/prestamos?action=devolver&id=<%= libro.getId() %>">

Devolver

</a>

<% } %>

</td>

</tr>

<%

}

}

%>

</tbody>

</table>

<br><br>

<a href="<%= request.getContextPath() %>/dashboard"
class="btn btn-secondary">
← Volver al Dashboard
</a>

</div>

</body>

</html>