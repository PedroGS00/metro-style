<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.dao.ProdutoDAO"%>
<%@ page import="com.metrostyle.models.Produto"%>
<%@ page import="com.metrostyle.dao.ClienteDAO"%>
<%@ page import="com.metrostyle.models.Cliente"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Ínicio</title>
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/index.css">
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
						<h1>Mais Populares</h1>
						<div class="lista-tenis">
							<%
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
											else if ("Tênis New Balance 1000 Masculino".equals(produto.getNome())) {
												imagemProduto = "New_Balance_1000.jpeg";
											} 
											else {
												imagemProduto = "default.png"; // Para outros produtos, você pode usar uma imagem padrão
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
											<form action="comprar" method="post">
												<input type="hidden" name="id_produto" value="<%= produto.getId() %>">
												<button type="submit">
													ADICIONAR 
													<img src="${pageContext.request.contextPath}/imgs/carrinho1.png" alt=""> 
												</button>
											</form>
										</div>
									</div>
							<%
									contador++; // Incrementa o contador
								}
							%>

						</div>
					</div>
				</div>
			</main>
	
			<jsp:include page="/includes/footer.jsp" />
		</div>
    </body>
</html>