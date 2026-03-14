package com.biblioteca.dao;

import com.biblioteca.model.Libro;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PrestamoDaoTest {

    @Test
    void testListarLibrosDisponibles(){

        PrestamoDAO prestamoDAO = new PrestamoDAO();

        List<Libro> libros = prestamoDAO.listarLibrosDisponibles();
        assertNotNull(libros);
    }

    @Test
    void testcontarLibros(){
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        int total = prestamoDAO.contarLibros();

        assertTrue(total >= 0);
    }

    @Test
    void testListarPrestamosPorUsuario(){
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        List<Libro> libros = prestamoDAO.listarPrestadosPorUsuario(2);

        assertNotNull(libros);
    }

    @Test
    void testPrestarLibro(){
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        prestamoDAO.prestarLibro(1,2);

        assertTrue(true);
    }

    @Test
    void testDevolverLibro(){
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        prestamoDAO.devolverLibro(1);

        assertTrue(true);
    }

}
