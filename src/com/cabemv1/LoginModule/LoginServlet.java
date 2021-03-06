package com.cabemv1.LoginModule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.cabemv1.connections.MysqlDataSourceConn;
import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MysqlDataSource dataSource = MysqlDataSourceConn.getDataSource();
		String Response = "";
		String emailid = request.getParameter("Email");
		String password = request.getParameter("Password");
		String loginCriterion = "";
		
		if(emailid.contains("@"))
		{
			loginCriterion = "email";
		}
		else
		{
			loginCriterion = "unique_id";
		}
    	try {
    		
    		//Another method of connecting to the database using the ConnectionConn class.
    		//Connection conn = ConnectionConn.getConnection(); 
    		Connection conn = dataSource.getConnection();
        	Statement stmt = conn.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE email = '"+emailid+"'");
        	
        	if(!(rs.next()))		
        	{
        		Response = "user not found";
        	}
        	else
        	{
        	rs.beforeFirst();
        	while(rs.next()) {
        		String Res = rs.getString("encrypted_password");
        		boolean authenticated = BCrypt.checkpw(password, Res);
        		
        		if(authenticated)
        		{
        			Response = "authenticated";
        		}
        		else
        		{
        			Response = "Wrong user Password";
        		}
        		
        	}
        	}
        	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		response.getWriter().append("Served at: LoginServlet" + " \n\n\n" + Response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
