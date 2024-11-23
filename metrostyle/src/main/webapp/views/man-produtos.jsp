<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Metrô Style | Manutenção de Produtos</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estruturaMan.css">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
	</head>
<body>

	 <% if (request.getAttribute("mensagem") != null) { %>
	<p style="color: green;"><%= request.getAttribute("mensagem") %></p>
	<% } %> 

	<div class="manutencao-abrir">
		<div class="manutencao-abrir-content">
			<div class="fechar-tela">
				<p><img src="${pageContext.request.contextPath}/imgs/x.png" alt="" onclick="fecharDiv()"></p>
			</div>

		    <form action="${produto == null ? 'produtos/novo' : 'update'}" method="post"> 
		        <input type="hidden" name="id" value="${produto.getId()}"> 
		
		       	<label for="marca">Marca:</label><br> 
		        <input type="text" name="marca" value="${produto.getMarca()}">
		        <br>
		        
		        <label for="descricao">Descrição:</label><br> 
		        <input type="text" name="descricao" value="${produto.getDesc()}">
		        <br>
		        
		        <label for="nome">Valor:</label><br> 
		        <input type="text" name="valor" value="${produto.getValor() != null? produto.getValor() : 0}">
		        <br><br>
		        
		        <input type="submit" value="Salvar"> 
    		</form>
		</div>
	</div>

	<div class="sidebar">
		<div class="header-sidebar">
			<a href="${pageContext.request.contextPath}/index.jsp"><h2>Metrô Style</h2></a>
			<div class="navbar">
				<div class="navbar-itens">
					<div class="user">
						<img id="user-img" src="${pageContext.request.contextPath}/imgs/user.png" alt="">
						<p>Administrador</p>
					</div>
					<div class="navbar-itens-f">
						<a href="${pageContext.request.contextPath}/index.jsp">HOME</a>
						<a href="${pageContext.request.contextPath}/views/produtos.jsp">PRODUTOS</a>
						<a href="${pageContext.request.contextPath}/views/sobre.jsp">SOBRE</a>
						<a href="${pageContext.request.contextPath}/views/contato.jsp">CONTATO</a>
					</div>
					<a href="${pageContext.request.contextPath}/adm-manutencao.jsp"><img id="sair" src="${pageContext.request.contextPath}/imgs/logout.png" alt=""></a>
				</div>
			</div>
		</div>
	</div>

	<div class="direita">
		
		<header>
			<h1>MANUTENÇÃO DE PRODUTOS</h1>
		</header>

		<main>
			<div class="content">
				<div class="cabecalho-lista">
					<h2>Lista de Produtos</h2>
					<button class="btn-cadastro" onclick="abrirDiv()">Cadastrar Produto</button>
				</div>
				<div class="cabecalho-itens">
					<p>Id do Produto</p>
					<p>Marca</p>
					<p>Descrição</p>
					<p>Valor</p>
					<p>Ações</p>
				</div>
		        <table border="1" cellspacing="0" cellpadding="5">
		            <tbody>
		                <c:forEach var="produto" items="${listaProdutos}">
		                    <tr>
		                        <td>${produto.id}</td>
		                        <td>${produto.marca}</td>
		                        <td>${produto.desc}</td>
		                        <td>${produto.valor}</td>
		                        <td> 
								    <a href="editar?id=${produto.id}">Editar</a>
								    <a href="excluir?id=${produto.id}" onclick="return confirm('Tem certeza que deseja excluir este produto?');">Excluir</a>
								</td>
		                    </tr>
		                </c:forEach>
		            </tbody>
		        </table> 
			</div>
		</main>

		<jsp:include page="/includes/footer.jsp" />
	</div>
</body>

<script src="${pageContext.request.contextPath}/js/sobreTela.js"></script>

</html>
