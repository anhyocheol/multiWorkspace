package model;

import java.sql.*;
import java.util.*;

public class MemberDAO {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String sql;
	
	public MemberDAO() {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public MemberDO getMember(String id) {
		MemberDO member = null;
		this.sql = "select id, passwd, name, to_char(regdate, 'YYYY-MM-DD HH24:MI:SS') as regdate, grade " + 
				   "from member where id = ?";
		
		try {
			this.pstmt = conn.prepareStatement(sql);
			
			this.pstmt.setString(1, id);
			rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				member = new MemberDO();
				
				member.setId(this.rs.getString("id"));
				member.setPasswd(this.rs.getString("passwd"));
				member.setName(this.rs.getString("name"));
				member.setRegdate(this.rs.getString("regdate"));
				member.setGrade(this.rs.getInt("grade"));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();					
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}		
		
		return member;
	}
	
	public int insertMember(MemberDO member) throws Exception {
		int rowCount = 0;
		boolean isIdDuplicate = false;
		
		try {
			this.conn.setAutoCommit(false);
			
			this.sql = "select id from member where id = ?";
			pstmt = conn.prepareStatement(sql);			
			pstmt.setString(1, member.getId());			
			this.rs = pstmt.executeQuery();
			
			if(!rs.next()) {
				this.sql = "insert into member (id, passwd, name) values (?, ?, ?)";
				pstmt = conn.prepareStatement(sql);			
				pstmt.setString(1, member.getId());
				pstmt.setString(2, member.getPasswd());
				pstmt.setString(3, member.getName());
				
				rowCount = pstmt.executeUpdate();
				this.conn.commit();
			}
			else {
				isIdDuplicate = true;
				this.conn.rollback();
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {			
			try {
				this.conn.setAutoCommit(true);
				
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		if(isIdDuplicate) {
			throw new Exception("아이디가 중복되었습니다.");
		}

		return rowCount;
	}

	public ArrayList<MemberDO> getAllMembers() {
		ArrayList<MemberDO> memberList = new ArrayList<MemberDO>();
		this.sql = "select id, name, to_char(regdate, 'YYYY-MM-DD HH24:MI:SS') as regdate " + 
				   "from member order by regdate";
		
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			MemberDO member = null;
			
			while(rs.next()) {
				member = new MemberDO();
				
				member.setId(rs.getString("id"));
				member.setName(rs.getString("name"));
				member.setRegdate(rs.getString("regdate"));
				
				memberList.add(member);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {			
			try {
				if(!stmt.isClosed()) {
					stmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		return memberList;
	}
	
	public int changePasswd(MemberDO member) {
		int rowCount = 0;
		this.sql = "update member set passwd = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getNewPasswd());
			pstmt.setString(2, member.getId());
			rowCount = pstmt.executeUpdate(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowCount;
	}
	
	public int changeGrade(MemberDO member) {
		int rowCount = 0;
		this.sql = "update member set grade = ? where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, member.getGrade());
			pstmt.setString(2, member.getId());
			rowCount = pstmt.executeUpdate(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowCount;
	}
	
	public int deleteMember(String id) {
		int rowCount = 0;
		this.sql = "delete from member where id = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rowCount = pstmt.executeUpdate(); 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(!pstmt.isClosed()) {
					pstmt.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return rowCount;
	}
	
	public void closeConn() {
		try {
			if(!conn.isClosed()) {
				conn.close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}

















