<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.biblioteca.model.Libro" %>

<html>

<head>

<title>Dashboard Usuario</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<h2>Mis Libros Prestados</h2>

<table class="table">

<thead>
<tr>
<th>ID</th>
<th>Titulo</th>
<th>Autor</th>
</tr>
</thead>

<tbody>

<%

List<Libro> prestados = (List<Libro>) request.getAttribute("prestados");

if(prestados != null){
for(Libro libro : prestados){

%>

<tr>

<td><%= libro.getId() %></td>
<td><%= libro.getTitulo() %></td>
<td><%= libro.getAutor() %></td>

</tr>

<%

}
}

%>

</tbody>

</table>

<br><br>

<h2>Catálogo de Libros</h2>

<table class="table">

<thead>
<tr>
<th>ID</th>
<th>Titulo</th>
<th>Autor</th>
<th>Estado</th>
</tr>
</thead>

<tbody>

<%

List<Libro> catalogo = (List<Libro>) request.getAttribute("catalogo");

if(catalogo != null){
for(Libro libro : catalogo){

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

</tr>

<%

}
}

%>

</tbody>

<div class="pagination">

<%
int paginaActual = (int) request.getAttribute("paginaActual");
int totalPaginas = (int) request.getAttribute("totalPaginas");
%>

<% if(paginaActual > 1){ %>

<a href="<%=request.getContextPath()%>/dashboard-user?page=<%=paginaActual-1%>" class="btn-page">
<< Anterior
</a>

<% } %>


<% for(int i=1; i<=totalPaginas; i++){ %>

<a href="<%=request.getContextPath()%>/dashboard-user?page=<%=i%>"
class="btn-page <%= (i == paginaActual ? "active" : "") %>">

<%=i%>

</a>

<% } %>


<% if(paginaActual < totalPaginas){ %>

<a href="<%=request.getContextPath()%>/dashboard-user?page=<%=paginaActual+1%>" class="btn-page">
Siguiente >>
</a>

<% } %>

</div>

</table>

</div>


</div>
</body>

</html>