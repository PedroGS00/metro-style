<<<<<<< HEAD
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
                    comprar(request, response);  // Adiciona a lógica de compra
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
            // Verifica se o usuário está logado, se não redireciona para o login
            Cliente clienteLogado = (Cliente) request.getSession().getAttribute("clienteLogado");

            if (clienteLogado == null) {
                response.sendRedirect(request.getContextPath() + "/views/login.jsp");
                return; // Interrompe o processamento caso o usuário não esteja logado
            }

            // Verifique se os parâmetros são nulos ou vazios antes de convertê-los
            String idCarrinhoParam = request.getParameter("id_carrinho");
            String idProdutoParam = request.getParameter("id_produto");
            String quantidadeParam = request.getParameter("quantidade");
            String precoUnitarioParam = request.getParameter("preco_unitario");

            // Verificação de nullidade
            if (idCarrinhoParam == null || idProdutoParam == null || quantidadeParam == null || precoUnitarioParam == null) {
                throw new IllegalArgumentException("Todos os parâmetros são obrigatórios.");
            }

            int idCarrinho = Integer.parseInt(idCarrinhoParam);
            int idProduto = Integer.parseInt(idProdutoParam);
            int quantidade = Integer.parseInt(quantidadeParam);
            double precoUnitario = Double.parseDouble(precoUnitarioParam);

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

            // Redireciona para a página do carrinho
            response.sendRedirect(request.getContextPath() + "/index.jsp");
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
        HttpSession session = request.getSession();
        Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");

        if (clienteLogado != null) {
            // Lógica de compra - por exemplo, gerar um pedido, calcular total, etc.
            // Você pode acessar os itens do carrinho e realizar o processo de compra aqui.

            // Supondo que a compra seja realizada com sucesso, redireciona para uma página de sucesso.
            response.sendRedirect(request.getContextPath() + "/compra/sucesso.jsp");
        } else {
            // Se o cliente não estiver logado, redireciona para login
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        }
    }
}
=======
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
>>>>>>> cf2de09a621f3afafc07e17047500b48701666de
