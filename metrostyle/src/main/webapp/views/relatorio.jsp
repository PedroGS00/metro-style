<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Relatórios de produtos </title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estruturaMan.css">
		<link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
		
	</head>

	<body>
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
				<h1>RELATÓRIO DE VENDAS</h1>
			</header>

			<main>
				<div class="content">
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

			<jsp:include page="/includes/footer.jsp" />
		</div>
	</body>
</html>