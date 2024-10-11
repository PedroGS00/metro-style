<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Metrô Style | Manutenção de Produtos</title>
		<link rel="stylesheet" href="../css/estruturaMan.css">
	</head>
<body>

	<!-- <h1>Manutenção de Produtos</h1> -->

	<!-- Exibir mensagem de sucesso ou erro -->
	<!-- <% if (request.getAttribute("mensagem") != null) { %>
	<p style="color: green;"><%= request.getAttribute("mensagem") %></p>
	<% } %> -->

	<div class="manutencao-abrir">
		<div>
			<div class="fechar-tela">
				<p onclick="fecharDiv()"><img src="../imgs/x.png" alt=""></p>
			</div>
			<!-- Formulário de Cadastro/Alteração de Produtos -->
			<form action="ProdutoServlet" method="post">
				<label for="nome">Nome do Produto:</label> <input type="text"
					id="nome" name="nome" value="${produto.nome}" required /> <label
					for="descricao">Descrição:</label>
				<textarea id="descricao" name="descricao" required>${produto.descricao}</textarea>

				<label for="preco">Preço:</label> <input type="number" id="preco"
					name="preco" step="0.01" value="${produto.preco}" required /> <input
					type="submit" value="Salvar Produto" />
			</form>

			<!-- Tabela de Produtos Cadastrados -->
			<h2>Lista de Produtos</h2>
			<table border="1">
				<thead>
					<tr>
						<th>Nome</th>
						<th>Descrição</th>
						<th>Preço</th>
						<th>Ações</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="produto" items="${produtos}">
						<tr>
							<td>${produto.nome}</td>
							<td>${produto.descricao}</td>
							<td>${produto.preco}</td>
							<td><a href="ProdutoServlet?action=editar&id=${produto.id}">Alterar</a>
								<a href="ProdutoServlet?action=excluir&id=${produto.id}"
								onclick="return confirm('Tem certeza que deseja excluir este produto?');">Excluir</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div class="sidebar">
		<div class="header-sidebar">
			<a href="../index.jsp"><h2>Metrô Style</h2></a>
			<div class="navbar">
				<div class="navbar-itens">
					<div class="user">
						<img id="user-img" src="../imgs/user.png" alt="">
						<p>Administrador</p>
					</div>
					<div class="navbar-itens-f">
						<a href="../index.jsp">HOME</a>
						<a href="./produtos.jsp">PRODUTOS</a>
						<a href="./sobre.jsp">SOBRE</a>
						<a href="./contato.jsp">CONTATO</a>
					</div>
					<a href="../adm-manutencao.jsp"><img id="sair" src="../imgs/logout.png" alt=""></a>
				</div>
			</div>
		</div>
	</div>

	<div class="direita">
		
		<header>
			<h1>MANUTENÇÃO DE PRODUTOS</h1>
		</header>

		<main>
			<div class="cabecalho">
				<div class="cabecalho-lista">
					<h2>Lista de Produtos</h2>
					<button class="btn-cadastro" onclick="abrirDiv()">Cadastrar Produto</button>
				</div>
				<div class="cabecalho-itens">
					<p>Nome</p>
					<p>E-mail</p>
					<p>Telefone</p>
					<p>Número da Inscrição</p>
					<p>Data de Emissão</p>
				</div>
			</div>
		</main>

		<footer>
			<p>&copy; 2024 - METRO STYLE</p>
		</footer>
	</div>
</body>

<script src="../js/telaCadastro.js"></script>

</html>
