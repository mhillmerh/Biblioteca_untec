package com.biblioteca.controller;

import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    @Override
    public void init() throws ServletException {
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if(action == null){
            action = "list";
        }

        switch(action){

            case "list":
                listarUsuarios(request,response);
                break;

            case "edit":
                mostrarFormularioEditar(request,response);
                break;

            case "delete":
                eliminarUsuario(request,response);
                break;

            default:
                listarUsuarios(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        switch(action){

            case "insert":
                insertarUsuario(request,response);
                break;

            case "update":
                actualizarUsuario(request,response);
                break;

            default:
                response.sendRedirect("usuarios?action=list");
                break;
        }
    }

    // 📋 LISTAR USUARIOS
    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuario> listaUsuarios = usuarioDAO.listarUsuarios();

        request.setAttribute("usuarios", listaUsuarios);

        request.getRequestDispatcher("/usuarios/usuarios.jsp")
                .forward(request,response);
    }

    // ➕ INSERTAR USUARIO
    private void insertarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        Usuario usuario = new Usuario();

        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRol(rol);

       usuarioDAO.registrarUsuario(usuario);

        response.sendRedirect("usuarios?action=list");
    }

    // ❌ ELIMINAR USUARIO
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        usuarioDAO.eliminarUsuario(id);

        response.sendRedirect("usuarios?action=list");
    }

    // ✏️ MOSTRAR FORMULARIO DE EDICIÓN
    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Usuario usuario = usuarioDAO.buscarUsuariosPorID(id);

        request.setAttribute("usuario", usuario);

        request.getRequestDispatcher("/usuarios/editar_usuario.jsp")
                .forward(request,response);
    }

    // 🔄 ACTUALIZAR USUARIO
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rol = request.getParameter("rol");

        Usuario usuario = new Usuario();

        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);
        usuario.setRol(rol);

        usuarioDAO.actualizarUsuario(usuario);

        response.sendRedirect("usuarios?action=list");
    }
}
