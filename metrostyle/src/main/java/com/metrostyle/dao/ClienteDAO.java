package com.metrostyle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metrostyle.models.Cliente;
import com.metrostyle.utils.ConnectionFactory;

public class ClienteDAO {
	Connection conexao; 

	public boolean inserir(Cliente cliente) {
		boolean retorno = false;
		try {
			conexao = ConnectionFactory.getConnection();
			String sql = "INSERT INTO tb_clientes (nome,email,senha) values(?,?,?)";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEmail());
			ps.setString(3, cliente.getSenha());
			int linhasAfetadas = ps.executeUpdate();
			if(linhasAfetadas >0) { retorno = true; }
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} 
		finally { 
			//código omitido}
		}
		return retorno;
	}
	
	public ArrayList<Cliente> listar() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM tb_clientes";
		try (
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
			ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				int id = rs.getInt("id_cliente");
				String nome = rs.getString("nome");
				String email = rs.getString("email");
				String senha= rs.getString("senha");
				
				Cliente itemLista = new Cliente();
				itemLista.setId(id);
				itemLista.setNome(nome);
				itemLista.setEmail(email);
				itemLista.setSenha(senha);
				clientes.add(itemLista);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		return clientes;
	}
	
	// Método para atualizar um cliente
    public boolean atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE tb_clientes SET nome = ?, email = ?, senha = ? WHERE id_cliente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setInt(4, cliente.getId());
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se a atualização for bem-sucedida
        }
    }

    // Método para excluir um cliente
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM tb_clientes WHERE id_cliente = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0; // Retorna true se a exclusão for bem-sucedida
        }
    }

	public Cliente autenticar(String email, String senha) {
		Cliente cliente = null;
		String sql = "SELECT * FROM tb_clientes WHERE email = ? AND senha = ?";
		try (Connection conn = ConnectionFactory.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, senha);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					cliente = new Cliente();
					cliente.setId(rs.getInt("id_cliente"));
					cliente.setNome(rs.getString("nome"));
					cliente.setEmail(rs.getString("email"));
					cliente.setSenha(rs.getString("senha"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente; 
	}	

}