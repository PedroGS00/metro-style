<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Metrô Style | Manutenção de Clientes</title>
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
				<form action="${pageContext.request.contextPath}/clientes/novo" method="post"> 
					<input type="hidden" name="id" value="${cliente.getId()}" required> 
			
					<label for="nome">Nome:</label><br> 
					<input type="text" name="nome" value="${cliente.getNome()}" required>
					<br>
					
					<label for="user">E-mail:</label><br> 
					<input type="text" name="email" value="${cliente.getEmail()}" required>
					<br>
					
					<label for="senha">Senha:</label><br> 
					<input type="text" name="senha" value="${cliente.getSenha()}" required>
					<br><br>
					
					<input type="submit" value="Salvar"> 
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
				<form action="${pageContext.request.contextPath}/clientes/update" method="post"> 
					<input id="id_cliente" type="hidden" name="id">

					<label for="nome">Nome:</label><br> 
					<input id="nome" type="text" name="nome" required>
					<br>

					<label for="user">E-mail:</label><br> 
					<input id="user" type="text" name="email" required>
					<br>

					<label for="senha">Senha:</label><br> 
					<input id="senha" type="text" name="senha" required>
					<br><br>

					<input type="submit" value="Atualizar">  
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
			<h1>MANUTENÇÃO DE CLIENTES</h1>
		</header>

		<main>
			<div class="content">
				<div class="cabecalho-lista">
					<h2>Lista de Clientes</h2>
					<button class="btn-cadastro" onclick="abrirDivCAD()">Cadastrar Cliente</button>
				</div>
		        <table class="content-tabela" border="1" cellspacing="0" cellpadding="5">
					<thead>
						<tr>
							<th>Id do Cliente</th>
							<th>Nome</th>
							<th>E-mail</th>
							<th>Senha</th>
							<th>Ações</th>
						</tr>
					</thead>
		            <tbody>
						<!-- Exemplo -->
						<%-- <tr>
							<td>1</td>
							<td>Rodrigo Pires</td>
							<td>rodrigo@gmail.com</td>
							<td>12345</td>
							<td> 
								<button class="btn-itens">Editar</button>

								<button id="excluir" class="btn-itens">Excluir</button>
							</td>
						</tr> --%>
		                <c:forEach var="cliente" items="${listaClientes}">
		                    <tr>
		                        <td>${cliente.id}</td>
		                        <td>${cliente.nome}</td>
		                        <td>${cliente.email}</td>
		                        <td>${cliente.senha}</td>
		                        <td> 
									<div id="btn-itens-linha">
										<button class="btn-itens" onclick="abrirDivEDIT_Cli(${cliente.id}, '${cliente.nome}', '${cliente.email}', '${cliente.senha}')">Editar</button>

										<a href="${pageContext.request.contextPath}/clientes/excluir?id=${cliente.id}"><button id="excluir" class="btn-itens" onclick="return confirm('Tem certeza que deseja excluir este cliente?');">Excluir</button></a>
									</div>
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
