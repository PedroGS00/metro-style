<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Metrô Style | Manutenção de Cliente</title>
		<link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/estruturaMan.css">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
	</head>
	<body>

		<% if (request.getAttribute("mensagem") != null) { %>
			<p><%= request.getAttribute("mensagem") %></p>
		<% } %>

		<div class="manutencao-abrir">
			<div>
				<div class="fechar-tela">
					<p onclick="fecharDiv()"><img src="${pageContext.request.contextPath}/imgs/x.png" alt=""></p>
				</div>
				 <h2>Cadastro de Cliente</h2> 

			<form action="${cliente == null ? 'novo' : 'update'}" method="post">
				
				<input type="hidden" name="id" value="${cliente.id_Cliente}">
					Nome: <input type="text" name="nome" value="${cliente.nome}"><br>
					Email: <input type="text" name="email" value="${cliente.email}"><br>
					Telefone: <input type="text" name="telefone" value="${cliente.telefone}"><br> 
					<input type="submit"value="Salvar"> 
					<input type="button" value="Cancelar" onclick="window.location.href='clientes/listar';" />
			</form>

				<h2>Lista de Clientes</h2>
				<form action="ClienteServlet" method="get">
					<input type="hidden" name="action" value="listar">
					<input type="submit" value="Listar Clientes">
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
						<a href="${pageContext.request.contextPath}/adm-manutencao.jsp">
						<img id="sair" src="${pageContext.request.contextPath}/imgs/logout.png" alt=""></a>
					</div>
				</div>
			</div>
		</div>

		<div class="direita">
			
			<header>
				<h1>MANUTENÇÃO DE CLIENTES</h1>
			</header>

			<main>
				<div class="content">
					<div class="cabecalho-lista">
						<h2>Lista de Clientes</h2>
						<button onclick="abrirDiv()">Cadastrar Cliente</button>
					</div>
					<thead>
			<tr>
				<th>ID</th>
				<th>Nome</th>
				<th>Email</th>
				<th>Telefone</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="clientes" items="${listaClientes}">
				<tr>
					<td>${cliente.id_Cliente}</td>
					<td>${cliente.nome}</td>
					<td>${cliente.email}</td>
					<td>${cliente.telefone}</td>
					<td>
					<a href="editar?id=${cliente.id_Cliente}">Editar</a> 
					<a href="excluir?id=${cliente.id_Cliente}">Excluir</a></td>
				</tr>
			</c:forEach>
		</tbody>
			</main>

			<jsp:include page="/includes/footer.jsp" />
		</div>
	</body>

	<script src="${pageContext.request.contextPath}/js/sobreTela.js"></script>
</html>
