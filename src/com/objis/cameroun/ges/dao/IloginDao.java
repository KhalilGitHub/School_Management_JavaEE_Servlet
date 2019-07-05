package com.objis.cameroun.ges.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.objis.cameroun.ges.domain.UserAccount;

public interface IloginDao {
	
	public  UserAccount findUser(Connection conn, String userName, String password) throws SQLException;
	public  UserAccount findUser(Connection conn, String userName) throws SQLException;

}
