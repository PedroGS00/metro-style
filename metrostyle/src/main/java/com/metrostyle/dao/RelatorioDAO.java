package com.metrostyle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metrostyle.models.Relatorio;
import com.metrostyle.utils.ConnectionFactory;

public class RelatorioDAO {
    Connection conexao;

    // Alterando método listar para incluir JOIN nas tabelas para dados completos
    public ArrayList<Relatorio> listar() throws SQLException {
        ArrayList<Relatorio> listaRelatorio = new ArrayList<>();

        // Ajustando a consulta SQL
        String sql = "SELECT iv.id_item_venda, v.id_venda, p.nome AS produto_nome, c.nome AS cliente_nome, c.email AS cliente_email, "
                + "iv.quantidade, iv.preco_unitario, iv.subtotal, v.valor_total, v.data_venda "
                + "FROM tb_Itens_Venda iv "
                + "JOIN tb_Vendas v ON iv.id_venda = v.id_venda "
                + "JOIN tb_Produtos p ON iv.id_produto = p.id_produto "
                + "JOIN tb_Clientes c ON v.id_cliente = c.id_cliente";

        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Relatorio relatorio = new Relatorio();
                relatorio.setId_item_venda(resultSet.getInt("id_item_venda"));
                relatorio.setId_venda(resultSet.getInt("id_venda"));
                relatorio.setProduto_nome(resultSet.getString("produto_nome"));
                relatorio.setCliente_nome(resultSet.getString("cliente_nome"));
                relatorio.setCliente_email(resultSet.getString("cliente_email"));
                relatorio.setQuantidade(resultSet.getInt("quantidade"));
                relatorio.setPreco_unitario(resultSet.getDouble("preco_unitario"));
                relatorio.setSubtotal(resultSet.getDouble("subtotal"));
                relatorio.setValor_total(resultSet.getDouble("valor_total"));
                relatorio.setData_venda(resultSet.getString("data_venda"));

                listaRelatorio.add(relatorio);
            }
        }
        return listaRelatorio;
    }

    // Alterando método inserir para refletir corretamente as inserções nas tabelas tb_Vendas e tb_Itens_Venda
    public boolean inserir(Relatorio relatorio) {
        boolean retorno = false;

        // Recursos para conexão e statements
        try (Connection conexao = ConnectionFactory.getConnection()) {

            conexao.setAutoCommit(false); // Inicia a transação

            // Inserção na tabela tb_Vendas
            String sqlVenda = "INSERT INTO tb_Vendas (id_cliente, data_venda, valor_total) VALUES (?, ?, ?)";
            try (PreparedStatement psVendas = conexao.prepareStatement(sqlVenda, PreparedStatement.RETURN_GENERATED_KEYS)) {

                psVendas.setInt(1, relatorio.getId_cliente());
                psVendas.setString(2, relatorio.getData_venda());
                psVendas.setDouble(3, relatorio.getValor_total());
                int linhasAfetadasVenda = psVendas.executeUpdate();

                if (linhasAfetadasVenda > 0) {
                    // Obtém o id gerado
                    try (ResultSet rs = psVendas.getGeneratedKeys()) {
                        if (rs.next()) {
                            int id_venda = rs.getInt(1);

                            // Inserção na tabela tb_Itens_Venda
                            String sqlItemVenda = "INSERT INTO tb_Itens_Venda (id_venda, id_produto, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
                            try (PreparedStatement psItensVenda = conexao.prepareStatement(sqlItemVenda)) {
                                psItensVenda.setInt(1, id_venda);
                                psItensVenda.setInt(2, relatorio.getId_produto());
                                psItensVenda.setInt(3, relatorio.getQuantidade());
                                psItensVenda.setDouble(4, relatorio.getPreco_unitario());
                                psItensVenda.setDouble(5, relatorio.getSubtotal());

                                int linhasAfetadasItemVenda = psItensVenda.executeUpdate();
                                if (linhasAfetadasItemVenda > 0) {
                                    retorno = true;
                                }
                            }
                        }
                    }
                }
                conexao.commit(); // Confirma a transação
            } catch (SQLException e) {
                conexao.rollback(); // Reverte em caso de erro
                throw e; // Relança a exceção para controle externo
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retorno;
    }

    public ArrayList<Relatorio> filtrarPorData(String data) throws SQLException {
        ArrayList<Relatorio> listaRelatorio = new ArrayList<>();
    
        // Ajuste na consulta SQL para filtrar por data
        String sql = "SELECT iv.id_item_venda, v.id_venda, p.nome AS produto_nome, c.nome AS cliente_nome, c.email AS cliente_email, "
                + "iv.quantidade, iv.preco_unitario, iv.subtotal, v.valor_total, v.data_venda "
                + "FROM tb_Itens_Venda iv "
                + "JOIN tb_Vendas v ON iv.id_venda = v.id_venda "
                + "JOIN tb_Produtos p ON iv.id_produto = p.id_produto "
                + "JOIN tb_Clientes c ON v.id_cliente = c.id_cliente "
                + "WHERE v.data_venda = ?"; // Filtra pela data
    
        try (Connection connection = ConnectionFactory.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
    
            statement.setString(1, data); // Define o parâmetro da data
    
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Relatorio relatorio = new Relatorio();
                    relatorio.setId_item_venda(resultSet.getInt("id_item_venda"));
                    relatorio.setId_venda(resultSet.getInt("id_venda"));
                    relatorio.setProduto_nome(resultSet.getString("produto_nome"));
                    relatorio.setCliente_nome(resultSet.getString("cliente_nome"));
                    relatorio.setCliente_email(resultSet.getString("cliente_email"));
                    relatorio.setQuantidade(resultSet.getInt("quantidade"));
                    relatorio.setPreco_unitario(resultSet.getDouble("preco_unitario"));
                    relatorio.setSubtotal(resultSet.getDouble("subtotal"));
                    relatorio.setValor_total(resultSet.getDouble("valor_total"));
                    relatorio.setData_venda(resultSet.getString("data_venda"));
    
                    listaRelatorio.add(relatorio);
                }
            }
        }
    
        return listaRelatorio;
    }     

}
