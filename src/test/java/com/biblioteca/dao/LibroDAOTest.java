package com.biblioteca.dao;

import com.biblioteca.model.Libro;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LibroDAOTest {

    @Test
    void testListarLibros(){
        LibroDAO libroDAO = new LibroDAO();

        List<Libro> libros = libroDAO.listarLibros();

        assertNotNull(libros);
    }

    @Test
    void testBuscarLibroPorId(){
        LibroDAO libroDAO = new LibroDAO();

        Libro libro = libroDAO.buscarLibroPorID(1);

        assertNotNull(libro);
    }

    @Test
    void testAgregarLibro(){
        LibroDAO libroDAO = new LibroDAO();

        Libro libro = new Libro();
        libro.setTitulo("Libro test de test");
        libro.setAutor("Test Tester");
        libro.setDisponible(true);

        libroDAO.agregarLibro(libro);

        assertTrue(true);
    }

    @Test
    void testActulizarDisponibilidad(){
        LibroDAO libroDAO = new LibroDAO();

        libroDAO.actualizarDisponibilidad(1, false);

        assertTrue(true);
    }

    @Test
    void testActualizarLibro() {
        LibroDAO libroDAO = new LibroDAO();

        Libro libro = new Libro();

        libro.setId(1);
        libro.setTitulo("Titulo nuevo");
        libro.setAutor("Acutor nuevo");
        libro.setDisponible(true);

        libroDAO.actualizarLibro(libro);

        assertTrue(true);
    }

    @Test
    void testEliminarLibro(){
        LibroDAO libroDAO = new LibroDAO();

        libroDAO.eliminarLibro(10);

        assertTrue(true);
    }
}
