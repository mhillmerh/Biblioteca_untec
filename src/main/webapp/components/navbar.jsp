<%
String rol = (String) session.getAttribute("rol");
String nombre = (String) session.getAttribute("nombre");
%>

<div class="navbar">
    <div class="navbar-brand">📚 Biblioteca UNTEC</div>
        <ul class="navbar-links">
            <% if("Administrador".equals(rol)){ %>
                <li><a href="dashboard_admin.jsp">Dashboard</a></li>
                <li><a href="<%= request.getContextPath() %>/usuarios?action=list">Usuarios</a></li>
                <li><a href="<%= request.getContextPath() %>/libros?action=list">Libros</a></li>
                <li><a href="<%= request.getContextPath() %>/prestamos?action=list">Prestamos</a></li>

            <% } else if("Usuario".equals(rol)){ %>

                <li><a href="dashboard_user.jsp">Inicio</a></li>
            <% } %>

        </ul>
        <div class="navbar-user">
            <%= nombre %>
                                        |
            <a href="logout">Cerrar Sesion</a>
        </div>

</div>