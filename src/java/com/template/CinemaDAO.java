package com.template;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;

public class CinemaDAO {

    private static final Logger logger = Logger.getLogger(CinemaDAO.class.getName());

    public void cadastrarFilme(CinemaDTO cinema) {
        String sql = "INSERT INTO cinema "
                + "(nome, genero, ano_lancamento, bilheteria, nota_imdb)"
                + "values (?,?,?,?,?)";

        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql)){ /* Prepara a
                instrução SQL por parametro, prevenindo SQL Injection */

            ps.setString(1, cinema.getNome());
            ps.setString(2, cinema.getGenero());
            ps.setInt(3, cinema.getAnoLancamento());
            ps.setDouble(4, cinema.getBilheteria());
            ps.setDouble(5, cinema.getNotaIMDB());

            ps.executeUpdate();  /* Utilizado para operações de escrita, executa a instrução SQL de
                INSERT, UPDATE ou DELETE no banco de dados */

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar filme"); // Exibe detalhes do erro
        }
    }
        public ArrayList<CinemaDTO> listarFilme() {
            // 1. Cria a lista que vai guardar os filmes
            ArrayList<CinemaDTO> lista = new ArrayList<>();

            String sql = "SELECT * FROM cinema ORDER BY id";

            try (Connection c = new Conexao().conectaBD();
                 PreparedStatement ps = c.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    // 2. Cria um DTO novo para cada linha que o banco de dados achar
                    CinemaDTO cinema = new CinemaDTO();

                    // 3. Pega os dados do banco e joga dentro do DTO usando os SETTERS
                    cinema.setId(rs.getInt("id"));
                    cinema.setNome(rs.getString("nome"));
                    cinema.setGenero(rs.getString("genero"));
                    cinema.setAnoLancamento(rs.getInt("ano_lancamento"));
                    cinema.setBilheteria(rs.getDouble("bilheteria"));
                    cinema.setNotaIMDB(rs.getDouble("nota_imdb"));

                    // 4. Adiciona esse filme preenchido na lista
                    lista.add(cinema);
                }

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Erro ao listar filme");
            }

            // 5. RETORNA A LISTA CHEIA (Em vez de retornar null!)
            return lista;
        }

    public void alterarFilme(CinemaDTO cinema){
        String sql = "UPDATE cinema SET nome = ?, genero = ?,"
                + "ano_lancamento = ?, bilheteria = ?,"
                + "nota_imdb = ? WHERE id = ?";
        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql)){

            ps.setString(1, cinema.getNome());
            ps.setString(2, cinema.getGenero());
            ps.setInt(3, cinema.getAnoLancamento());
            ps.setDouble(4, cinema.getBilheteria());
            ps.setDouble(5, cinema.getNotaIMDB());
            ps.setInt(6, cinema.getId());

            ps.executeUpdate();
            System.out.println("Filme alterado!");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao alterar filme");
        }
    }

    public void deletarFilme(int id){
        String sql = "DELETE FROM cinema WHERE id = ?";
        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, id);
            int linhas = ps.executeUpdate();

            if (linhas > 0) {
                System.out.println("Filme excluido!");
            } else {
                System.out.println("Filme nao encontrado!");
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao deletar filme");
        }
    }

}

