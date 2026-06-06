package com.salimocarvalho.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionDB {
    private static final String URL = "jdbc:postgresql://localhost:5432/sistem_gestao_financeira";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    static void main(String[] args) {
        try {
            Connection con = ConnectionDB.getConnection();
            System.out.println("Conectado com sucesso!");
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro de conexao: " + e.getMessage());
        }
    }
}
