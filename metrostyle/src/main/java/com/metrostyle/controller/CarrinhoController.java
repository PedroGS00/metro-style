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
import com.metrostyle.dao.VendasDAO;
import com.metrostyle.models.Carrinho;
import com.metrostyle.models.Cliente;
import com.metrostyle.utils.ConnectionFactory;


@WebServlet(name = "carrinho", urlPatterns = {"/carrinho", "/carrinho/novo", "/carrinho/excluir", "/carrinho/comprar"})
public class CarrinhoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private CarrinhoDAO carrinhoDAO;

    // Constantes para rotas
    private static final String ROTA_NOVO = "/carrinho/novo";
    private static final String ROTA_EXCLUIR = "/carrinho/excluir";
    private static final String ROTA_COMPRAR = "/carrinho/comprar";

    public CarrinhoController() {
        this.carrinhoDAO = new CarrinhoDAO();
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
                case ROTA_COMPRAR:
                    if ("POST".equalsIgnoreCase(request.getMethod())) {
                        comprar(request, response);
                    }
                    break;
                default:
                    listar(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CarrinhoController.class.getName()).log(Level.SEVERE, null, ex);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a requisição.");
        }
    }

    // Método para listar os itens do carrinho do cliente logado
    private void listar(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");

        if (clienteLogado != null) {
            int idCliente = clienteLogado.getId();
            ArrayList<Carrinho> listaCarrinho = carrinhoDAO.listar(idCliente);
            request.setAttribute("listaCarrinho", listaCarrinho);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/carrinho.jsp");
            dispatcher.forward(request, response);
        } else {
            // Se o cliente não estiver logado, redireciona para login
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        }
    }

    private void salvar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        try {
            // Verifica se o usuário está logado
            Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");

            if (clienteLogado == null) {
                response.sendRedirect(request.getContextPath() + "/views/login.jsp");
                return; // Interrompe o processamento caso o usuário não esteja logado
            }

            // Verifique se os parâmetros são nulos ou vazios antes de convertê-los
            String idProdutoParam = request.getParameter("id_produto");
            String quantidadeParam = request.getParameter("quantidade");
            String precoUnitarioParam = request.getParameter("preco_unitario");

            // Verificação de nullidade
            if (idProdutoParam == null || quantidadeParam == null || precoUnitarioParam == null) {
                throw new IllegalArgumentException("Todos os parâmetros são obrigatórios.");
            }

            int idProduto = Integer.parseInt(idProdutoParam);
            int quantidade = Integer.parseInt(quantidadeParam);
            double precoUnitario = Double.parseDouble(precoUnitarioParam);

            // Recupera o cliente logado
            int idCliente = clienteLogado.getId();
            
            // Verifica se o cliente já tem um carrinho
            int idCarrinho = carrinhoDAO.getCarrinhoIdByCliente(idCliente); // Supondo que esse método existe

            if (idCarrinho == 0) {
                // Se não houver carrinho, cria um novo carrinho para o cliente
                idCarrinho = carrinhoDAO.criarCarrinho(idCliente); // Método que cria um novo carrinho no banco
            }

            // Cria o item para o carrinho
            Carrinho itemCarrinho = new Carrinho();
            itemCarrinho.setId_carrinho(idCarrinho);
            itemCarrinho.setId_produto(idProduto);
            itemCarrinho.setQuantidade(quantidade);
            itemCarrinho.setPreco_unitario(precoUnitario);
            itemCarrinho.setSubtotal(quantidade * precoUnitario);

            // Tenta salvar o item no carrinho
            carrinhoDAO.inserir(itemCarrinho);

            // Mensagem de sucesso
            request.getSession().setAttribute("mensagem", "Item adicionado ao carrinho com sucesso!");
            request.getSession().setAttribute("tipoMensagem", "sucesso");

            // Obtém o URL da página anterior
            String referer = request.getHeader("Referer"); 

            // Redireciona para produtos.jsp se a página anterior for produtos.jsp
            if (referer != null && referer.contains("produtos.jsp")) {
                response.sendRedirect(request.getContextPath() + "/views/produtos.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/index.jsp"); // Redireciona para a página inicial se não for produtos.jsp
            }

        } catch (NumberFormatException e) {
            // Loga o erro e exibe uma mensagem adequada
            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao processar os parâmetros. Certifique-se de que todos os campos estão preenchidos corretamente.");
            request.getSession().setAttribute("tipoMensagem", "erro");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } catch (IllegalArgumentException e) {
            // Exibe um erro se algum parâmetro estiver ausente
            e.printStackTrace();
            request.getSession().setAttribute("mensagem", e.getMessage());
            request.getSession().setAttribute("tipoMensagem", "erro");
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }



    private void excluir(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String idParam = request.getParameter("id_item_carrinho");  // Corrigido para o nome correto do parâmetro

        if (idParam != null && !idParam.isEmpty()) {
            try {
                // Tenta converter o parâmetro para inteiro
                int id = Integer.parseInt(idParam);

                if (carrinhoDAO.excluir(id)) {
                    response.sendRedirect(request.getContextPath() + "/carrinho");
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Erro ao excluir cliente.");
                }
            } catch (NumberFormatException e) {
                // Caso a conversão para inteiro falhe
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID inválido.");
            }
        } else {
            // Caso o parâmetro 'id_item_carrinho' não seja enviado ou esteja vazio
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID não fornecido.");
        }
    }

    // Método para processar a compra
    private void comprar(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Recupera o cliente logado da sessão
        HttpSession session = request.getSession();
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");

        if (clienteLogado == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        int idCliente = clienteLogado.getId();

        try (Connection conn = ConnectionFactory.getConnection()) {
            conn.setAutoCommit(false); // Inicia transação

            try {
                // Passo 1: Criar uma venda
                String sqlInsertVenda = "INSERT INTO tb_Vendas (id_cliente, data_venda, valor_total) VALUES (?, NOW(), ?)";
                PreparedStatement psVenda = conn.prepareStatement(sqlInsertVenda, PreparedStatement.RETURN_GENERATED_KEYS);
                double valorTotal = 0.0;

                // Recupera os itens do carrinho
                ArrayList<Carrinho> listaCarrinho = carrinhoDAO.listar(idCliente);
                if (listaCarrinho.isEmpty()) {
                    throw new IllegalArgumentException("O carrinho está vazio.");
                }

                // Calcula o valor total da venda
                for (Carrinho item : listaCarrinho) {
                    valorTotal += item.getSubtotal();
                }

                psVenda.setInt(1, idCliente);
                psVenda.setDouble(2, valorTotal);
                psVenda.executeUpdate();

                // Recupera o ID da venda recém-criada
                ResultSet rsVenda = psVenda.getGeneratedKeys();
                int idVenda = 0;
                if (rsVenda.next()) {
                    idVenda = rsVenda.getInt(1);
                } else {
                    throw new SQLException("Erro ao inserir venda, ID não gerado.");
                }

                // Passo 2: Inserir os itens na tabela de itens de venda
                String sqlInsertItensVenda = "INSERT INTO tb_Itens_Venda (id_venda, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)";
                PreparedStatement psItensVenda = conn.prepareStatement(sqlInsertItensVenda);

                for (Carrinho item : listaCarrinho) {
                    psItensVenda.setInt(1, idVenda);
                    psItensVenda.setInt(2, item.getId_produto());
                    psItensVenda.setInt(3, item.getQuantidade());
                    psItensVenda.setDouble(4, item.getPreco_unitario());
                    psItensVenda.addBatch();
                }

                psItensVenda.executeBatch();

                // Passo 3: Limpar os itens do carrinho
                String sqlDeleteCarrinhoItens = "DELETE FROM tb_Carrinho_Itens WHERE id_carrinho = (SELECT id_carrinho FROM tb_Carrinho WHERE id_cliente = ?)";
                PreparedStatement psLimparCarrinho = conn.prepareStatement(sqlDeleteCarrinhoItens);
                psLimparCarrinho.setInt(1, idCliente);
                psLimparCarrinho.executeUpdate();

                conn.commit(); // Confirma a transação

                // Redireciona para a página de sucesso
                response.sendRedirect(request.getContextPath() + "/views/sucesso.jsp");

            } catch (Exception e) {
                conn.rollback(); // Reverte a transação em caso de erro
                e.printStackTrace();
                throw new SQLException("Erro ao processar a compra.", e);
            }
        }
    }

}
