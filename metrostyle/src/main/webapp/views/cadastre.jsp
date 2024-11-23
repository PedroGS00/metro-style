<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> -->

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Cadastro</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
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
                <div class="cadastro-container">
                   <form action="${pageContext.request.contextPath}/views/cadastro-response.jsp" method="post">
                        <h2>Cadastre-se na Metro Style</h2>
                        <div class="cadastro-campo">
                            <input type="text" name="usuario" placeholder="Nome de Usuário" required>
                            <input type="text" name="cadastro" placeholder="E-mail" required>
                            <input type="password" name="senha" placeholder="Senha" required>
                        </div>
                        <input type="submit" value="ENTRAR">
            
                        <div class="custom-checkbox">
                            <input type="checkbox" name="terms"> Concordo com os Termos de Serviço e a Política de Privacidade
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