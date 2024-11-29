package com.metrostyle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import com.metrostyle.models.Carrinho;
import com.metrostyle.models.Cliente;
import com.metrostyle.utils.ConnectionFactory;

public class CarrinhoDAO {
	Connection conexao; 

	public boolean inserir(Carrinho carrinho) {
	    boolean retorno = false;
	    String sql;
	    try (Connection conexao = ConnectionFactory.getConnection()) {
	        // Verificar se o produto já está no carrinho
	        sql = "SELECT id_item_carrinho, quantidade FROM tb_carrinho_itens WHERE id_carrinho = ? AND id_produto = ?";
	        try (PreparedStatement psCheck = conexao.prepareStatement(sql)) {
	            psCheck.setInt(1, carrinho.getId_carrinho());
	            psCheck.setInt(2, carrinho.getId_produto());
	            
	            try (ResultSet rs = psCheck.executeQuery()) {
	                if (rs.next()) {
	                    // Se o produto já existir, acumulamos a quantidade
	                    int idItemCarrinho = rs.getInt("id_item_carrinho");
	                    int quantidadeAtual = rs.getInt("quantidade");
	                    int novaQuantidade = quantidadeAtual + carrinho.getQuantidade();

	                    // Atualizar a quantidade no carrinho
	                    sql = "UPDATE tb_carrinho_itens SET quantidade = ? WHERE id_item_carrinho = ?";
	                    try (PreparedStatement psUpdate = conexao.prepareStatement(sql)) {
	                        psUpdate.setInt(1, novaQuantidade);
	                        psUpdate.setInt(2, idItemCarrinho);
	                        
	                        int linhasAfetadas = psUpdate.executeUpdate();
	                        if (linhasAfetadas > 0) {
	                            retorno = true;
	                        }
	                    }
	                } else {
	                    // Caso não exista, insere um novo item no carrinho
	                    sql = "INSERT INTO tb_carrinho_itens (id_carrinho, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
	                    try (PreparedStatement psInsert = conexao.prepareStatement(sql)) {
	                        psInsert.setInt(1, carrinho.getId_carrinho());
	                        psInsert.setInt(2, carrinho.getId_produto());
	                        psInsert.setInt(3, carrinho.getQuantidade());
	                        psInsert.setDouble(4, carrinho.getPreco_unitario());
	                        
	                        int linhasAfetadas = psInsert.executeUpdate();
	                        if (linhasAfetadas > 0) {
	                            retorno = true;
	                        }
	                    }
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return retorno;
	}
	
	public ArrayList<Carrinho> listar(int idCliente) {
	    ArrayList<Carrinho> itensCarrinho = new ArrayList<>();
	    String sql = "SELECT ci.*, p.nome AS nome_produto " +
	                 "FROM tb_carrinho_itens ci " +
	                 "JOIN tb_produtos p ON ci.id_produto = p.id_produto " +
	                 "JOIN tb_carrinho c ON ci.id_carrinho = c.id_carrinho " +
	                 "WHERE c.id_cliente = ?";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idCliente);
	        ResultSet rs = stmt.executeQuery();

	        while (rs.next()) {
	            Carrinho item = new Carrinho();
	            item.setId_item_carrinho(rs.getInt("id_item_carrinho"));
	            item.setId_carrinho(rs.getInt("id_carrinho"));
	            item.setId_produto(rs.getInt("id_produto"));
	            item.setNomeProduto(rs.getString("nome_produto"));
	            item.setQuantidade(rs.getInt("quantidade"));
	            item.setPreco_unitario(rs.getDouble("preco_unitario"));
	            item.setSubtotal(rs.getDouble("subtotal"));
	            itensCarrinho.add(item);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return itensCarrinho;
	}

	public boolean excluir(int idItemCarrinho) throws SQLException {
	    String sql = "DELETE FROM tb_carrinho_itens WHERE id_item_carrinho = ?";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idItemCarrinho);
	        int linhasAfetadas = stmt.executeUpdate();
	        return linhasAfetadas > 0; // Retorna true se a exclusão for bem-sucedida
	    }
	}
	
	
	public Carrinho obterCarrinhoPorCliente(int idCliente) {
	    Carrinho carrinho = null;
	    String sql = "SELECT * FROM tb_carrinho WHERE id_cliente = ?";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idCliente);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            carrinho = new Carrinho();
	            carrinho.setId_carrinho(rs.getInt("id_carrinho"));
	            carrinho.setId_cliente(rs.getInt("id_cliente"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return carrinho;
	}
	
	public Carrinho buscarProdutoNoCarrinho(int idCarrinho, int idProduto) throws SQLException {
	    Carrinho carrinho = null;
	    // Usando try-with-resources para gerenciar os recursos automaticamente
	    try (Connection conexao = ConnectionFactory.getConnection()) {
	        String sql = "SELECT * FROM tb_carrinho_itens WHERE id_carrinho = ? AND id_produto = ?";
	        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
	            ps.setInt(1, idCarrinho);
	            ps.setInt(2, idProduto);

	            // Executa a consulta e processa o ResultSet
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    carrinho = new Carrinho();
	                    carrinho.setId_item_carrinho(rs.getInt("id_item_carrinho"));
	                    carrinho.setId_carrinho(rs.getInt("id_carrinho"));
	                    carrinho.setId_produto(rs.getInt("id_produto"));
	                    carrinho.setQuantidade(rs.getInt("quantidade"));
	                    carrinho.setPreco_unitario(rs.getDouble("preco_unitario"));
	                    carrinho.setSubtotal(rs.getDouble("subtotal"));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return carrinho;
	}

	
	public boolean atualizar(Carrinho carrinho) throws SQLException {
	    boolean retorno = false;
	    try (Connection conexao = ConnectionFactory.getConnection()) {
	        // Calculando o novo subtotal com base na quantidade e no preço unitário
	        double novoSubtotal = carrinho.getQuantidade() * carrinho.getPreco_unitario();

	        // SQL de atualização
	        String sql = "UPDATE tb_carrinho_itens SET quantidade = ?, subtotal = ? WHERE id_item_carrinho = ?";
	        
	        // Preparar o statement
	        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
	            ps.setInt(1, carrinho.getQuantidade());
	            ps.setDouble(2, novoSubtotal);  // Subtotal recalculado
	            ps.setInt(3, carrinho.getId_item_carrinho());
	            
	            // Executa a atualização e verifica se houve sucesso
	            int linhasAfetadas = ps.executeUpdate();
	            if (linhasAfetadas > 0) {
	                retorno = true;
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return retorno;
	}



}