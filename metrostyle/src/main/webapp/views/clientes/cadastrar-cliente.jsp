<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Cliente</title>
</head>
<body>
    <h2>Cadastro de Cliente</h2>

    <% if (request.getAttribute("mensagem") != null) { %>
        <p><%= request.getAttribute("mensagem") %></p>
    <% } %>

    <form action="ClienteServlet" method="post">
        <input type="hidden" name="action" value="incluir">
        Nome: <input type="text" name="nome" required><br>
        Email: <input type="email" name="email" required><br>
        Telefone: <input type="text" name="telefone" required><br>
        Endereço: <input type="text" name="endereco" required><br> <!-- Novo campo -->
        <input type="submit" value="Cadastrar">
    </form>

    <h2>Lista de Clientes</h2>
    <form action="ClienteServlet" method="get">
        <input type="hidden" name="action" value="listar">
        <input type="submit" value="Listar Clientes">
    </form>
</body>
</html>
