package com.metrostyle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.metrostyle.models.Produto;
import com.metrostyle.utils.ConnectionFactory;

public class ProdutoDAO {
	Connection conexao; 
	
    public ArrayList<Produto> listar() { 
        ArrayList<Produto> produtos = new ArrayList<>(); 
        String sql = "SELECT * FROM tb_produtos"; 
        try (Connection connection = ConnectionFactory.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) { 
            ResultSet rs = preparedStatement.executeQuery(); 
            while (rs.next()) { 
                int id = rs.getInt("id_produto");
                String nome = rs.getString("nome"); 
                String desc = rs.getString("descricao");
                double preco = rs.getDouble("preco"); 
                int estoque = rs.getInt("estoque"); 
                Produto itemLista = new Produto(); 
                itemLista.setId(id); 
                itemLista.setNome(nome);  
                itemLista.setDesc(desc); 
                itemLista.setPreco(preco);
                itemLista.setEstoque(estoque);;
                produtos.add(itemLista); 
            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        return produtos; 
    }

    // Método para inserir um novo produto
    public boolean inserir(Produto produto) { 
        boolean retorno = false; 
        try { 
            conexao = ConnectionFactory.getConnection(); 
            String sql = "INSERT INTO tb_produtos (nome, descricao, preco, estoque) values(?, ?, ?, ?)"; 
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDesc());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getEstoque());
            int linhasAfetadas = ps.executeUpdate(); 
            if (linhasAfetadas > 0) { 
                retorno = true; 
            }
        } 
        catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        finally { 
            // código omitido
        }  
        return retorno;  
    }

    // Método para atualizar um produto
    public boolean atualizar(Produto produto) throws SQLException {
        String sql = "UPDATE tb_produtos SET nome = ?, descricao = ?, preco = ?, estoque = ? WHERE id_produto = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDesc());
            stmt.setDouble(3, produto.getPreco());
            stmt.setInt(4, produto.getEstoque());
            stmt.setInt(5, produto.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se a atualização for bem-sucedida
        }
    }

    // Método para excluir um produto
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM tb_produtos WHERE id_produto = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se a exclusão for bem-sucedida
        }
    }
    
    public List<Produto> getAllProdutos() {
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM tb_produtos");
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }


}
