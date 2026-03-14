<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
<title>Agregar Libro</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<div class="form-card">

<h2>Agregar libro</h2>

<form action="<%= request.getContextPath() %>/libros" method="post">

<input type="hidden" name="action" value="insert">

<div class="form-group">
<label>Titulo</label>
<input type="text" name="titulo" required>
</div>

<div class="form-group">
<label>Autor</label>
<input type="text" name="autor" required>
</div>

<div class="form-group">
<select name="disponible">
<option value="true">Disponible</option>
<option value="false">No disponible</option>
</select>
</div>

</div>

<div class="form-actions">

<button type="submit" class="btn btn-primary">
Guardar Libro
</button>

<a href="<%= request.getContextPath() %>/libros?action=list"
class="btn btn-secondary">
Cancelar
</a>

</div>

</form>

</div>

</div>

</body>

</html