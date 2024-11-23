package com.metrostyle.controller;

import java.io.IOException;
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

import com.metrostyle.dao.ProdutoDAO;
import com.metrostyle.models.Produto;

@WebServlet(name = "produtos", urlPatterns = {"/produtos", "/produtos/novo", "/produtos/editar", "/produtos/excluir", "/produtos/update"})
public class ProdutoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProdutoDAO produtoDAO;

    // Constantes para rotas
    private static final String ROTA_NOVO = "/produtos/novo";
    private static final String ROTA_EDITAR = "/produtos/editar";
    private static final String ROTA_EXCLUIR = "/produtos/excluir";
    private static final String ROTA_UPDATE = "/produtos/update";

    public ProdutoController() {
        this.produtoDAO = new ProdutoDAO();
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
                case "/produtos":
                    listar(request, response);
                    break;
                case ROTA_NOVO:
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        salvar(request, response);
                    }
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a requisição.");
        }
    }

    // Método para listar todos os produtos
    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<Produto> listaProdutos = produtoDAO.listar();
        request.setAttribute("listaProdutos", listaProdutos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/man-produtos.jsp");
        dispatcher.forward(request, response);
    }

    // Método para salvar um novo produto
    private void salvar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String marca = request.getParameter("marca");
        String descricao = request.getParameter("descricao");
        double valor = Double.parseDouble(request.getParameter("valor"));

        // Cria e insere o produto
        Produto produto = new Produto();
        produto.setMarca(marca);
        produto.setDesc(descricao);
        produto.setValor(valor);

        produtoDAO.inserir(produto);

        // Redireciona para a lista após salvar
        response.sendRedirect(request.getContextPath() + "/produtos");
    }
}
