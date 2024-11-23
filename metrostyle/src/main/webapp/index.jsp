${pageContext.request.contextPath}<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Metrô Style | Ínicio</title>
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" type="text/css"  href="${pageContext.request.contextPath}/css/estruturaNav.css">
		<link rel="shortcut icon" type="imagex/png" href="${pageContext.request.contextPath}/imgs/icon.png">
		
    </head>

    <body>
    	<div class="sidebar">METRO STYLE</div>
    
		<div class="direita">
			<header>
				<jsp:include page="/includes/nav_bar.jsp" />
			</header>
			
			<main>
				<div class="container-tenis">
					<div class="mais-pop">
						<h1>Mais Populares</h1>
						<div class="lista-tenis">
							<div class="box-tenis">
								<img src="${pageContext.request.contextPath}/imgs/1.png" alt="Tênis">
								<div class="box-tenis-desc">
									<p id="nome">Tênis Nike Revolution 6 Next Nature Preto/ Branco</p>
									<p id="modelo">Casual</p>
									<p id="valor">R$ 400,00</p>
								</div>
								<div class="box-tenis-comprar">
									<button>COMPRAR</button>
								</div>
							</div>
							<div class="box-tenis">
								<img src="${pageContext.request.contextPath}/imgs/1.png" alt="Tênis">
								<div class="box-tenis-desc">
									<p id="nome">Tênis Nike Revolution 6 Next Nature Preto/ Branco</p>
									<p id="modelo">Casual</p>
									<p id="valor">R$ 400,00</p>
								</div>
								<div class="box-tenis-comprar">
									<button>COMPRAR</button>
								</div>
							</div>
							<div class="box-tenis">
								<img src="${pageContext.request.contextPath}/imgs/1.png" alt="Tênis">
								<div class="box-tenis-desc">
									<p id="nome">Tênis Nike Revolution 6 Next Nature Preto/ Branco</p>
									<p id="modelo">Casual</p>
									<p id="valor">R$ 400,00</p>
								</div>
								<div class="box-tenis-comprar">
									<button>COMPRAR</button>
								</div>
							</div>
							<div class="box-tenis">
								<img src="${pageContext.request.contextPath}/imgs/1.png" alt="Tênis">
								<div class="box-tenis-desc">
									<p id="nome">Tênis Nike Revolution 6 Next Nature Preto/ Branco</p>
									<p id="modelo">Casual</p>
									<p id="valor">R$ 400,00</p>
								</div>
								<div class="box-tenis-comprar">
									<button>COMPRAR</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>
	
			<jsp:include page="/includes/footer.jsp" />
		</div>
    </body>
</html>