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
import jakarta.servlet.http.HttpSession;

import com.metrostyle.dao.CarrinhoDAO;
import com.metrostyle.dao.ClienteDAO;
import com.metrostyle.models.Carrinho;
import com.metrostyle.models.Cliente;
import com.metrostyle.utils.ConnectionFactory;


@WebServlet(name = "carrinho", urlPatterns = {"/carrinho", "/carrinho/novo", "/carrinho/excluir"})
public class CarrinhoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CarrinhoDAO carrinhoDAO;

    // Constantes para rotas
    private static final String ROTA_NOVO = "/carrinho/novo";
    private static final String ROTA_EXCLUIR = "/carrinho/excluir";

    public CarrinhoController() {
        this.carrinhoDAO = new CarrinhoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processarRequisicao(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente clienteLogado = (Cliente) session.getAttribute("cliente");

        if (clienteLogado != null) {
            // Recupera o ID do produto enviado pelo formulário
            int idProduto = Integer.parseInt(request.getParameter("id_produto"));
            int idCliente = clienteLogado.getId();

            // Verifica ou cria um carrinho no banco de dados
            Carrinho carrinho = carrinhoDAO.obterCarrinhoPorCliente(idCliente);
            if (carrinho == null) {
                carrinho = carrinhoDAO.criarCarrinho(idCliente); // Crie um carrinho para o cliente, caso não exista
            }

            // Adiciona o item ao carrinho
            carrinhoDAO.adicionarItem(carrinho.getId_carrinho(), idProduto);

            // Redireciona para a página de listagem ou atualiza a página atual

            response.sendRedirect(request.getContextPath() + "/views/carrinho.jsp");

        } else {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        }
    }


    private void processarRequisicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
	            case "/carrinho":
	                listar(request, response);
	                break;
                case ROTA_NOVO:
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        salvar(request, response);
                    }
                    break;
                case ROTA_EXCLUIR:
                    excluir(request, response);
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
    
    // Método para listar os itens do carrinho do cliente logado
    protected void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");

        if (clienteLogado != null) {
            System.out.println("Cliente logado: " + clienteLogado.getNome());
            int idCliente = clienteLogado.getId();
            ArrayList<Carrinho> listaCarrinho = carrinhoDAO.listar(idCliente);

            request.setAttribute("listaCarrinho", listaCarrinho);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/carrinho.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("Nenhum cliente logado");
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        }
    }




    
 // Método para salvar um novo item no carrinho
    private void salvar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int idCarrinho = Integer.parseInt(request.getParameter("id_carrinho"));
        int idProduto = Integer.parseInt(request.getParameter("id_produto"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double precoUnitario = Double.parseDouble(request.getParameter("preco_unitario"));
        
        Carrinho itemCarrinho = new Carrinho();
        itemCarrinho.setId_carrinho(idCarrinho);
        itemCarrinho.setId_produto(idProduto);
        itemCarrinho.setQuantidade(quantidade);
        itemCarrinho.setPreco_unitario(precoUnitario);
        itemCarrinho.setSubtotal(quantidade * precoUnitario);

        carrinhoDAO.inserir(itemCarrinho);

        // Mensagem de sucesso
        request.getSession().setAttribute("mensagem", "Item adicionado ao carrinho com sucesso!");
        request.getSession().setAttribute("tipoMensagem", "sucesso");

        response.sendRedirect(request.getContextPath() + "/carrinho");
    }

    
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (carrinhoDAO.excluir(id)) {
            response.sendRedirect(request.getContextPath() + "/carrinho");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Erro ao excluir cliente.");
        }
    }
}
