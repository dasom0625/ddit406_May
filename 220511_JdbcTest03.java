//lprod_id값을 두개 입력받아서 두 값 중 작은 값부터 큰값 사이의 자료를 출력하시오.


package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest03 {

	public static void main(String[] args) throws ClassNotFoundException {

		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		String sql = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc11", "java");

			//값 두개 입력받기
			System.out.print("정수값 1 > ");
			int n1 = sc.nextInt();
			System.out.println();
			System.out.print("정수값 2 > ");
			int n2 = sc.nextInt();
			System.out.println();

			
			if(n1<n2) {
				sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN " + n1 + " AND " + n2;
			}
			else {
				sql = "SELECT * FROM LPROD WHERE LPROD_ID BETWEEN " + n2 + " AND " + n1;
			}
//비교문방법 2			
//			if(n1>n2) {
//				int temp =n1;
//				n1=n2;
//				n2=temp;
//			}
			//SQL질의문 작성
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			while(rs.next()) {
				
				System.out.println("n1 과 n2사이의 LPROD_ID : " + rs.getInt("LPROD_ID"));
				System.out.println("============================================");
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 자원 반납해주기
			if(rs!=null) try {rs.close();} catch (SQLException e) {	}
			if(st!=null) try {st.close();} catch (SQLException e) {	}
			if(con!=null) try {con.close();} catch (SQLException e) { }
		}
	}

}
