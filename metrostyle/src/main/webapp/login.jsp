<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro</title>
<link rel="stylesheet" href="css/style.css">
</head>

<%
if (request.getAttribute("falha") != null){
%>
<script type="text/javascript"> alert("<%=request.getAttribute("falha")%>")
</script>
<%
}
%>
<body>

<div class="sidebar">METRO STYLE</div>
    <div class="navbar">
        <a href="index.jsp">HOME</a>
        <a href="#">PRODUTOS</a>
        <a href="#">CLIENTES</a>
        <a href="#">CONTATO</a>
    </div>

	<div class="login-container">
        <form action="login-response.jsp" method="post">
            <h2>Logue-se na Metro Style</h2>
            <input type="text" name="Login" placeholder="E-mail" required>
            <input type="password" name="password" placeholder="Senha" required>
        
            <input type="submit" value="ENTRAR">
            <label>
                <input type="checkbox" name="terms"> Concordo com os Termos de Serviço e a Política de Privacidade
            </label>    
   		        
       
            <div class="social-login">
                <p>OR</p>
                <a href="#"><img src="google-logo.png" alt="Google"></a>
                <a href="imgs/apple.png"><img src="apple-logo.png" alt="Apple"></a>
                <a href="#"><img src="facebook-logo.png" alt="Facebook"></a>
            </div>
        </form>
	</div> 
	
</body>
</html>