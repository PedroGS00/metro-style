<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manutenção de Produtos</title>
</head>
<body>

	<h1>Manutenção de Produtos</h1>

	<!-- Exibir mensagem de sucesso ou erro -->
	<% if (request.getAttribute("mensagem") != null) { %>
	<p style="color: green;"><%= request.getAttribute("mensagem") %></p>
	<% } %>

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

</body>
</html>
