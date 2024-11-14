<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Relatorio Resumido</title>
		<link rel="shortcut icon" type="imagex/png" href="../imgs/icon.png">

</head>
<body>

	<h2>Resumo geral das vendas por cliente no dia</h2>
	
	<form action="RelatorioServlet" method="post">
		Data: <input type="date" name="data" required> 
		<br>
		Cliente: <input type="text" name="cliente" required> 
		<br>
		<input type="hidden" name="action" value="resumoPorCliente">
		<br>
		<input type="submit" value="Gerar relatorio">
	</form>
	
	<br>
	<%-- Exemplo de tabela de vendas por cliente no dia --%>
    <table border="1">
        <tr>
            <th>ID Pedido</th>
            <th>Data</th>
            <th>Total</th>
        </tr>
        <tr>
            <td>001</td>
            <td>11/10/2024</td>
            <td>R$ 200,00</td>
        </tr>
    </table>

    <br>
    <a href="relatorio.jsp">Voltar aos Relatórios</a>

</body>
</html>