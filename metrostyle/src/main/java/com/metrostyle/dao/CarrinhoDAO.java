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
	    try {
	        conexao = ConnectionFactory.getConnection();
	        String sql = "INSERT INTO tb_carrinhos_itens (id_carrinho, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
	        PreparedStatement ps = conexao.prepareStatement(sql);
	        ps.setInt(1, carrinho.getId_carrinho());
	        ps.setInt(2, carrinho.getId_produto());
	        ps.setInt(3, carrinho.getQuantidade());
	        ps.setDouble(4, carrinho.getPreco_unitario());
	        
	        int linhasAfetadas = ps.executeUpdate();
	        if (linhasAfetadas > 0) {
	            retorno = true;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (conexao != null) {
	                conexao.close();
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
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
	
	public Carrinho criarCarrinho(int idCliente) {
	    String sql = "INSERT INTO tb_carrinho (id_cliente) VALUES (?)";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	        stmt.setInt(1, idCliente);
	        stmt.executeUpdate();
	        ResultSet rs = stmt.getGeneratedKeys();
	        if (rs.next()) {
	            Carrinho carrinho = new Carrinho();
	            carrinho.setId_carrinho(rs.getInt(1)); // Pega o ID gerado
	            carrinho.setId_cliente(idCliente);
	            return carrinho;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	
	public boolean adicionarItem(int idCliente, int idProduto) {
	    String sql = "INSERT INTO tb_carrinho_itens (id_carrinho, id_produto, quantidade, preco_unitario, subtotal) " +
	                 "VALUES ((SELECT id_carrinho FROM tb_carrinho WHERE id_cliente = ?), ?, 1, " +
	                 "        (SELECT preco FROM tb_produtos WHERE id_produto = ?), " +
	                 "        (SELECT preco FROM tb_produtos WHERE id_produto = ?))";
	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, idCliente);
	        stmt.setInt(2, idProduto);
	        stmt.setInt(3, idProduto);
	        stmt.setInt(4, idProduto);
	        return stmt.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}


