<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="./css/login.css">
        <link rel="stylesheet" href="./css/estrutura.css">
    </head>

    <%
        if (request.getAttribute("falha") != null){
            %>
                <script type="text/javascript"> alert("<%=request.getAttribute("falha")%>")
                </script>
            <%
        }
    %>

    <div class="sidebar">METRO STYLE</div>
    
    <header>
        <div class="navbar">
            <a href="index.jsp">HOME</a>
            <a href="#">PRODUTOS</a>
            <a href="#">CLIENTES</a>
            <a href="#">CONTATO</a>
        </div>
    </header>

    <body>
        <div class="login-container">
            <form action="login-response.jsp" method="post">
                <h2>Logue-se na Metro Style</h2>
                <input type="text" name="login" placeholder="E-mail" required>
                <input type="password" name="senha" placeholder="Senha" required>
                <label>
                    <input type="checkbox" name="terms"> Concordo com os Termos de Serviço e a Política de Privacidade
                </label>
                <input type="submit" value="ENTRAR">
                
                <p>OR</p>
                
                <div class="social-login">
                    <a href="#"><img src="./imgs/apple.png" alt="Apple"></a>
                    <a href="#"><img src="./imgs/google.png" alt="Google"></a>
                    <a href="#"><img src="./imgs/facebook.png" alt="Facebook"></a>
                </div>
            </form>
        </div> 
    </body>
</html>