<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.dao.ProdutoDAO"%>
<%@ page import="com.metrostyle.models.Produto"%>
<%@ page import="com.metrostyle.dao.ClienteDAO"%>
<%@ page import="com.metrostyle.models.Cliente"%>
<%@ page import="com.metrostyle.dao.CarrinhoDAO"%>
<%@ page import="com.metrostyle.models.Carrinho"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Produtos</title>
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/produtos.css">
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/estruturaNav.css">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
        
    </head>

    <body>
    <%
    // Recupera os produtos do banco
    ProdutoDAO produtoDAO = new ProdutoDAO();
    List<Produto> produtos = produtoDAO.getAllProdutos(); // Supondo que você tenha um método para pegar todos os produtos
	%>   
	
    	<div class="sidebar">METRO STYLE</div>
    
		<div class="direita">
			<header>
				<jsp:include page="/includes/nav_bar.jsp" />
			</header>
			
			<main>
				<div class="container-tenis">
					<div class="mais-pop">
						<h1>Produtos</h1>
						<div class="lista-tenis">
							<%
							
							    // Verificar se o cliente está logado
							    Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");
							    int idCarrinho = 0;  // Valor default caso não encontre um carrinho
	
							    if (clienteLogado != null) {
							        // O carrinho é associado ao cliente logado
							        CarrinhoDAO carrinhoDAO = new CarrinhoDAO();
							        Carrinho carrinho = carrinhoDAO.obterCarrinhoPorCliente(clienteLogado.getId());
							        if (carrinho != null) {
							            idCarrinho = carrinho.getId_carrinho();  // Obtém o id do carrinho
							        }
							    }
							
								int contador = 0; // Inicializa um contador
								for (Produto produto : produtos) {
									if (contador >= 4) { 
										break; // Interrompe o loop após exibir 4 produtos
									}
							%>
							
									<div class="box-tenis">
										<%
											// Condicional para verificar o nome do produto e atribuir a imagem correspondente
											String imagemProduto = "";
											if ("Tênis Nike Air Force 1 07 Masculino".equals(produto.getNome())) {
												imagemProduto = "Air_Force_1.avif";
											} 
											else if ("Tênis Nike Air Max Dn Masculino".equals(produto.getNome())) {
												imagemProduto = "Air_Max_DN.png";
											} 
											else if ("Tênis Nike Air Jordan 4 Travis Scott".equals(produto.getNome())) {
												imagemProduto = "Air_Jordan_4_Travis_Scott.png";
											} 
											else if ("Tênis New Balance 1000".equals(produto.getNome())) {
												imagemProduto = "New_Balance_1000.jpeg";
											} 
											else {
												imagemProduto = "default.png"; // Para outros produtos, usa uma imagem padrão
											}
										%>
										
										<!-- Exibe a imagem do produto -->
										<img src="${pageContext.request.contextPath}/imgs/<%= imagemProduto %>" alt="<%= produto.getNome() %>">
										
										<div class="box-tenis-desc">
											<p id="nome"><%= produto.getNome() %></p>
											<p id="modelo">Casual</p>
											<p id="preco">R$ <%= produto.getPreco() %></p>
										</div>
										
										<div class="box-tenis-comprar">
											<form action="${pageContext.request.contextPath}/carrinho/novo" method="post">
											    <input type="hidden" name="id_carrinho" value="<%= idCarrinho %>">
											    <input type="hidden" name="id_produto" value="<%= produto.getId() %>">
											    <input type="hidden" name="quantidade" value="1"> <!-- Defina uma quantidade padrão ou obtenha ela dinamicamente -->
											    <input type="hidden" name="preco_unitario" value="<%= produto.getPreco() %>">
											    <button type="submit">
											        ADICIONAR
											        <img src="${pageContext.request.contextPath}/imgs/carrinho1.png" alt="">
											    </button>
											</form>
										</div>
									</div>
									<%
										String mensagem = (String) session.getAttribute("mensagem");
								    	if (mensagem != null) {
									%>
									<script>
							        alert('<%= mensagem %>');
							    	</script>
							    	<%
							    	session.removeAttribute("mensagem");
								        }
							    	%>
							<%
									// Incrementa o contador
									contador++;
								}
							%>
						</div> <!-- Fecha a última lista -->
					</div>
				</div>
			</main>
		<jsp:include page="/includes/footer.jsp" />		
		</div>
    </body>
</html>