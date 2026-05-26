package com.template;

public class CinemaDAO {

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
            e.printStackTrace(); // Exibe detalhes do erro
        }
    }

    public void listarFilme(){
        DecimalFormat df = new DecimalFormat("#,##0.00"); /* Formata a
        bilheteria sem notação científica */
        String sql = "SELECT * FROM cinema ORDER BY id";
        try (Connection c = new Conexao().conectaBD();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery();) { /* Utilizado em comandos
                SELECT, executa a consulta SQL e armazena os resultados
                retornados pelo banco */

            while(rs.next())
            {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("nome") + " | " +
                                rs.getString("genero") + " | " +
                                rs.getInt("ano_lancamento") + " | " +
                                df.format(rs.getDouble("bilheteria")) + " | " +
                                rs.getDouble("nota_imdb")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

}
