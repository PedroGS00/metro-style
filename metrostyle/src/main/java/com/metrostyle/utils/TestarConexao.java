package com.metrostyle.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestarConexao {
    public static void main(String[] args) {
        // URL do banco de dados
        String url = "jdbc:mysql://localhost:3306/db_metroStyle"; // Troque pelo seu banco
        String usuario = "root"; // Substitua pelo seu usuário
        String senha = ""; // Substitua pela sua senha

        try (Connection conexao = DriverManager.getConnection(url, usuario, senha)) {
            System.out.println("Conexão bem-sucedida!");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados.");
            e.printStackTrace();
        }
    }
}