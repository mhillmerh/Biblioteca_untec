package com.biblioteca.controller;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = usuarioDAO.login(email, password);

        if (usuario != null) {

            HttpSession session = request.getSession();

            // Guardar datos en sesión
            session.setAttribute("usuario", usuario);
            session.setAttribute("nombre", usuario.getNombre());
            session.setAttribute("rol", usuario.getRol());

            // Ir al controlador del dashboard
            // REDIRECCIÓN SEGÚN ROL
            if(usuario.getRol().equalsIgnoreCase("Administrador")){

                response.sendRedirect(request.getContextPath() + "/dashboard");

            }else{

                response.sendRedirect(request.getContextPath() + "/dashboard-user");

            }


        } else {
            request.setAttribute("Error", "Usuario o contraseña no validos");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
