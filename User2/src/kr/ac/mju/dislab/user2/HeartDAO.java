package kr.ac.mju.dislab.user2;

import java.sql.*;

import javax.naming.*;
import javax.sql.*;

import java.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HeartDAO {
	public static DataSource getDataSource() throws NamingException {
		Context initCtx = null;
		Context envCtx = null;

		// Obtain our environment naming context
		initCtx = new InitialContext();
		envCtx = (Context) initCtx.lookup("java:comp/env");

		// Look up our data source
		return (DataSource) envCtx.lookup("jdbc/WebDB");
	}
	public static void GiveHaert(String gid,String rid,String name) throws NamingException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		GregorianCalendar gc = new GregorianCalendar();
	    long milis = gc.getTimeInMillis();// 밀리초로 바꿔준다 
	    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss"); // 기본 데이타베이스 저장 타입
        Date d = gc.getTime(); // Date -> util 패키지
        String str = sf.format(d);
        
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement(
					"INSERT INTO gheart(id, givetime, rid, rname, finish) " +
					"VALUES(?, ?, ?, ?, ?)"
					);
			stmt.setString(1,  gid);
			stmt.setString(2,  str);
			stmt.setString(3,  rid);
			stmt.setString(4, name);
			stmt.setInt(5,  0);
			
			// 수행
			stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
	}
	
	public static void ReceiveHeart(String gid,String rid,String name) throws NamingException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		GregorianCalendar gc = new GregorianCalendar();
	    SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss"); // 기본 데이타베이스 저장 타입
        Date d = gc.getTime(); // Date -> util 패키지
        String str = sf.format(d);
        
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement(
					"INSERT INTO rheart(id, receivetime, gid, gname, finish) " +
					"VALUES(?, ?, ?, ?, ?)"
					);
			stmt.setString(1,  rid);
			stmt.setString(2,  str);
			stmt.setString(3,  gid);
			stmt.setString(4, name);
			stmt.setInt(5, 0);
			
			// 수행
			stmt.executeUpdate();
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
	}
	
	public static boolean alreadySend(String gid,String rid) throws NamingException, SQLException{
		boolean send = false;//보낸적있는가?
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		DataSource ds = getDataSource();
		
		try {
			conn = ds.getConnection();

			// 질의 준비
			stmt = conn.prepareStatement("SELECT * FROM gheart WHERE id = ? AND rid = ?");
			stmt.setString(1, gid);
			stmt.setString(2, rid);
			
			// 수행
			rs = stmt.executeQuery();

			if (rs.next()) {
				send = true;
			}	
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}

		return send;
	}
	public static List<Gheart> showgAll(String id) throws NamingException, SQLException
	{
		List<Gheart> list; //반환하는 유저 리스트
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs= null;
		DataSource ds = getDataSource();
		list = new ArrayList<Gheart>();
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM gheart WHERE id = ? ");
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			while(rs.next())
			{
				list.add(new Gheart(rs.getString("givetime"), rs.getString("id"),
						rs.getString("rid"), rs.getString("rname"), rs.getInt("finish")));
			}
	
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return list;
	}
	public static List<Rheart> showrAll(String id) throws NamingException, SQLException
	{
		List<Rheart> list; //반환하는 유저 리스트
		Connection conn = null;
		PreparedStatement stmt=null;
		ResultSet rs= null;
		DataSource ds = getDataSource();
		list = new ArrayList<Rheart>();
		
		try {
			conn = ds.getConnection();
			stmt = conn.prepareStatement("SELECT * FROM rheart WHERE id = ? ");
			stmt.setString(1, id);
			rs = stmt.executeQuery();

			while(rs.next())
			{
				list.add(new Rheart(rs.getString("id"), rs.getString("receivetime"),
						rs.getString("gid"), rs.getString("gname"), rs.getInt("finish")));
			}
	
		} finally {
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		
		return list;
	}
	
}
