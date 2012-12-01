package kr.ac.mju.dislab.user2;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;
import java.util.*;

public class UserDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		// Obtain our environment naming context
		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		// Look up our data source
		return (DataSource) envCtx.lookup("jdbc/WebDB");
	}
	public static User findById(String userid) throws NamingException, SQLException{
		User user = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM users WHERE userid = ?");
			stmt.setString(1, userid);
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				user = new User(rs.getString("id"),
						rs.getString("userid"),
						rs.getString("name"),
						rs.getString("pwd"),
						rs.getString("email"),
						rs.getString("gender"),
						rs.getDate("birth")
						);
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return user;
	}
	
	public static boolean create(User user) throws SQLException, NamingException {
		int result;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement(
					"INSERT INTO users(userid, name, pwd, email, gender,id) " +
					"VALUES(?, ?, ?, ?, ?,?)"
					);
			stmt.setString(1,  user.getUserid());
			stmt.setString(2,  user.getName());
			stmt.setString(3,  user.getPwd());
			stmt.setString(4,  user.getEmail());
			stmt.setString(5,  user.getGender());
			stmt.setString(6,  user.getId());
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
	
	public static boolean update(User user) throws SQLException, NamingException {
		int result;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement(
					"UPDATE users " +
					"SET  userid=?, name=?, email=?, gender=?, " +
					"WHERE id=?"
					);
			stmt.setString(1,  user.getUserid());
			stmt.setString(2,  user.getName());
			stmt.setString(3,  user.getEmail());
			stmt.setString(4,  user.getGender());
			stmt.setString(5,  user.getId());
			
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
	
	public static boolean remove(String id) throws NamingException, SQLException {
		int result;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("DELETE FROM users WHERE id=?");
			stmt.setString(1,  id);
			
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
	public static User login(String userid,String pwd) throws NamingException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		User user=null;
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();
			
			stmt = conn.prepareStatement("SELECT * FROM users WHERE userid = ?");
			stmt.setString(1, userid);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				if(pwd.equals(rs.getString("pwd")))
				{
					user = findById(userid);
				}
				else
				{
				}
			}
			else
			{
			}
			
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return user;	
		}
	public static void addHash(int hash,String gender,String userid) throws NamingException, SQLException
	{	
		int result;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("UPDATE users SET hash= ? WHERE  gender =? AND userid=?");
			stmt.setInt(1,  hash);
			stmt.setString(2, gender);
			stmt.setString(3, userid);
			result = stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
	}
	public static List<User> showAll(String gender) throws NamingException, SQLException
	{
		List<User> list; //반환하는 유저 리스트
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs= null;
		DataSource ds = getDataSource();
		list = new ArrayList<User>();
		int person = 0 ; //db에 저장되있는 사람의 수
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM users WHERE gender= ? ");
			
			if(gender.equals("male"))
			{
				stmt.setString(1, "female");
				rs = stmt.executeQuery();
			}
			else
			{
				stmt.setString(1, "male");
				rs = stmt.executeQuery();
			}
			
			while(rs.next())
			{
				person++;
				int num = (int)(Math.random()*person*10)+1;
				addHash(num,rs.getString("gender"),rs.getString("userid"));
			}
			rs.close();
			rs= null;
			stmt.close();
			stmt=null;
			stmt = conn.prepareStatement("SELECT *FROM users WHERE gender=? ORDER BY hash");
			if(gender.equals("male"))
			{
				stmt.setString(1, "female");
				rs = stmt.executeQuery();
			}
			else
			{
				stmt.setString(1, "male");
				rs = stmt.executeQuery();
			}
			while(rs.next())
			{
				list.add(new User(rs.getString("id"),rs.getString("userid")
					,rs.getString("name"),rs.getString("pwd"),rs.getString("email"),
					rs.getString("gender"),rs.getDate("birth")));
			}
		
			
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return list;
	}
}

