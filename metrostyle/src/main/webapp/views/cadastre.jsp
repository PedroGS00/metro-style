<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.dao.ClienteDAO"%>
<%@ page import="com.metrostyle.models.Cliente"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Cadastro</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estruturaNav.css">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
    </head>

    <%
        String mensagem = (String) session.getAttribute("mensagem");
        String tipoMensagem = (String) session.getAttribute("tipoMensagem");
        if (mensagem != null && tipoMensagem != null) {
    %>
        <script>
            alert('<%= mensagem %>');
        </script>
    <%
            session.removeAttribute("mensagem");
            session.removeAttribute("tipoMensagem");
        }
    %>

    <%
        // Desloga o usuário, invalidando a sessão
        session.invalidate();
    %>


    <body>

        <div class="sidebar">METRO STYLE</div>
        
        <div class="direita">
			<header>
				<jsp:include page="/includes/nav_bar.jsp" />
			</header>
        
            <main>
                <div class="cadastro-container">
                   <form action="${pageContext.request.contextPath}/clientes/novo" method="post">
                        <input type="hidden" name="origem" value="usuario">
                        <h2>Cadastre-se na Metro Style</h2>
                        <div class="cadastro-campo">
                            <input type="hidden" name="id" value="${cliente.getId()}"> 
                            <input type="text" name="nome" placeholder="Nome" value="${cliente.getNome()}" required>
                                
                            <input type="text" name="email" placeholder="E-mail" value="${cliente.getEmail()}" required>
                                
                            <input type="password" name="senha" placeholder="Senha" value="${cliente.getSenha()}" required>
                        </div>
                        <input type="submit" value="CADASTRAR">
            
                        <div class="custom-checkbox">
                            <input type="checkbox" name="terms" required> Concordo com os Termos de Serviço e a Política de Privacidade
                        </div>   
           
                        <div class="com-conta">
                            <p>Já tem uma conta? <a href="${pageContext.request.contextPath}/views/login.jsp">Login</a></p>
                        </div>
                            
                        <p>OR</p>
                            
                        <div class="social-cadastro">
                            <a href="#"><img src="${pageContext.request.contextPath}/imgs/apple.png" alt="Apple"></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/imgs/google.png" alt="Google"></a>
                            <a href="#"><img src="${pageContext.request.contextPath}/imgs/facebook.png" alt="Facebook"></a>
                        </div>
                    </form>
                </div> 
             </main>
        
			<jsp:include page="/includes/footer.jsp" />
        </div>
    </body>
</html>