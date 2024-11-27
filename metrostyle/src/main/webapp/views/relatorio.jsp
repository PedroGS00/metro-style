<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Metrô Style | Relatórios </title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estruturaMan.css">
		<link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
		
	</head>

	<body>
		<div class="manutencao-abrir">
			<div class="manutencao-abrir-cadastro">
				<div class="fechar-tela-cad">
					<p><img src="${pageContext.request.contextPath}/imgs/x.png" alt="" onclick="fecharDivCAD()"></p>
				</div>

				<div class="manutencao-form">
					<form method="post"> 				
						<label for="nome">Nome:</label><br> 
						<input type="text" name="nome" value="${cliente.getNome()}">
						<br>
						
						<label for="user">E-mail:</label><br> 
						<input type="text" name="email" value="${cliente.getEmail()}">
						<br>
						
						<label for="senha">Senha:</label><br> 
						<input type="text" name="senha" value="${cliente.getSenha()}">
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
				<h1>RELATÓRIO DE VENDAS</h1>
			</header>

			<main>
				<div class="content">
					<div class="cabecalho-lista">
						<h2>Lista de Vendas</h2>
					</div>
					<table class="content-tabela" border="1" cellspacing="0" cellpadding="5">
						<thead>
							<tr>
								<th>Id do Item da Venda</th> 
								<th>Id da Venda</th>
								<th>Produto</th>
								<th>Data da Venda</th>
								<th>Quantidade</th>
								<th>Valor Total</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="relatorio" items="${listaRelatorio}">
								<tr>
									<td>${relatorio.id_item_venda}</td>
									<td>${relatorio.id_venda}</td>
									<td>${relatorio.produto_nome}</td>
									<td>${relatorio.data_venda}</td>
									<td>${relatorio.quantidade}</td>
									<td>R$ ${relatorio.valor_total}</td>
									<td>
										<button class="btn-itens" 
												onclick="abrirDetalhes_Relatorio(${relatorio.id_venda}, ${relatorio.id_cliente}, ${relatorio.id_produto}, '${relatorio.data_venda}', ${relatorio.quantidade}, ${relatorio.preco_unitario}, ${relatorio.subtotal}, ${relatorio.valor_total})">Exibir Detalhes</button>
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
</html>