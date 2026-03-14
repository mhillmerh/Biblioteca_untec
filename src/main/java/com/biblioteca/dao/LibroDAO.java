package com.biblioteca.dao;

import com.biblioteca.model.Libro;
import com.biblioteca.util.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LibroDAO {

    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList<>();

        try {
            Connection conexion = ConexionDB.conectar();
            String sql = "SELECT * FROM LIBROS";

            Statement stmt = conexion.createStatement();
            ResultSet resultado = stmt.executeQuery(sql);

            while (resultado.next()) {
                int id = resultado.getInt("id");
                String titulo = resultado.getString("titulo");
                String autor = resultado.getString("autor");
                boolean disponible = resultado.getBoolean("disponible");

                Libro libro =  new Libro(id, titulo, autor, disponible);

                libros.add(libro);
            }
        } catch (Exception e) {
            System.out.println("Error al listar Libros: " + e.getMessage());
        }
        return libros;
    }

    public void agregarLibro(Libro libro) {

        try {
            Connection conexion = ConexionDB.conectar();

            String sql = "INSERT INTO libros (titulo, autor, disponible) VALUES (?, ?, ?)";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setBoolean(3, libro.isDisponible());

            stmt.executeUpdate();

            System.out.println("Libro agregado correctamente");
        } catch (Exception e) {
            System.out.println("Error al agregar libro: " + e.getMessage());
        }
    }

    public Libro buscarLibroPorID (int id) {
        Libro libro =null;

        try {
            Connection conexion = ConexionDB.conectar();

            String sql = "SELECT * FROM libros WHERE id = ?";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int libroID = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                boolean disponible = rs.getBoolean("disponible");

                libro = new Libro(libroID, titulo, autor, disponible);
            }
        } catch (Exception e) {
            System.out.print("Error al buscar libro" + e.getMessage());
        }
        return libro;
    }

    public void actualizarDisponibilidad (int id, boolean disponible) {
        try{
            Connection conexion = ConexionDB.conectar();

            String sql = "UPDATE libros SET disponible = ? WHERE id = ?";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setBoolean(1, disponible);
            stmt.setInt(2, id);

            stmt.executeUpdate();
        } catch (Exception e) {
            System.out.print("Error al actualizar disponibilidad del libro: " + e.getMessage());
        }
    }

    public void actualizarLibro(Libro libro){
        try {
            Connection conexion = ConexionDB.conectar();

            String sql = "UPDATE libros SET titulo = ?, autor = ?, disponible = ? WHERE id = ?";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setString(1, libro.getTitulo());
            stmt.setString(2, libro.getAutor());
            stmt.setBoolean(3, libro.isDisponible());
            stmt.setInt(4, libro.getId());

            System.out.println("Libro actualizado con exito.");
        } catch (Exception e) {
            System.out.println("Error al actualizar libro: " + e.getMessage());
        }


    }


    public void eliminarLibro(int id) {
        try {
            Connection conexion = ConexionDB.conectar();

            String sql = "DELETE FROM libros WHERE id = ?";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();

            System.out.print("Libro eliminado con éxito");
        } catch (Exception e) {
            System.out.print("Error al eliminar libro: " + e.getMessage());
        }
    }

}
