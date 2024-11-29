package com.metrostyle.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.metrostyle.models.Carrinho;
import com.metrostyle.utils.ConnectionFactory;

public class VendasDAO {

    private Connection connection;

    public VendasDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    public void adicionarItemVenda(Carrinho item, int idCliente) throws SQLException {
        String sql = "INSERT INTO tb_itens_venda (id_cliente, id_produto, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idCliente);
            stmt.setInt(2, item.getId_produto());
            stmt.setInt(3, item.getQuantidade());
            stmt.setDouble(4, item.getPreco_unitario());
            stmt.setDouble(5, item.getSubtotal());
            stmt.executeUpdate();
        }
    }
}
