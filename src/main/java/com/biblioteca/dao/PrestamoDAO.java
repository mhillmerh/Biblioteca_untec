package com.biblioteca.dao;

import com.biblioteca.model.Libro;
import com.biblioteca.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public List<Libro> listarLibros() {

        List<Libro> libros = new ArrayList<>();

        try {

            Connection conexion = ConexionDB.conectar();

            String sql = "SELECT * FROM libros";

            Statement stmt = conexion.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");
                boolean disponible = rs.getBoolean("disponible");

                Libro libro = new Libro(id, titulo, autor, disponible);

                libros.add(libro);
            }

        } catch (Exception e) {
            System.out.println("Error al listar libros: " + e.getMessage());
        }

        return libros;
    }

    public void cambiarDisponibilidad(int id, boolean disponible) {

        try {

            Connection conexion = ConexionDB.conectar();

            String sql = "UPDATE libros SET disponible = ? WHERE id = ?";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setBoolean(1, disponible);
            stmt.setInt(2, id);

            stmt.executeUpdate();

        } catch (Exception e) {

            System.out.println("Error al cambiar disponibilidad: " + e.getMessage());
        }
    }

    public void prestarLibro(int libroId, int usuarioId){

        try{

            Connection conexion = ConexionDB.conectar();

            String sql =
                    "INSERT INTO prestamos (usuario_id, libro_id, fecha_prestamo) " +
                            "VALUES (?, ?, NOW())";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, usuarioId);
            stmt.setInt(2, libroId);

            stmt.executeUpdate();

            cambiarDisponibilidad(libroId,false);

        }catch(Exception e){
            System.out.println("Error prestar libro: " + e.getMessage());
        }
    }

    public void devolverLibro(int libroId){

        try{

            Connection conexion = ConexionDB.conectar();

            String sql =
                    "UPDATE prestamos " +
                            "SET fecha_devolucion = NOW() " +
                            "WHERE libro_id = ? AND fecha_devolucion IS NULL";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, libroId);

            stmt.executeUpdate();

            cambiarDisponibilidad(libroId,true);

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public List<Libro> listarPrestadosPorUsuario(int usuarioId){

        List<Libro> lista = new ArrayList<>();

        try{

            Connection conexion = ConexionDB.conectar();

            String sql =
                    "SELECT l.id, l.titulo, l.autor, l.disponible " +
                    "FROM prestamos p " +
                    "JOIN libros l ON p.libro_id = l.id " +
                    "WHERE p.usuario_id = ?";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, usuarioId);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){

                Libro libro = new Libro();

                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setDisponible(rs.getBoolean("disponible"));

                lista.add(libro);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return lista;
    }

    public List<Libro> listarLibrosDisponibles() {

        List<Libro> lista = new ArrayList<>();

        try {

            Connection conexion = ConexionDB.conectar();

            String sql = "SELECT * FROM libros WHERE disponible = true";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Libro libro = new Libro();

                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setDisponible(rs.getBoolean("disponible"));

                lista.add(libro);
            }

            rs.close();
            stmt.close();
            conexion.close();

        } catch (Exception e) {
            System.out.println("Error al listar libros disponibles: " + e.getMessage());
        }

        return lista;
    }

    public List<Libro> listarLibrosPaginados(int inicio, int limite) {
        List<Libro> lista = new ArrayList<>();

        try{
            Connection conexion =  ConexionDB.conectar();

            String sql = "SELECT * FROM libros LIMIT ?, ?";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            stmt.setInt(1, inicio);
            stmt.setInt(2, limite);

            ResultSet rs = stmt.executeQuery();

            while(rs.next()) {
                Libro libro = new Libro();
                libro.setId(rs.getInt("id"));
                libro.setTitulo(rs.getString("titulo"));
                libro.setAutor(rs.getString("autor"));
                libro.setDisponible(rs.getBoolean("disponible"));

                lista.add(libro);
            }
        } catch (Exception e){
            System.out.println("Error, No se puede realizar paginación de libros: " + e.getMessage());
        }

        return lista;
    }

    public int contarLibros() {
        int total = 0;
        try{
            Connection conexion = ConexionDB.conectar();

            String sql = "SELECT COUNT(*) FROM libros";

            PreparedStatement stmt = conexion.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                total = rs.getInt(1);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return total;
    }

}