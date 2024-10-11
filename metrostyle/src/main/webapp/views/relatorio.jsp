<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Relatórios de produtos </title>
		<link rel="stylesheet" href="../css/estruturaMan.css">
	</head>

	<body>
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
				<h1>RELATÓRIO DE VENDAS</h1>
			</header>

			<main>
				<div class="cabecalho">
					<div class="cabecalho-lista">
						<h2>Lista de Vendas</h2>
					</div>
					<div class="cabecalho-itens">
						<p>Cliente</p>
						<p>Produto</p>
						<p>Quantidade</p>
						<p>Valor da Unidade</p>
						<p>Valor Total</p>
						<p>Data de Emissão</p>
					</div>
				</div>
			</main>

			<footer>
				<p>&copy; 2024 - METRO STYLE</p>
			</footer>
		</div>
	</body>
</html>