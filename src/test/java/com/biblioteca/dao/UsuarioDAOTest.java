package com.biblioteca.dao;

import com.biblioteca.model.Libro;
import com.biblioteca.model.Usuario;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDAOTest {

    @Test
    void testLoginUsuario() {
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.login(
                "admin@biblioteca.com",
                "admin123456"
        );

        assertNotNull(usuario);
        assertEquals("Administrador", usuario.getRol());
    }

    @Test
    void testLoginIncorrecto(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.login(
                "123pormi@test.com",
                "12345pormi"
        );
        assertNull(usuario);
    }

    @Test
    void testBuscarUsuarioPorId(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario usuario = usuarioDAO.buscarUsuariosPorID(1);

        assertNotNull(usuario);
        assertEquals(1, usuario.getId());
    }

    @Test
    void testListarUsuarios(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        List<Usuario> usuarios = usuarioDAO.listarUsuarios();

        assertNotNull(usuarios);
        assertTrue(usuarios.size() >0 );
    }

    @Test
    void testRegistrarUsuario(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = new Usuario();

        usuario.setNombre("test");
        usuario.setEmail("test@email.com");
        usuario.setPassword("123456");
        usuario.setRol("Usuario");

        usuarioDAO.registrarUsuario(usuario);

        assertTrue(true);
    }

    @Test
    void testEliminarUsuario(){
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.eliminarUsuario(1);

        assertTrue(true);
    }

    @Test
    void testListarLibros(){
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        List<Libro> libros = prestamoDAO.listarLibros();

        assertNotNull(libros);
        assertTrue(libros.size() > 0);
    }

    @Test
    void testcontarLibros(){
        PrestamoDAO prestamoDAO = new PrestamoDAO();

        int total = prestamoDAO.contarLibros();
        assertTrue(total >=0);
    }
}
