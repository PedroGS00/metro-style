package com.metrostyle.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import com.metrostyle.dao.ClienteDAO;
import com.metrostyle.models.Cliente;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name="clientes", urlPatterns={"/clientes","/clientes/novo","/clientes/listar"})
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClienteDAO clienteDAO;
	public ClienteController() {
		super();
		clienteDAO = new ClienteDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/clientes/novo":
				novo(request, response);
				break;
			case "/clientes/listar":
				listar(request, response);
				break;
			default:
				listar(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getServletPath();
	    try {
	        switch (action) {
	            case "/clientes/novo":
	                inserir(request, response);
	                break;
	            case "/clientes/listar":
					listar(request, response);
					break;
	            default:
	                response.sendRedirect("listar");
	                break;
	        }
	    } catch (SQLException ex) {
	        throw new ServletException(ex);
	    }
	}
s
	private void inserir(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
	    String nome = request.getParameter("nome");
	    String email = request.getParameter("email");
	    String telefone = request.getParameter("telefone");

	    Cliente cliente = new Cliente();
	    cliente.setNome(nome);
	    cliente.setEmail(email);
	    cliente.setTelefone(telefone);

	    clienteDAO.inserir(cliente);
	    response.sendRedirect("listar");
	}

	private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException,
	IOException, ServletException {
		ArrayList<Cliente> listaClientes = clienteDAO.listar();
		request.setAttribute("listaClientes", listaClientes);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clientes/cliente-listar.jsp");
		dispatcher.forward(request, response);
	}
	
	private void novo(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/clientes/cliente-cadastro.jsp");
		dispatcher.forward(request, response);
	}

}