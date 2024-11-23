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

}
