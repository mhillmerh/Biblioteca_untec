<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>

    <title>Login - Biblioteca UNTEC</title>
    <link rel="stylesheet" href="css/style.css">

</head>
<body class="login-page">
    <div class="login-container">
            <div class="login-card">
                <div class="login-header">
                    <div class="login-icon">📚</div>
                    <h1>Biblioteca UNTEC</h1>
                    <p>Sistema de Biblioteca Digital</p>
                </div>
                <% if(request.getAttribute("error") != null){ %>
                <div class="alert alert-error">
                    <%= request.getAttribute("error") %>
                </div>
                <% } %>
                <form action="login" method="post">
                    <div class="form-group">
                        <label for="email">Correo electrónico</label>
                        <input type="email" id="email" name="email" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" id="password" name="password" required>
                    </div>

                    <button type="submit" class="btn btn-primary btn-full">
                        Iniciar sesión
                    </button>

                </form>

                <p class="login-hint">
                    ¿No tienes cuenta?
                    <a href="registro.jsp" class="btn btn-outline btn-sm">Registrarse</a>
                </p>
            </div>
     </div>

</body>
</html>