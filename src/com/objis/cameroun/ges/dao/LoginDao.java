package com.objis.cameroun.ges.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.objis.cameroun.ges.domain.UserAccount;

public class LoginDao implements IloginDao{
	
	public  UserAccount findUser(Connection conn, String userName, String password) throws SQLException 
	{         
		String sql = "Select a.userName, a.Password, a.Level from tbl_acount a  where a.userName = ? and a.password = ?"; 
		
		PreparedStatement pstm = conn.prepareStatement(sql);   
		
		pstm.setString(1, userName);        
		pstm.setString(2, password);        
		
		ResultSet rs = pstm.executeQuery();         
		if (rs.next()) 
		{            
			String gender = rs.getString("Level");            
			UserAccount user = new UserAccount();            
			user.setUserName(userName);            
			user.setPassword(password);            
			user.setGender(gender);            
			return user;        
		}        
		return null;    
	}     
	public  UserAccount findUser(Connection conn, String userName) throws SQLException 
	{         
		String sql = "Select a.User_Name, a.Password, a.Gender from user_account a "               
		+ " where a.User_Name = ? "; 
		
		PreparedStatement pstm = conn.prepareStatement(sql); 
		pstm.setString(1, userName);         
		ResultSet rs = pstm.executeQuery(); 
		
		if (rs.next()) 
		{            
			String password = rs.getString("Password");            
			String gender = rs.getString("Gender");            
			UserAccount user = new UserAccount();            
			user.setUserName(userName);            
			user.setPassword(password);            
			user.setGender(gender);            
			return user;        
		}        
		return null;    
	}     
	

}
