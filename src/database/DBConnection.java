//DBConnection.java
package database;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.activation.DataSource;
import javax.annotation.Resource;
public class DBConnection {
	
	@Resource(name = "jdbc/UsersDB")
	private DataSource dataSource;
	
	public static Connection createConnection()
	{
		Connection con = null;
		String url = "jdbc:mysql://localhost:3306/pilsview"; //MySQL URL followed by the database name
		String username = "root"; //MySQL username
		String password = ""; //MySQL password
		
		try 
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver"); //loading MySQL drivers. This differs for database servers 
			} 
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password); //attempting to connect to MySQL database
			System.out.println("Printing connection object "+con);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return con; 
	}
}