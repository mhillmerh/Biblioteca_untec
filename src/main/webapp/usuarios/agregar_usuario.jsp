<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
<title>Agregar Usuario</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<div class="form-card">

<h2>Agregar Usuario</h2>

<form action="<%= request.getContextPath() %>/usuarios" method="post">

<input type="hidden" name="action" value="insert">

<div class="form-group">
<label>Nombre</label>
<input type="text" name="nombre" required>
</div>

<div class="form-group">
<label>Email</label>
<input type="email" name="email" required>
</div>

<div class="form-group">
<label>Password</label>
<input type="password" name="password" required>
</div>

<div class="form-group">
<label>Rol</label>

<select name="rol">

<option value="Usuario">Usuario</option>
<option value="Administrador">Administrador</option>

</select>

</div>

<div class="form-actions">

<button type="submit" class="btn btn-primary">
Guardar Usuario
</button>

<a href="<%= request.getContextPath() %>/usuarios?action=list"
class="btn btn-secondary">
Cancelar
</a>

</div>

</form>

</div>

</div>

</body>

</html>