package com.biblioteca.controller;

import com.biblioteca.dao.PrestamoDAO;
import com.biblioteca.dao.UsuarioDAO;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/prestamos")
public class PrestamoServlet extends HttpServlet {

    private PrestamoDAO prestamoDAO;
    private UsuarioDAO usuarioDAO;

    public void init() {
        prestamoDAO = new PrestamoDAO();
        usuarioDAO = new UsuarioDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {

            case "devolver":
                devolverLibro(request, response);
                break;

            default:
                listarPrestamos(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("prestar".equals(action)) {
            prestarLibro(request, response);
        }
    }

    private void listarPrestamos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // SOLO LIBROS DISPONIBLES
        List<Libro> libros = prestamoDAO.listarLibrosDisponibles();

        // LISTA DE USUARIOS
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();

        request.setAttribute("libros", libros);
        request.setAttribute("usuarios", usuarios);

        request.getRequestDispatcher("/prestamos/prestamos.jsp")
                .forward(request, response);
    }

    private void prestarLibro(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int libroId = Integer.parseInt(request.getParameter("libroId"));
        int usuarioId = Integer.parseInt(request.getParameter("usuarioId"));

        prestamoDAO.prestarLibro(libroId, usuarioId);

        response.sendRedirect("prestamos");
    }

    private void devolverLibro(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        prestamoDAO.devolverLibro(id);

        response.sendRedirect("prestamos");
    }
}