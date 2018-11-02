package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.LoginBean;
import database.DBConnection;

public class UserDao {
	public static LoginBean getUser(int userId){  
		Connection con = null;
		PreparedStatement ps= null;
		try{  
			con = DBConnection.createConnection();

			String query = "select * from user where user_id=?";
			ps = con.prepareStatement(query);
			ps.setString(1, String.valueOf(userId));  

			ResultSet rs=ps.executeQuery();  
			
			try{
				rs.first();
				LoginBean loginBean = new LoginBean();
				loginBean.setUserName(rs.getString("username"));
				return loginBean;
			}catch (SQLException ex){
				return null;
			}
			
			
		}catch(Exception e){System.out.println(e);}  
		return null;  
	}  
}
