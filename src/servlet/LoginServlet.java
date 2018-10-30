package servlet;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.LoginBean;
import dao.LoginDao;
/**
 * Servlet implementation class Servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*StudentBean studentBean = new StudentBean();
		studentBean.setFirstName("Jasper");
		studentBean.setLastName("Kuiper");
		studentBean.setAge(22);

		request.setAttribute("student", studentBean);*/

		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {  

		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  

		LoginBean loginBean = new LoginBean();		
		String n=request.getParameter("username");  
		String p=request.getParameter("password");  

		loginBean.setUserName(n);
		loginBean.setPassword(p); 
		LoginDao loginDao = new LoginDao();
		
		boolean userLoggedIn = loginDao.validate(loginBean);
		
		if(userLoggedIn = true)   //On success, you can display a message to user on Home page
		{
			RequestDispatcher rd=request.getRequestDispatcher("/");  
			rd.forward(request,response);
			HttpSession loginSession = request.getSession(true);
			loginSession.setMaxInactiveInterval(60*60);
		}
		else   //On Failure, display a meaningful message to the User.
		{
			out.print("Wrong username or password!");  
			RequestDispatcher rd=request.getRequestDispatcher("index.jsp");  
			rd.include(request,response); 
		}

		out.close();  
	}  
}
