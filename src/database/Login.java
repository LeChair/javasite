package database;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try{
        
        //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");

	//creating connection with the database 
        Connection  con=DriverManager.getConnection
                     ("jdbc:mysql:/ /localhost:8080/javasite","root","");

        PreparedStatement ps=con.prepareStatement
                  ("insert into user values(?,?,?)");

        ps.setString(1, username);
        ps.setString(2, password);
        int i=ps.executeUpdate();
        
          if(i>0)
          {
            out.println("You are sucessfully logged in");
          }
        
        }
        catch(Exception se)
        {
            se.printStackTrace();
        }
	
      }
  }