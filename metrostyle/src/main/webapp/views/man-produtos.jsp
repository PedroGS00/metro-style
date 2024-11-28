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

	<div class="sobreTela-abrir">
		<div class="sobreTela-abrir-cadastro">
			<div class="fechar-tela-cad">
				<p><img src="${pageContext.request.contextPath}/imgs/x.png" alt="" onclick="fecharDivCAD()"></p>
			</div>
			<div class="sobreTela-form">
				<form action="${pageContext.request.contextPath}/produtos/novo" method="post"> 
					<input type="hidden" name="id" value="${produto.getId()}"> 
			
					<label for="nome">Nome:</label><br> 
					<input type="text" name="nome" value="${produto.getMarca()}">
					<br>
					
					<label for="descricao">Descrição:</label><br> 
					<textarea name="descricao" id="descricao-cad" rows="6" cols="50" wrap="soft"></textarea>
					<br>
					
					<label for="preco">Preço:</label><br> 
					<input type="text" name="preco" value="${produto.getPreco() != null? produto.getPreco() : 0}">
					<br>

					<label for="estoque">Estoque:</label><br> 
					<input type="text" name="estoque" value="${produto.getEstoque() != null? produto.getEstoque() : 0}">
					<br><br>
					
					<input type="submit" value="Cadastrar">
				</form>
			</div>
		</div>
	</div>

	<div class="sobreTela-abrir">
		<div class="sobreTela-abrir-editar">
			<div class="fechar-tela-edit">
				<p><img src="${pageContext.request.contextPath}/imgs/x.png" alt="" onclick="fecharDivEDIT()"></p>
			</div>

			<div class="sobreTela-form">
				<form action="${pageContext.request.contextPath}/produtos/update" method="post"> 
					<input id="id_produto" type="hidden" name="id">

					<label for="nome">Nome:</label><br> 
					<input id="nome" type="text" name="nome">
					<br>

					<label for="descricao">Descrição:</label><br> 
					<textarea name="descricao" id="descricao" rows="6" cols="50" wrap="soft"></textarea>
					<br>

					<label for="preco">Preço:</label><br> 
					<input id="preco" type="text" name="preco">
					<br>

					<label for="estoque">Estoque:</label><br> 
					<input id="estoque" type="text" name="estoque">
					<br><br>

					<input type="submit" value="Salvar">  
				</form>
			</div>

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
					<button class="btn-cadastro" onclick="abrirDivCAD()">Cadastrar Produto</button>
				</div>
		        <table class="content-tabela" border="1" cellspacing="0" cellpadding="5">
					<thead>
						<tr>
							<th>Id do Produto</th>
							<th>Nome</th>
							<th>Descrição</th>
							<th>Preço</th>
							<th>Estoque</th>
							<th>Ações</th>
						</tr>
					</thead>
		            <tbody>
						<!-- Exemplo -->
						<%-- <tr>
							<td>1</td>
							<td>Tênis Casual AirMax 90</td>
							<td>Tênis especifico para jogos!</td>
							<td>R$ 789,90</td>
							<td>50</td>
							<td> 
								<button class="btn-itens">Editar</button>

								<button id="excluir" class="btn-itens">Excluir</button>
							</td>
						</tr> --%>
		                <c:forEach var="produto" items="${listaProdutos}">
		                    <tr>
		                        <td>${produto.id}</td>
		                        <td>${produto.nome}</td>
		                        <td>${produto.desc}</td>
		                        <td>${produto.preco}</td>
		                        <td>${produto.estoque}</td>
		                        <td> 
								    <button class="btn-itens" onclick="abrirDivEDIT_Prod(${produto.id}, '${produto.nome}', '${produto.desc}', ${produto.preco}, '${produto.estoque}')">Editar</button>

								    <a href="${pageContext.request.contextPath}/produtos/excluir?id=${produto.id}"><button id="excluir" class="btn-itens" onclick="return confirm('Tem certeza que deseja excluir este produto?');">Excluir</button></a>
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
