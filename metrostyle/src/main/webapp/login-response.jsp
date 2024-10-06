<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Retorno</title>
</head>
<body>

<%
	String login = request.getParameter("login");
	String senha = request.getParameter("senha");
	
	 if(login != null && senha!= null) {
	  
	   if(login.equals("admin") && senha.equals("1234")){
		   
		   session.setAttribute("login",login);
	
	     response.sendRedirect("index.jsp");
	
	   }else{
	     response.sendRedirect("login.jsp");
	   }
	}else{
	  response.sendRedirect("login.jsp"); 
	}


%>

</body>
</html>