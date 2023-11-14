package test;
import java.sql.*;
public class ConnectionTest {
	public static void main(String[] args) {
		String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
		String jdbc_url = "jdbc:oracle:thin:@localhost:1521:XE";
		Connection conn = null;
		
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "scott", "tiger");
			
			System.out.println("JDBC 연결 성공!");
		}
		catch(ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 실패!");
			e.printStackTrace();
		}
		catch(SQLException e) {
			System.out.println("JDBC 연결 실패!");
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
				System.out.println("JDBC 연결 종료!");
			}
			catch(Exception e) {
				System.out.println("JDBC 연결 종료 실패!");
				e.printStackTrace();
			}
		}
	}
}