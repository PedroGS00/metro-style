<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Metrô Style | Manutenção de Cliente</title>
		<link rel="stylesheet" type="text/css"  href="../css/estruturaMan.css">
        <link rel="shortcut icon" type="imagex/png" href="../imgs/icon.png">
	</head>
	<body>

		<% if (request.getAttribute("mensagem") != null) { %>
			<p><%= request.getAttribute("mensagem") %></p>
		<% } %>

		<div class="manutencao-abrir">
			<div>
				<div class="fechar-tela">
					<p onclick="fecharDiv()"><img src="../imgs/x.png" alt=""></p>
				</div>
				 <h2>Cadastro de Cliente</h2> 

				<form action="ClienteServlet" method="post">
					<input type="hidden" name="action" value="incluir">
					Nome: <input type="text" name="nome" required><br>
					Email: <input type="email" name="email" required><br>
					Telefone: <input type="text" name="telefone" required><br>
					Endereço: <input type="text" name="endereco" required><br> 
					<input type="submit" value="Cadastrar">
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
				<h1>MANUTENÇÃO DE CLIENTES</h1>
			</header>

			<main>
				<div class="cabecalho">
					<div class="cabecalho-lista">
						<h2>Lista de Clientes</h2>
						<button onclick="abrirDiv()">Cadastrar Cliente</button>
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

			<jsp:include page="/includes/footer.jsp" />
		</div>
	</body>

	<script src="../js/sobreTela.js"></script>
</html>
