package dao;

import java.sql.*;

import beans.LoginBean;
import database.DBConnection;  

public class LoginDao {  
	public int validate(LoginBean loginBean){  
		String name = loginBean.getUserName();
		String pass = loginBean.getPassword();
		Connection con = null;
		PreparedStatement ps= null;
		try{  
			// Create connection with DB
			con = DBConnection.createConnection();
			
			// Get all data from DB if username and password are equal to the credentials entered by user
			String query = "select * from user where username=? and password=?";
			ps = con.prepareStatement(query);
			ps.setString(1,name);  
			ps.setString(2,pass);  

			ResultSet rs=ps.executeQuery();  
			
			try{
				rs.first();
				String id = rs.getString("user_id");
				return Integer.valueOf(id);
			}catch (SQLException ex){
				return 0;
			}
			
			
		}catch(Exception e){System.out.println(e);}  
		return 0;  
	}  
}  