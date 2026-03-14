package com.biblioteca.controller;

import com.biblioteca.dao.PrestamoDAO;
import com.biblioteca.model.Libro;
import com.biblioteca.model.Usuario;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard-user")
public class UsuarioDashboardServlet extends HttpServlet {

    private PrestamoDAO prestamoDAO;


    public void init(){
        prestamoDAO = new PrestamoDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if(usuario == null){
            response.sendRedirect("login.jsp");
            return;
        }

        int usuarioId = usuario.getId();

        int pagina = 1;
        int registroPorPagina = 10;

        String pageParam = request.getParameter("page");

        if (pageParam != null) {
            pagina = Integer.parseInt(pageParam);
        }

        int inicio = (pagina - 1) * registroPorPagina;

        List<Libro> prestados = prestamoDAO.listarPrestadosPorUsuario(usuarioId);
        List<Libro> catalogo = prestamoDAO.listarLibrosPaginados(inicio, registroPorPagina);

        int totalLibros =  prestamoDAO.contarLibros();

        int totalPaginas = (int) Math.ceil((double) totalLibros / registroPorPagina);

        request.setAttribute("prestados", prestados);
        request.setAttribute("catalogo", catalogo);
        request.setAttribute("paginaActual", pagina);
        request.setAttribute("totalPaginas", totalPaginas);

        request.getRequestDispatcher("/dashboard_user.jsp").forward(request, response);


    }
}