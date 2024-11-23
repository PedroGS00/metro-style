<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> -->

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estruturaNav.css">
        <link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
        
    </head>

    <!-- <%
        if (request.getAttribute("falha") != null){
            %>
                <script type="text/javascript"> alert("<%=request.getAttribute("falha")%>")
                </script>
            <%
        }
    %> -->

    <body>
        <div class="sidebar">METRO STYLE</div>
    
        <div class="direita">
			<header>
				<jsp:include page="/includes/nav_bar.jsp" />
			</header>
    
            <main>
                <div class="login-container">
                    <form action="${pageContext.request.contextPath}/views/login-response.jsp" method="post">
                        <h2>Logue-se na Metro Style</h2>
                        <div class="login-campo">
                            <input type="text" name="login" placeholder="Usuário" required>
                            <input type="password" name="senha" placeholder="Senha" required>
                        </div>
                        <input type="submit" value="ENTRAR">
    
                        <div class="custom-checkbox">
                            <input type="checkbox" name="terms"> Concordo com os Termos de Serviço e a Política de Privacidade
                        </div>               
    
                        <div class="sem-conta">
                            <p>Ainda não tem conta? <a href="${pageContext.request.contextPath}/views/cadastre.jsp">Cadastre-se</a></p>
                        </div>
                        
                        <p>OR</p>
                        
                        <div class="social-login">
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