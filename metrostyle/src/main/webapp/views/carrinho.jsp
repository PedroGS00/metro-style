<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.dao.ClienteDAO"%>
<%@ page import="com.metrostyle.models.Cliente"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Carrinho</title>
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/estruturaNav.css">
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/carrinho.css">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
        
    </head>
    
    <% 
	    String mensagem = (String) request.getAttribute("mensagem");
	    String tipoMensagem = (String) request.getAttribute("tipoMensagem");
	    if (mensagem != null && tipoMensagem != null) {
	%>
	    <script>
	        alert('<%= mensagem %>');
	    </script>
	<% 
	    }
	%>

    <body>
    	<div class="sidebar">METRO STYLE</div>
    
		<div class="direita">
			<header>
				<jsp:include page="/includes/nav_bar.jsp" />
			</header>
			
			<main>
				<table class="content-tabela" border="1" cellspacing="0" cellpadding="5">
				    <thead>
				        <tr>
				            <th>Nome do Produto</th>
				            <th>Quantidade</th>
				            <th>Preço Unitário</th>
				            <th>Subtotal</th>
				            <th>Ações</th>
				        </tr>
				    </thead>
				    <tbody>
				        <c:forEach var="item" items="${listaCarrinho}">
				            <tr>
				                <td>${item.nomeProduto}</td>
				                <td>${item.quantidade}</td>
				                <td>R$ ${item.preco_unitario}</td>
				                <td>R$ ${item.subtotal}</td>
				                <td>
				                    <form action="${pageContext.request.contextPath}/removerItemCarrinho" method="post">
				                        <input type="hidden" name="id_item_carrinho" value="${item.id_item_carrinho}" />
				                        <button type="submit">Remover</button>
				                    </form>
				                </td>
				            </tr>
				        </c:forEach>
				    </tbody>
				    
				</table>
			</main>
			
			<jsp:include page="/includes/footer.jsp" />
		</div>
    </body>
</html>