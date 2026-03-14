<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.biblioteca.model.Libro" %>

<%
Libro libro = (Libro) request.getAttribute("libro");
%>

<!DOCTYPE html>
<html>

<head>

<title>Editar libro</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<div class="form-card">

<h2>Editar libro</h2>

<form action="<%= request.getContextPath() %>/libros" method="post">

<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="<%= libro.getId() %>">

<div class="form-group">

<label>Titulo</label>

<input
type="text"
name="titulo"
value="<%= libro.getTitulo() %>"
required>

</div>

<div class="form-group">

<label>Autor</label>

<input
type="text"
name="autor"
value="<%= libro.getAutor() %>"
required>

</div>

<div class="form-group">

<label>Disponible</label>
<select name="disponible">
<option value="true">Disponible</option>
<option value="false">No disponible</option>
</select>

</div>

<div class="form-actions">

<button type="submit" class="btn btn-primary">
Actualizar libro
</button>

<a
href="<%= request.getContextPath() %>/libros?action=list"
class="btn btn-secondary">
Cancelar
</a>

</div>

</form>

</div>

</div>

</body>

</html>