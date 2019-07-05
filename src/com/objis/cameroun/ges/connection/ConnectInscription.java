package com.objis.cameroun.ges.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectInscription {
	
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException 
	{  
	// Note: Change the connection parameters accordingly.     
	String hostName = "localhost";     
	String dbName = "db_ecole";     
	String userName = "root";     
	String password = "hamsaad12";     
	return getMySQLConnection(hostName, dbName, userName, password); 
}   

public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException
{         
	Class.forName("com.mysql.jdbc.Driver");         
	String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName;       
	Connection conn = DriverManager.getConnection(connectionURL, userName, password);     
	return conn; 
}
} 
