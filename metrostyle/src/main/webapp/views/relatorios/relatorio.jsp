<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Relatórios de produtos</title>
		<link rel="shortcut icon" type="imagex/png" href="../imgs/icon.png">

</head>
<body>

	<h2>Relatórios de Pedidos</h2>

	<ul>
		<li> <a href="<%=request.getContextPath()%>/views/relatorios/relatorio-resumido.jsp">Resumo geral das vendas por cliente no dia</a> </li>
		<li> <a href="<%=request.getContextPath()%>/views/relatorios/relatorio-detalhado.jsp">Detalhes dos produtos comprados por venda específica</a> </li>	

	</ul>

</body>
</html>