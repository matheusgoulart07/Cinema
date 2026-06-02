package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    static String conexao = "jdbc:postgresql://localhost:5432/cinema";
    static String usuario = "postgres";
    static String senha = "postgres";

    //Estabelece e retorna uma conexão com o banco de dados.

    public Connection conectaBD() {
        try {
            return DriverManager.getConnection(conexao, usuario, senha);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

}
