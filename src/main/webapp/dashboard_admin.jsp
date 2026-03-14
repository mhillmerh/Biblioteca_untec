<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

<title>Dashboard Admin - Biblioteca UNTEC</title>

<link rel="stylesheet"
href="<%= request.getContextPath() %>/css/style.css">

</head>

<body>

<jsp:include page="/components/navbar.jsp" />

<div class="main-content">

<div class="page-header">

<div>
<h2>Panel de Administración</h2>
<p class="subtitle">Gestión general de la biblioteca</p>
</div>

</div>

<!-- Tarjetas del dashboard -->

<div class="card-grid">

<!-- Usuarios -->

<div class="card card-indigo">

<div class="card-icon">👤</div>

<div>

<span class="card-number">Usuarios</span>
<span class="card-label">Administrar usuarios del sistema</span>

<br><br>

<a href="<%= request.getContextPath() %>/usuarios?action=list"
class="btn btn-outline btn-sm">
Gestionar
</a>

</div>

</div>

<!-- Libros -->

<div class="card card-indigo">

<div class="card-icon">📚</div>

<div>

<span class="card-number">Libros</span>
<span class="card-label">Gestionar catálogo de libros</span>

<br><br>

<a href="<%= request.getContextPath() %>/libros?action=list"
class="btn btn-outline btn-sm">
Gestionar
</a>

</div>

</div>

<!-- Préstamos -->

<div class="card card-indigo">

<div class="card-icon">🔄</div>

<div>

<span class="card-number">Préstamos</span>
<span class="card-label">Administrar préstamos</span>

<br><br>

<a href="<%= request.getContextPath() %>/prestamos?action=list"
class="btn btn-outline btn-sm">
Ver préstamos
</a>

</div>

</div>

</div>

</div>

</body>
</html>>