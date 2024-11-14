<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Relatório Detalhado de Vendas</title>
   <link rel="shortcut icon" type="imagex/png" href="../imgs/icon.png">
    
</head>
<body>
    <h2>Relatório Detalhado de Produtos Comprados</h2>

    <form action="RelatorioServlet" method="post">
        ID Pedido: <input type="text" name="pedido" required><br>
        <input type="hidden" name="action" value="detalhado">
        <br>
        <input type="submit" value="Gerar Relatório">
    </form>

	<br>
    <%-- Exemplo de tabela detalhada de uma venda específica --%>
    <table border="1">
        <tr>
            <th>Produto</th>
            <th>Quantidade</th>
            <th>Preço Unitário</th>
            <th>Total</th>
            <th>Data</th>
        </tr>
        <tr>
            <td>Air Jordan 1</td>
            <td>2</td>
            <td>R$ 1500,00</td>
            <td>R$ 3000,00</td>
            <td>01/08/2024</td>
        </tr>
    </table>

    <br>
    <a href="relatorio.jsp">Voltar aos Relatórios</a>
</body>
</html>