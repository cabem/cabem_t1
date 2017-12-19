package com.cabemv1.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConn {
	static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://sql12.freemysqlhosting.net:3306/sql12211685";
		String user="sql12211685";
		String pass="eU7XBtIU1c";
		conn = DriverManager.getConnection(url,user,pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
