package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import model.Cliente;


public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ClienteServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cliente umCliente = new Cliente();
		umCliente.setNome(request.getParameter("nome"));
		umCliente.setSobrenome(request.getParameter("sobrenome"));
		umCliente.setSexo(request.getParameter("sexo"));
		umCliente.setCidade(request.getParameter("cidade"));
		String[] idiomas = request.getParameterValues("idiomas");
		umCliente.setIdiomas(idiomas);
		
		//Passo o objeto inteiro para o request
		request.setAttribute("cliente", umCliente);
		request.getRequestDispatcher("visualiza-cliente.jsp").forward(request, response);	
		
	}

}
