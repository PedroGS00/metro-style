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

import com.metrostyle.dao.ProdutoDAO;
import com.metrostyle.models.Produto;
import com.metrostyle.utils.ConnectionFactory;

@WebServlet(name = "produtos", urlPatterns = {"/produtos", "/produtos/novo", "/produtos/excluir", "/produtos/update"})
public class ProdutoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ProdutoDAO produtoDAO;

    // Constantes para rotas
    private static final String ROTA_NOVO = "/produtos/novo";
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
                case ROTA_UPDATE:
                    atualizar(request, response);
                    break;
                case ROTA_EXCLUIR:
                    excluir(request, response);
                    break;
                default:
                    listar(request, response);
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
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int estoque = Integer.parseInt(request.getParameter("estoque"));

        // Cria e insere o produto
        Produto produto = new Produto();
        produto.setNome(nome);
        produto.setDesc(descricao);
        produto.setPreco(preco);
        produto.setEstoque(estoque);

        produtoDAO.inserir(produto);

        // Redireciona para a lista após salvar
        response.sendRedirect(request.getContextPath() + "/produtos");
    }

    // Método para buscar um produto pelo ID
    public Produto buscarPorId(int id) throws SQLException {
        Produto produto = null;
        String sql = "SELECT * FROM tb_produtos WHERE id_produto = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id_produto"));
                produto.setNome(rs.getString("nome"));
                produto.setDesc(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setEstoque(rs.getInt("estoque"));
            }
        }
        return produto; // Retorna null se não encontrar o produto
    }

    // Método para atualizar um produto existente
    private void atualizar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int estoque = Integer.parseInt(request.getParameter("estoque"));

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(nome);
        produto.setDesc(descricao);
        produto.setPreco(preco);
        produto.setEstoque(estoque);
        
        produtoDAO.atualizar(produto);

        response.sendRedirect(request.getContextPath() + "/produtos");
    }

    // Método para excluir um produto
    private void excluir(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        if (produtoDAO.excluir(id)) {
            response.sendRedirect(request.getContextPath() + "/produtos");
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Erro ao excluir produto.");
        }
    }

}
