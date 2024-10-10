package servelets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class LoginServelet
 */
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
			
		HttpSession sessao = request.getSession();
			
		if(login.equals("admin") && senha.equals("1234")) 
		{
			sessao.setAttribute("login", login);
			request.getRequestDispatcher("adm-manutencao.jsp").forward(request, response);
		}
		
		else if(login.equals("usuario") && senha.equals("1234"))
		{
			sessao.setAttribute("login", login);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	
		else 
		{
			request.setAttribute("falha", "Login ou Senha inválidos");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
