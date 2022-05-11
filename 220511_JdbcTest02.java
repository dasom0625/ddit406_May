//문제) 사용자로부터 Lprod_id값을 입력받아 입력한 값보다 Lprod_id가 큰 자료들을 출력하시오

package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest02 {

	public static void main(String[] args) throws ClassNotFoundException {
		//초기화
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		//try-catch
		try {
			//Driver로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//DB연결
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc11", "java");
			
			//입력받을 값
			System.out.print("id에 넣을 값 > ");
			int id = sc.nextInt();
			
			//SQL문 작성(내가 입력한 값보다 LPROD_ID보다 큰 경우 출력하는 SQL문
			String sql = "SELECT * FROM LPROD WHERE LPROD_ID > " + id;
			
			
			//Statement객체 생성
			st = con.createStatement();
			
			//SQL문 결과 얻어오기
			rs = st.executeQuery(sql);
			
			System.out.println("<< 쿼리문 처리 결과 >>");
			
			while(rs.next()) {
					System.out.println("id보다 큰 LPROD_ID : " + rs.getInt("LPROD_ID"));
					System.out.println("------------------------------------");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();} catch (SQLException e) {	}
			if(st!=null) try {st.close();} catch (SQLException e) {	}
			if(con!=null) try {con.close();} catch (SQLException e) { }
		}
	
		
		
	}
	
}
