package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) throws ClassNotFoundException {

		
		Scanner sc = new Scanner(System.in);

		Connection con = null;
		Statement st = null;
		PreparedStatement ps = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc11", "java");
			
			System.out.println("계좌 번호 정보 추가하기");
			
			System.out.print("계좌번호 : ");
			String bankNo = sc.next();
			
			System.out.print("은행이름 : ");
			String bankName = sc.next();
			
			System.out.print("예금주  : ");
			String userName = sc.next();
			
//			// < Statement객체를 이용하여 데이터 추가하기 >
//			String sql = "\r\n" + 
//					"INSERT INTO BANKINFO"
//					+ " (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
//					+ " VALUES ('" + bankNo + "', '"+ bankName +"', '" + userName + "', SYSDATE)";
//			
//			st = con.createStatement();
//			
//			//SELECT문을 실행할때는 st.executeQuery()메서드를 사용
//			//SLELCT문 외에것을 실행할때는 ps.executeUpdate()메서드를 사용
//			//ps.executeUpdate()메서듸의 반환값 => 작업에 성공한 레코드수(튜플)이 된다.
//			int cnt = st.executeUpdate(sql);
//			
//			System.out.println("반환값 : " + cnt);
			
////////////////////////////////////////////////////////////////////////////////////////////////////
			
			// < PrepareStatemet객체를 이용하여 데이터 추가하기 >
			//쿼리문에서 데이터가 들어갈 자리를 물음표(?)로 표시하여 작성한다.
			String sql = "\r\n" + 
					"INSERT INTO BANKINFO"
					+ " (BANK_NO, BANK_NAME, BANK_USER_NAME, BANK_DATE)"
					+ " VALUES (?, ?, ?, SYSDATE)";
			//PreparedStatement객체 생성 =>사용할 쿼리문을 인수값으로 넘겨준다.
			ps = con.prepareStatement(sql);

			//쿼리문의 물음표(?)자리에 들어갈 데이터를 세팅한다.
			//형식 ) ps.set자료형이름(물음표 번호, 세팅할 데이터)
			ps.setString(1, bankNo);
			ps.setString(2, bankName);
			ps.setString(3, userName);
			
			//데이터 세팅이 완료되면 쿼리문을 실행한다.
			int cnt = ps.executeUpdate();
			
			System.out.println("반환 값 : " + cnt);
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}finally{
		if(st!=null) try {st.close();} catch (SQLException e) {	}
		if(con!=null) try {con.close();} catch (SQLException e) { }
		if(ps!=null) try {ps.close();} catch (SQLException e) { }

		}//빌리진 강아지 보고싶다. . .
		
		
		
		
		//저녁에 밥통령 갔다올테니 그리워하지 말 것
	}

}
