package com.epaylater.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
	private static String _username = "akshitij";
	private static String _pwd = "016010";
	//private static String _dbUrl = "jdbc:mysql://localhost:3306/epaylaterdb";
	private static String _dbUrl = "jdbc:postgresql://localhost:5432/epaylater";
	
	public Profile retrieve(String key){
		Connection con = null;
		Statement stmt = null;
		ResultSet res = null;
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			con = DriverManager.getConnection(_dbUrl,_username,_pwd);
			stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
			res = stmt.executeQuery("SELECT * FROM Profile WHERE phoneno = \'" + key + "\'");
			//also need to check if count = 1
			if(res.first()){
				Profile p = new Profile(res.getString("firstname"),res.getString("lastname"),res.getString("phoneno"),
						res.getString("emailid"),res.getString("alternateemailid"));
				return p;
			}
			else{
				return null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getMessage());
			return null;
		}finally{
			closeConnection(con,stmt,res);
		}
	}
	
	public int performUpdate(Profile p){
		Connection con = null;
		PreparedStatement preparedStmt = null;
		ResultSet res = null;
		Profile found = retrieve(p.getPhoneNo());
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(found != null){
			
			/*String firstNameUpdate = "firstname = ?";
			String lastNameUpdate = "lastname = ?";
			String emailIdUpdate = "emailid = ?";
			String alternateEmailIdUpdate = "alternateemailid = ?";*/
			
			if(p.getFirstName() != null){
				found.setFirstName(p.getFirstName());
			}
			if(p.getLastName() != null){
				found.setLastName(p.getLastName());
			}
			if(p.getEmailId() != null){
				found.setEmailId(p.getFirstName());
			}
			if(p.getAlternateEmailId() != null){
				found.setAlternateEmailId(p.getAlternateEmailId());
			}
			try {
				
				con = DriverManager.getConnection(_dbUrl,_username,_pwd);
				preparedStmt = con.prepareStatement("UPDATE Profile SET firstname = ?, "
						+ "lastname = ?, "
						+ "emailid = ?, "
						+ "alternateemailid = ? "
						+ "WHERE phoneno = ?");
				preparedStmt.setString(1,found.getFirstName());
				preparedStmt.setString(2,found.getLastName());
				preparedStmt.setString(3,found.getEmailId());
				preparedStmt.setString(4,found.getAlternateEmailId());
				preparedStmt.setString(5,found.getPhoneNo());
				preparedStmt.executeUpdate();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
				e.printStackTrace();
				return 0;
			}finally{
				closeConnection(con, preparedStmt, res);
			}
		}else{
			try {
				con = DriverManager.getConnection(_dbUrl,_username,_pwd);
				preparedStmt = con.prepareStatement("INSERT INTO Profile "
						+ "(phoneno,firstname,lastname,emailid,alternateemailid) VALUES (?,?,?,?,?)");
				preparedStmt.setString(1,p.getPhoneNo());
				preparedStmt.setString(2,p.getFirstName());
				preparedStmt.setString(3,p.getLastName());
				preparedStmt.setString(4,p.getEmailId());
				preparedStmt.setString(5,p.getAlternateEmailId());
				preparedStmt.executeUpdate();
				return 1;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.err.println(e.getMessage());
				e.printStackTrace();
				return 0;
			}finally{
				closeConnection(con, preparedStmt, res);
			}
		}
	}
	public void closeConnection(Connection con, Statement stmt, ResultSet res){
	    try {
            if (res != null) {
                    res.close();
            }
            if (stmt != null) {
                    stmt.close();
            }
            if (con != null) {
                   con.close();
            }
	    } catch (SQLException e) {
	    	System.err.println(e.getMessage());
	    }

	}
}
