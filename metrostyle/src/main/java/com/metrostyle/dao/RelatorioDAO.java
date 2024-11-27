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
	
    public ArrayList<Relatorio> listar() { 
        ArrayList<Relatorio> relatorios = new ArrayList<>(); 
        String sql = "SELECT * FROM tb_vendas"; 
        try (Connection connection = ConnectionFactory.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) { 
            ResultSet rs = preparedStatement.executeQuery(); 
            while (rs.next()) { 
                int id_venda = rs.getInt("id_venda");
                int id_cliente = rs.getInt("id_cliente");
                int id_produto = rs.getInt("id_produto");
                String data_venda = rs.getString("data_venda");
                int quantidade = rs.getInt("quantidade");
                double preco_unitario = rs.getDouble("preco_unitario");
                double subtotal = rs.getDouble("subtotal");
                double valor_total = rs.getDouble("valor_total");


                Relatorio itemRelatorio = new Relatorio();
                itemRelatorio.setId_venda(id_venda);
                itemRelatorio.setId_cliente(id_cliente);
                itemRelatorio.setId_produto(id_produto);
                itemRelatorio.setData_venda(data_venda);
                itemRelatorio.setQuantidade(quantidade);
                itemRelatorio.setPreco_unitario(preco_unitario);
                itemRelatorio.setSubtotal(subtotal);
                itemRelatorio.setValor_total(valor_total);

                relatorios.add(itemRelatorio);

            } 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } 
        return relatorios; 
    }

    public boolean inserir(Relatorio relatorio) { 
        boolean retorno = false; 
        try { 
            conexao = ConnectionFactory.getConnection();
            String sql = "INSERT INTO tb_vendas (id_cliente, id_produto, data_venda, quantidade, preco_unitario, subtotal, valor_total) values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
        
            ps.setInt(1, relatorio.getId_cliente());
            ps.setInt(2, relatorio.getId_produto());
            ps.setString(3, relatorio.getData_venda());
            ps.setInt(4, relatorio.getQuantidade());
            ps.setDouble(5, relatorio.getPreco_unitario());
            ps.setDouble(6, relatorio.getSubtotal());
            ps.setDouble(7, relatorio.getValor_total());
        
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
