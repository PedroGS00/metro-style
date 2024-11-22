<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
 	<head>
        <meta charset="UTF-8">
        <title>Metrô Style | Administração</title>
        <link rel="stylesheet" type="text/css"  href="./css/estruturaNav.css">
        <link rel="stylesheet" type="text/css"  href="./css/adm-manutencao.css">
        <link rel="shortcut icon" type="imagex/png" href="./imgs/icon.png">
        
    </head>

    <body>
		<div class="sidebar">METRO STYLE</div>

		<div class="direita">
			<header>
				<jsp:include page="/includes/nav_bar.jsp" />
			</header>
	
			<main>
				<div class="btn-nav">
					<a href="views/man-produtos.jsp"><button>Manutenção de Produtos</button></a>
					<a href="views/man-cliente.jsp"><button>Manutenção de Clientes</button></a>
					<a href="views/relatorio.jsp"><button>Relatório de Vendas</button></a>
				</div>
				<a href="./views/login.jsp"><button id="sair">Sair</button></a>
			</main>
	
			<jsp:include page="/includes/footer.jsp" />
		</div>
	
	</body>
</html>