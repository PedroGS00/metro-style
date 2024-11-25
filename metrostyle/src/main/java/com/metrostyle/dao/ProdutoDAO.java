package com.metrostyle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                String marca = rs.getString("marca"); 
                String desc = rs.getString("descricao");
                double valor = rs.getDouble("valor"); 
                Produto itemLista = new Produto(); 
                itemLista.setId(id); 
                itemLista.setMarca(marca);  
                itemLista.setDesc(desc); 
                itemLista.setValor(valor);
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
            String sql = "INSERT INTO tb_produtos (marca, descricao, valor) values(?, ?, ?)"; 
            PreparedStatement ps = conexao.prepareStatement(sql); 
            ps.setString(1, produto.getMarca()); 
            ps.setString(2, produto.getDesc()); 
            ps.setDouble(3, produto.getValor());
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
        String sql = "UPDATE tb_produtos SET marca = ?, descricao = ?, valor = ? WHERE id_produto = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getMarca());
            stmt.setString(2, produto.getDesc());
            stmt.setDouble(3, produto.getValor());
            stmt.setInt(4, produto.getId());
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

}
