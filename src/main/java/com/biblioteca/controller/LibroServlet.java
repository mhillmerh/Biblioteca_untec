package com.biblioteca.controller;

import com.biblioteca.dao.LibroDAO;
import com.biblioteca.model.Libro;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/libros")
public class LibroServlet extends HttpServlet {

    private LibroDAO libroDAO;

    public void init() {
        libroDAO = new LibroDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                listarLibros(request, response);
                break;

            case "delete":
                eliminarLibro(request, response);
                break;

            case "edit":
                mostrarEditar(request, response);
                break;

            default:
                listarLibros(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            response.sendRedirect("libros?action=list");
            return;
        }
        switch (action) {

            case "insert":
                insertarLibro(request, response);
                break;

            case "update":
                actualizarLibro(request, response);
                break;

            default:
                response.sendRedirect("libros?action=list");
        }



    }

    private void listarLibros(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Libro> listaLibros = libroDAO.listarLibros();

        request.setAttribute("libros", listaLibros);

        request. getRequestDispatcher("/libros/libros.jsp").forward(request, response);
    }

    private void insertarLibro(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        boolean disponible = Boolean.parseBoolean(request.getParameter("disponible"));

        Libro libro = new Libro();

        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setDisponible(disponible);

        libroDAO.agregarLibro(libro);

        response.sendRedirect("libros?action=list");
    }

    private void actualizarLibro(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String titulo = request.getParameter("titulo");
        String autor = request.getParameter("autor");
        boolean disponible = Boolean.parseBoolean(request.getParameter("disponible"));

        Libro libro = new Libro();

        libro.setId(id);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setDisponible(disponible);

        libroDAO.actualizarLibro(libro);

        response.sendRedirect("libros?action=list");
    }

    private void eliminarLibro(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        libroDAO.eliminarLibro(id);

        response.sendRedirect("libros?action=list");
    }

    private void mostrarEditar(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Libro libro = libroDAO.buscarLibroPorID(id);

        request.setAttribute("libro", libro);

        request.getRequestDispatcher("/libros/editar_libro.jsp").forward(request, response);
    }
}
