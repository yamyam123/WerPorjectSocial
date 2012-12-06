package kr.ac.mju.dislab.user2;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import java.util.*;
public class ProblemDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		// Obtain our environment naming context
		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		// Look up our data source
		return (DataSource) envCtx.lookup("jdbc/WebDB");
	}
	public static boolean create(Problem problem) throws SQLException, NamingException {
		int result;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement(
					"INSERT INTO problem(title, pnumber, first, second, third, fourth, answer,id,rid) " +
					"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)"
					);
			stmt.setString(1, problem.getTitle());
			stmt.setInt(2,  problem.getpNumber());
			stmt.setString(3,  problem.getFirst());
			stmt.setString(4,  problem.getSecond());
			stmt.setString(5,  problem.getThird());
			stmt.setString(6,  problem.getFourth());
			stmt.setInt(7,  problem.getAnswer());
			stmt.setString(8, problem.getId());
			stmt.setString(9, problem.getrId());
			// 수행
			result = stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return (result == 1);
	}
	public static Problem findPromblem(String id,String rId)throws SQLException, NamingException {
		Problem problem = new Problem();
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs= null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Problem WHERE id = ? AND rid=?");
			stmt.setString(1, rId);
			stmt.setString(2, id);
			rs = stmt.executeQuery();

			if(rs.next())
			{
				problem = new Problem(rs.getString("id"),
						rs.getString("rid"),
						rs.getString("title"),
						rs.getInt("pnumber"),
						rs.getString("first"),
						rs.getString("second"),
						rs.getString("third"),
						rs.getString("fourth"),
						rs.getInt("hpnumber"),
						rs.getInt("answer"),
						rs.getInt("hpnumber")
						
						);
			}
	
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return problem;
	}
	public static void setPhoneNumber(String id,String number)throws NamingException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("UPDATE problem set hpnumber=? WHERE id = ?");
			stmt.setString(1, number);
			stmt.setString(2, id);
			
			// 수행
			result = stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}

	}
	public static void setPublicHp(String id,String rId,int number)throws NamingException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("UPDATE problem set publichp=? WHERE id = ? AND rid=?");
			stmt.setInt(1, number);
			stmt.setString(2, id);
			stmt.setString(3, rId);
			// 수행
			result = stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}

	}
	public static Problem deletePromblem(String id,String rId)throws SQLException, NamingException {
		Problem problem = new Problem();
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs= null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("DELETE FROM Problem WHERE id = ? AND rid=?");
			stmt.setString(1, rId);
			stmt.setString(2, id);
			stmt.executeUpdate();
	
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return problem;
	}
}
