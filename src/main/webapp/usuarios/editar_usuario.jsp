<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.biblioteca.model.Usuario" %>

<%
Usuario usuario = (Usuario) request.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>

<head>

<title>Editar Usuario</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<div class="form-card">

<h2>Editar Usuario</h2>

<form action="<%= request.getContextPath() %>/usuarios" method="post">

<input type="hidden" name="action" value="update">
<input type="hidden" name="id" value="<%= usuario.getId() %>">

<div class="form-group">

<label>Nombre</label>

<input
type="text"
name="nombre"
value="<%= usuario.getNombre() %>"
required>

</div>

<div class="form-group">

<label>Email</label>

<input
type="email"
name="email"
value="<%= usuario.getEmail() %>"
required>

</div>

<div class="form-group">

<label>Password</label>

<input
type="password"
name="password"
value="<%= usuario.getPassword() %>"
required>

</div>

<div class="form-group">

<label>Rol</label>

<select name="rol">

<option value="Usuario"
<%= "Usuario".equals(usuario.getRol()) ? "selected" : "" %>>
Usuario
</option>

<option value="Administrador"
<%= "Administrador".equals(usuario.getRol()) ? "selected" : "" %>>
Administrador
</option>

</select>

</div>

<div class="form-actions">

<button type="submit" class="btn btn-primary">
Actualizar Usuario
</button>

<a
href="<%= request.getContextPath() %>/usuarios?action=list"
class="btn btn-secondary">
Cancelar
</a>

</div>

</form>

</div>

</div>

</body>

</html>