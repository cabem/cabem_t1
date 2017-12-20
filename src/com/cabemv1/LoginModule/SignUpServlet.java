package com.cabemv1.LoginModule;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.cabemv1.connections.MysqlDataSourceConn;
import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		MysqlDataSource dataSource = MysqlDataSourceConn.getDataSource();
		String Response = "";
		String username = request.getParameter("UserName");
		String emailID = request.getParameter("Email");
		String password = request.getParameter("Password");
		String name = request.getParameter("Name");
		String salt = BCrypt.gensalt(12);
		String encrypted_password = BCrypt.hashpw(password, salt);
		String sql = "";
		int result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String createdAt = sdf.format(Calendar.getInstance().getTime());
		
		String modifiedAt = createdAt;
		
		try {
    		
    		//Another method of connecting to the database using the ConnectionConn class.
    		//Connection conn = ConnectionConn.getConnection(); 
    		Connection conn = dataSource.getConnection();
        	Statement stmt = conn.createStatement();
        	sql = "INSERT into users (unique_id,name,email,encrypted_password,salt,created_at,updated_at) "
        			+ "VALUES('"+username+"','"+name+"','"+emailID+"','"+encrypted_password+"','"+salt+"','"+createdAt+"','"+modifiedAt+"')";
    
        	result = stmt.executeUpdate(sql);
        	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
		
		response.getWriter().append("Served at: SignUpServlet " + " \n\n\n" + result + "\n\n\n by SQL query ---> "+ sql);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
