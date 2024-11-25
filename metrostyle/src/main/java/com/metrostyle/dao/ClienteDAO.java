package com.metrostyle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.metrostyle.models.Cliente;
import com.metrostyle.models.Produto;
import com.metrostyle.utils.ConnectionFactory;

public class ClienteDAO {
	Connection conexao; 

	public boolean inserir(Cliente cliente) {
		boolean retorno = false;
		try {
			conexao = ConnectionFactory.getConnection();
			String sql = "INSERT INTO dbcliente (nome,email,telefone) values(?,?,?)";
			PreparedStatement ps = conexao.prepareStatement(sql);
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getUser());
			ps.setString(3, cliente.getSenha());
			int linhasAfetadas = ps.executeUpdate();
			if(linhasAfetadas >0) { retorno = true; }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			//código omitido}
		}
		return retorno;
	}
	
	public ArrayList<Cliente> listar() {
		ArrayList<Cliente> clientes = new ArrayList<>();
		String sql = "SELECT * FROM dbcliente";
		try (
		Connection connection = ConnectionFactory.getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement(sql))
		{
		ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
		int id = rs.getInt("id_cliente");
		String nome = rs.getString("nome");
		String user = rs.getString("user");
		String senha= rs.getString("senha");
		
		Cliente itemLista = new Cliente();
		itemLista.setId(id);
		itemLista.setNome(nome);
		itemLista.setUser(user);
		itemLista.setSenha(senha);
		clientes.add(itemLista);
		}
		}catch (SQLException e) {
		e.printStackTrace();
		}

		return clientes;
		}


}