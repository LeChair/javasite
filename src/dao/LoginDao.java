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
			con = DBConnection.createConnection();

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