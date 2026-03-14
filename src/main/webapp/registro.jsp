<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>

<head>

    <title>Registro - Biblioteca UNTEC</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body class="login-page">

        <div class="login-container">

            <div class="login-card">

                <div class="login-header">

                     <div class="login-icon">📚</div>

                        <h1>Registro</h1>

                        <p>Crear una cuenta en Biblioteca UNTEC</p>

                     </div>

                     <% if(request.getAttribute("error") != null){ %>

                     <div class="alert alert-error">
                        <%= request.getAttribute("error") %>
                     </div>

                      <% } %>

                <form action="registro" method="post">

                    <div class="form-group">

                        <label for="nombre">Nombre completo</label>

                        <input type="text" id="nombre" name="nombre" required>

                    </div>

                     <div class="form-group">

                         <label for="email">Correo electrónico</label>

                         <input type="email" id="email" name="email" required>

                     </div>

                     <div class="form-group">

                        <label for="password">Contraseña</label>

                        <input type="password" id="password" name="password" required>

                     </div>

                     <button type="submit" class="btn btn-primary btn-full">
                        Registrarse
                     </button>

                </form>

                <p class="login-hint">

                ¿Ya tienes cuenta?

                <a href="index.jsp" class="btn btn-outline btn-sm">Iniciar sesión</a>

                </p>

            </div>

        </div>

</body>

</html>
