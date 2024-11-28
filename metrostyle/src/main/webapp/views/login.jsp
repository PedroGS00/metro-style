<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.metrostyle.dao.ClienteDAO"%>
<%@ page import="com.metrostyle.models.Cliente"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
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

    <body>
        <div class="sidebar">METRO STYLE</div>
    
        <div class="direita">
            <header>
                <jsp:include page="/includes/nav_bar.jsp" />
            </header>
    
            <main>
                <div class="login-container">
                    <form action="${pageContext.request.contextPath}/views/login.jsp" method="post">
                        <h2>Logue-se na Metro Style</h2>
                        <div class="login-campo">
                            <input type="text" name="email" id="email" placeholder="E-mail" required>
                            <input type="password" name="senha" id="senha" placeholder="Senha" required>
                        </div>
                        <input type="submit" value="ENTRAR">
    
                        <div class="custom-checkbox">
                            <input type="checkbox" name="terms" required> Concordo com os Termos de Serviço e a Política de Privacidade
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

                    <%
                        String email = request.getParameter("email");
                        String senha = request.getParameter("senha");

                        if (email != null && senha != null) {
                            ClienteDAO clienteDAO = new ClienteDAO();
                            Cliente cliente = clienteDAO.autenticar(email, senha);

                            if (email.equals("admin") && senha.equals("admin")) {
                                response.sendRedirect("../adm-manutencao.jsp"); // Redireciona para a página de administração
                            } else if (cliente != null) {
                                session.setAttribute("cliente", cliente); // Armazena o cliente na sessão
                                response.sendRedirect("../index.jsp"); // Redireciona para a página inicial
                            } else {
                                out.println("<script type='text/javascript'>");
                                out.println("alert('Credenciais inválidas. Tente novamente.');");
                                out.println("</script>");
                            }
                        }
                    %>
                </div>
            </main>
    
            <jsp:include page="/includes/footer.jsp" />
        </div>
    </body>
</html>
