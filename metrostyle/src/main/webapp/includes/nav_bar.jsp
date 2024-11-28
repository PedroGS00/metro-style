<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.models.Cliente"%>

<div class="navbar">
	<div>
		.
	</div>
	<div class="navbar-itens">
		<a href="${pageContext.request.contextPath}/index.jsp">HOME</a>
		<a href="${pageContext.request.contextPath}/views/produtos.jsp">PRODUTOS</a>
		<a href="${pageContext.request.contextPath}/views/sobre.jsp">SOBRE</a>
		<a href="${pageContext.request.contextPath}/views/contato.jsp">CONTATO</a>
	</div>
	<div class="navbar-img">
		<a href="#"><img id="carrinho-img" src="${pageContext.request.contextPath}/imgs/carrinho1.png" alt=""></a>
		<div class="texto-login">
			<a href="${pageContext.request.contextPath}/views/login.jsp">
				<img src="${pageContext.request.contextPath}/imgs/login1.png" alt="">
			</a>
			<p>
			<% 
				Cliente cliente = (Cliente) session.getAttribute("cliente");
				if (cliente != null) {
					out.print(cliente.getNome()); 
				} else {
					out.print("Visitante"); 
				}
			%>
			</p>
		</div>
	</div>
</div>