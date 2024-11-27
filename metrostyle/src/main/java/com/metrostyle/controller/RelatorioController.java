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

import com.metrostyle.dao.RelatorioDAO;
import com.metrostyle.models.Produto;
import com.metrostyle.models.Relatorio;
import com.metrostyle.utils.ConnectionFactory;

@WebServlet(name = "relatorios", urlPatterns = {"/relatorios", "/relatorios/novo"})

public class RelatorioController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private RelatorioDAO relatorioDAO;

    // Constantes para rotas
    private static final String ROTA_NOVO = "/relatorios/novo";

    public RelatorioController() {
        this.relatorioDAO = new RelatorioDAO();
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
                case "/relatorios":
                    listar(request, response);
                    break;
                case ROTA_NOVO:
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        salvar(request, response);
                    }
                    break;
                default:
                    listar(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RelatorioController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a requisição.");
        }
    }

    // Método para listar todos os relatorio
    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<Relatorio> listaRelatorio = relatorioDAO.listar();
        request.setAttribute("listaRelatorio", listaRelatorio);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/relatorio.jsp");
        dispatcher.forward(request, response);
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {

        // Captura os dados do request
        int id_cliente = Integer.parseInt(request.getParameter("id_cliente"));
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        String data_venda = request.getParameter("data_venda");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double preco_unitario = Double.parseDouble(request.getParameter("preco_unitario"));
        double subtotal = Double.parseDouble(request.getParameter("subtotal"));
        double valor_total = Double.parseDouble(request.getParameter("valor_total"));

        // Cria e insere o relatório
        Relatorio relatorio = new Relatorio();
        relatorio.setId_cliente(id_cliente);
        relatorio.setId_produto(id_produto);
        relatorio.setData_venda(data_venda);
        relatorio.setQuantidade(quantidade);
        relatorio.setPreco_unitario(preco_unitario);
        relatorio.setSubtotal(subtotal);
        relatorio.setValor_total(valor_total);

        // Salva no banco de dados
        relatorioDAO.inserir(relatorio);

        // Redireciona para a lista após salvar
        response.sendRedirect(request.getContextPath() + "/relatorios");
    }
}
