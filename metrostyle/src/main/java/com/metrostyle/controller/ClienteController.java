package com.metrostyle.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.metrostyle.dao.ClienteDAO;
import com.metrostyle.models.Cliente;
import com.metrostyle.utils.ConnectionFactory;

@WebServlet(name = "clientes", urlPatterns = {"/clientes", "/clientes/novo", "/clientes/excluir", "/clientes/update", "/clientes/cadastrar"})
public class ClienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ClienteDAO clienteDAO;

    // Constantes para rotas
    private static final String ROTA_NOVO = "/clientes/novo";
    private static final String ROTA_EXCLUIR = "/clientes/excluir";
    private static final String ROTA_UPDATE = "/clientes/update";
    private static final String ROTA_CADASTRAR = "/clientes/cadastrar";

    public ClienteController() {
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processarRequisicao(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processarRequisicao(request, response);
    }

    private void processarRequisicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
	            case "/clientes":
	                listar(request, response);
	                break;
                case ROTA_NOVO:
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        salvar(request, response);
                    }
                    break;
                case ROTA_UPDATE:
                    atualizar(request, response);
                    break;
                case ROTA_EXCLUIR:
                    excluir(request, response);
                    break;
                case ROTA_CADASTRAR:
                    cadastrar(request, response);
                    break;
                default:
                    listar(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a requisição.");
        }
    }

    // Método para listar todos os clientes
    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<Cliente> listaClientes = clienteDAO.listar();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/man-clientes.jsp");
        dispatcher.forward(request, response);
    }

    // Método para salvar um novo cliente
    private void salvar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Cria e insere o cliente
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);

        clienteDAO.inserir(cliente);

        // Redireciona para a lista após salvar
        response.sendRedirect(request.getContextPath() + "/clientes");
    }

    // Método para salvar um novo cliente
    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        // Cria e insere o cliente
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);

        clienteDAO.inserir(cliente);

        // Redireciona para a lista após salvar
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }

    // Método para atualizar um cliente existente
    private void atualizar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");


        Cliente cliente = new Cliente();
        cliente.setId(id);
        cliente.setNome(nome);
        cliente.setEmail(email);
        cliente.setSenha(senha);

        clienteDAO.atualizar(cliente);

        response.sendRedirect(request.getContextPath() + "/clientes");
    }

    // Método para excluir um cliente
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (clienteDAO.excluir(id)) {
            response.sendRedirect(request.getContextPath() + "/clientes");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Erro ao excluir cliente.");
        }
    }

}
