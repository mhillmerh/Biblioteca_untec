package com.biblioteca.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3307/biblioteca?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static ConexionDB instancia;
    private Connection conexion;

    private ConexionDB() {

        try {

            Class.forName(DRIVER);

            conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);

            System.out.println("✅ Conexión a base de datos establecida.");

        } catch (ClassNotFoundException e) {

            throw new RuntimeException("❌ Driver no encontrado: " + e.getMessage());

        } catch (SQLException e) {

            throw new RuntimeException("❌ Error conectando a BD: " + e.getMessage());

        }
    }

    public static synchronized ConexionDB getInstancia() {

        if (instancia == null || estaConexionCerrada()) {
            instancia = new ConexionDB();
        }

        return instancia;
    }

    public Connection getConexion() {
        return conexion;
    }

    private static boolean estaConexionCerrada() {

        try {

            return instancia.conexion == null || instancia.conexion.isClosed();

        } catch (SQLException e) {

            return true;

        }
    }
    public static Connection conectar() {
        return getInstancia().getConexion();
    }
}