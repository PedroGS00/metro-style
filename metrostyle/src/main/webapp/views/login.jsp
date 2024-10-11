<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> -->

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Login</title>
        <link rel="stylesheet" href="../css/login.css">
        <link rel="stylesheet" href="../css/estruturaNav.css">
        <link rel="shortcut icon" type="imagex/png" href="../imgs/icon.png">
        
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
                <div class="navbar">
                    <div>
                        .
                    </div>
                    <div class="navbar-itens">
                        <a href="../index.jsp">HOME</a>
                        <a href="./produtos.jsp">PRODUTOS</a>
                        <a href="./sobre.jsp">SOBRE</a>
                        <a href="./contato.jsp">CONTATO</a>
                    </div>
                    <div class="navbar-img">
                        <a href="#"><img src="../imgs/carrinho1.png" alt=""></a>
                    </div>
                </div>
            </header>
    
            <main>
                <div class="login-container">
                    <form action="./login-response.jsp" method="post">
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
                            <p>Ainda não tem conta? <a href="./cadastre.jsp">Cadastre-se</a></p>
                        </div>
                        
                        <p>OR</p>
                        
                        <div class="social-login">
                            <a href="#"><img src="../imgs/apple.png" alt="Apple"></a>
                            <a href="#"><img src="../imgs/google.png" alt="Google"></a>
                            <a href="#"><img src="../imgs/facebook.png" alt="Facebook"></a>
                        </div>
                    </form>
                </div>
            </main>
    
            <footer>
                <p>&copy; 2024 - METRO STYLE</p>
            </footer>
        </div>
    </body>
</html>