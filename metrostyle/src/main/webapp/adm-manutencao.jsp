<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.dao.ClienteDAO"%>
<%@ page import="com.metrostyle.models.Cliente"%>

<!DOCTYPE html>
<html>
 	<head>
        <meta charset="UTF-8">
        <title>Metrô Style | Administração</title>
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/estruturaNav.css">
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/adm-manutencao.css">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
        
    </head>

    <body>
		<div class="sidebar">METRO STYLE</div>

		<div class="direita">
			<header>
				<jsp:include page="/includes/nav_bar.jsp" />
			</header>
	
			<main>
				<div class="btn-nav">
					<a href="${pageContext.request.contextPath}/produtos"><button>Manutenção de Produtos</button></a>
					<a href="${pageContext.request.contextPath}/clientes"><button>Manutenção de Clientes</button></a>
					<a href="${pageContext.request.contextPath}/relatorios"><button>Relatório de Vendas</button></a>
				</div>
				<a href="${pageContext.request.contextPath}/views/login.jsp"><button id="sair">Sair</button></a>
			</main>
	
			<jsp:include page="/includes/footer.jsp" />
		</div>
	
	</body>
</html>