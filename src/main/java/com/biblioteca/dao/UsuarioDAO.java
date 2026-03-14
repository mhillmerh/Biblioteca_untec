package com.biblioteca.dao;

import com.biblioteca.model.Usuario;
import com.biblioteca.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public Usuario login (String email, String password) {
        Usuario usuario = null;

        try {
            Connection conexion = ConexionDB.conectar();

            String sql ="SELECT * FROM usuarios WHERE email = ? AND password = ?";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs= ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("rol")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> lista = new ArrayList<>();

        try {
            Connection conexion = ConexionDB.conectar();

            String sql = "SELECT * FROM usuarios";

            Statement st = conexion.createStatement();

            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("rol")
                );
                lista.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public Usuario buscarUsuariosPorID(int id) {
        Usuario usuario = null;

        try {
            Connection conexion = ConexionDB.conectar();

            String sql = "SELECT * FROM usuarios WHERE id = ?";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("rol")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public void registrarUsuario(Usuario usuario) {
        Connection conexion = ConexionDB.conectar();
        if(conexion == null) {
            System.out.print("No se pudo estabolecer conexion con la base de datos.");
            return;
        }

        try {

            String sql = "INSERT INTO usuarios (nombre, email, password, rol) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());

            ps.executeUpdate();

            System.out.println("Usuario registrado con éxito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(int id) {

        try{
            Connection conexion = ConexionDB.conectar();

            String sql = "DELETE FROM usuarios WHERE id = ?";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Usuario eliminado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarUsuario(Usuario usuario){
        try{
            Connection conexion = ConexionDB.conectar();

            String sql = "UPDATE usuarios SET nombre = ?, email = ?, password = ?, rol = ? WHERE id = ?";

            PreparedStatement ps = conexion.prepareStatement(sql);

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getEmail());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getRol());
            ps.setInt(5, usuario.getId());

            ps.executeUpdate();

            System.out.println("Usuario actualizado correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
