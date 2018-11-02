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
import dao.UserDao;
/**
 * Servlet implementation class Servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

	}

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
		
		int userID = loginDao.validate(loginBean);
		
		if(userID != 0)   //On success, you can display a message to user on Home page
		{
			LoginBean userBean = UserDao.getUser(userID);
			HttpSession loginSession = request.getSession(true);
			loginSession.setMaxInactiveInterval(60*60);
			request.getSession().setAttribute("userBean", userBean);
			RequestDispatcher rd=request.getRequestDispatcher("/");  
			rd.forward(request,response);
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
