<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.dao.ClienteDAO"%>
<%@ page import="com.metrostyle.models.Cliente"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Compra Realizada</title>
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/contato.css">
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/estruturaNav.css">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
        
    </head>

    <body>
    	<div class="sidebar">METRO STYLE</div>
    
		<div class="direita">
			<header>
				<jsp:include page="/includes/nav_bar.jsp" />
			</header>
			
			<main>
				<h1>Compra realizada com sucesso!</h1>
				<p>Obrigado por comprar na MetroStyle. Seu pedido foi registrado.</p>
				<a href="/metrostyle/index.jsp">Voltar à página inicial</a>
			</main>
	
			<jsp:include page="/includes/footer.jsp"/>
		</div>
    </body>
</html>