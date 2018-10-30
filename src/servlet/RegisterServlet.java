//RegisterServlet.java
package servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.RegisterBean;
import dao.RegisterDao;
public class RegisterServlet extends HttpServlet {
	public RegisterServlet() {
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Copying all the input parameters in to local variables
		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		RegisterBean registerBean = new RegisterBean();
		//Using Java Beans - An easiest way to play with group of related data
		registerBean.setUserName(userName);
		registerBean.setEmail(email);
		registerBean.setPassword(password); 
		RegisterDao registerDao = new RegisterDao();
		//The core Logic of the Registration application is present here. We are going to insert user data in to the database.
		String userRegistered = registerDao.registerUser(registerBean);
		if(userRegistered.equals("SUCCESS"))   //On success, you can display a message to user on Home page
		{
			request.getRequestDispatcher("/").forward(request, response);
		}
		else   //On Failure, display a meaningful message to the User.
		{
			request.setAttribute("errMessage", userRegistered);
			request.getRequestDispatcher("/javasite/login").forward(request, response);
		}
	}
}