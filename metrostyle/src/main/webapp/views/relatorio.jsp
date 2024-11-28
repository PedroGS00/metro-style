<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.dao.RelatorioDAO"%>
<%@ page import="com.metrostyle.models.Relatorio"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Metrô Style | Relatórios </title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/estruturaMan.css">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/relatorio.css">
		<link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
		
	</head>

	<body>
		<div class="sobreTela-abrir">
			<div class="sobreTela-abrir-editar">
				<div class="fechar-tela-edit">
					<p><img src="${pageContext.request.contextPath}/imgs/x.png" alt="" onclick="fecharDivEDIT()"></p>
				</div>

				<div class="sobreTela-form">
					<form>
						<label for="id_item_venda">ID Item Venda:</label> 
						<input id="id_item_venda" type="text" name="id_item_venda" readonly>
						<br>

						<label for="id_venda">ID Venda:</label> 
						<input id="id_venda" type="text" name="id_venda" readonly>
						<br>

						<label for="id_cliente">ID Cliente:</label> 
						<input id="id_cliente" type="text" name="id_cliente" readonly>
						<br>						

						<label for="cliente_nome">Cliente Nome:</label> 
						<input id="cliente_nome" type="text" name="cliente_nome" readonly>
						<br>

						<label for="produto_nome">Produto Nome:</label> 
						<input id="produto_nome" type="text" name="produto_nome" readonly>
						<br>

					</form>
					<form>

						<label for="preco_unitario">Preço Unitário:</label> 
						<input id="preco_unitario" type="text" name="preco_unitario" readonly>
						<br>

						<label for="quantidade">Quantidade:</label> 
						<input id="quantidade" type="text" name="quantidade" readonly>
						<br>

						<label for="subtotal">SubTotal:</label> 
						<input id="subtotal" type="text" name="subtotal" readonly>
						<br>

						<label for="valor_total">Valor Total (ID Venda):</label> 
						<input id="valor_total" type="text" name="valor_total" readonly><br>

						<label for="data_venda">Data da Venda:</label>
						<input id="data_venda" type="text" name="data_venda" readonly>
						<br>
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
						<form method="GET" action="<%= request.getContextPath() + "/relatorios" %>">
							<div class="campo-pesquisa">
								<label for="dataPesquisa">Filtro por Data:</label>
								<input type="date" id="dataPesquisa" name="dataPesquisa" />
								<button type="submit">Pesquisar</button>
							</div>
						</form>
					</div>
					<%
						String data_venda_pesquisa = request.getParameter("data_venda_pesquisa");
						ArrayList<Relatorio> listaRelatorio = new ArrayList<>();

						if (data_venda_pesquisa != null && !data_venda_pesquisa.isEmpty()) {
							// Chama o método de filtro no DAO
							RelatorioDAO relatorioDAO = new RelatorioDAO();
							listaRelatorio = relatorioDAO.filtrarPorData(data_venda_pesquisa); // Passando a data como parâmetro
						} else {
							// Se não for informada uma data, exibe todos os relatórios
							RelatorioDAO relatorioDAO = new RelatorioDAO();
							listaRelatorio = relatorioDAO.listar(); // Método listar sem filtro
						}
					%>
					<table class="content-tabela" border="1" cellspacing="0" cellpadding="5">
						<thead>
							<tr>
								<th>Id do Item da Venda</th> 
								<th>Id da Venda</th>
								<th>Cliente</th>
								<th>Valor Total</th>
								<th>Data da Venda</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="relatorio" items="${listaRelatorio}">
								<tr>
									<td>${relatorio.id_item_venda}</td>
									<td>${relatorio.id_venda}</td>
									<td>${relatorio.cliente_nome}</td>
									<td>${relatorio.valor_total}</td>
									<td>${relatorio.data_venda}</td>
									<td>
										<button class="btn-itens" 
												onclick="abrirDetalhes_Relatorio(${relatorio.id_item_venda}, ${relatorio.id_venda}, ${relatorio.id_venda}, '${relatorio.cliente_nome}', '${relatorio.produto_nome}', ${relatorio.preco_unitario}, ${relatorio.quantidade}, ${relatorio.subtotal}, ${relatorio.valor_total}, '${relatorio.data_venda}')">
											Exibir Detalhes
										</button>
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