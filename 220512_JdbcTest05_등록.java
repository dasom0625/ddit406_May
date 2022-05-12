/*
- lprod_gu와 lprod_nm은 사용자로부터 직접 입력받아 처리하고, 
  lprod_id는 현재의 lprod_id중 제일 큰값보다 1크게 한다.
  
- 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
  (select count(*) from lprod where lprod_gu = P101; 가 있는지 없는지..)
  =>DB에 등록되어있는지 아닌지 if문쓰고, 등록되어있으면 다시 입력하게 하기.
 
 */
package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest05 {

	public static void main(String[] args) throws ClassNotFoundException {
//내방법 1.
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps  = null;
		Scanner sc = new Scanner(System.in);
		
		String gu = null;
		String nm = null;
		String id = null;
		
		try {
			
			//1. Driver로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB연결하기=>연결이 되면, Connection 객체가 생성됨
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc11", "java");
			
			//3. 입력받기
			while(true) {
			System.out.print("lprod_gu를 입력하세요 > ");
			gu = sc.nextLine();
			
			String sql = "select count(*) from lprod where lprod_gu = '" + gu +"'";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			int temp = 0;
			
			while(rs.next()) {
			temp = rs.getInt(1);
			}
			
			if (temp == 0 ) {
				break;
			}
			System.out.println("이미 존재하는 lprod_gu입니다.");

		}
			
		System.out.print("lprod_nm을 입력하세요 > ");
		nm = sc.next();	
    			//4. 비교하기
			String sql = "INSERT INTO LPROD VALUES ((SELECT MAX(LPROD_ID)+1 FROM LPROD),?,?)";//
			//ps에 sql넘겨주기
			ps = con.prepareStatement(sql);
			
			//쿼리문 ?에 들어갈 값들 세팅
			
			ps.setString(1, gu);
			ps.setString(2, nm);
			
			//데이터 세팅이 완료되면 쿼리문을 실행.
			int cnt = ps.executeUpdate();
			
			System.out.println("반환 값 : " + cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) try {con.close();} catch (SQLException e) { }
			if(ps!=null) try {ps.close();} catch (SQLException e) { }
			if(rs!=null) try {rs.close();} catch (SQLException e) { }
			if(st!=null) try {st.close();} catch (SQLException e) { }
		}
    	}

}
    
/////////////////////////////////////////////////////////////////////////////////////////////////////
 /*
- lprod_gu와 lprod_nm은 사용자로부터 직접 입력받아 처리하고, 
  lprod_id는 현재의 lprod_id중 제일 큰값보다 1크게 한다.
  
- 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
  (select count(*) from lprod where lprod_gu = P101; 가 있는지 없는지..)
  =>DB에 등록되어있는지 아닌지 if문쓰고, 등록되어있으면 다시 입력하게 하기.
 
 */
package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest05 {

	public static void main(String[] args) throws ClassNotFoundException {
//내방법2.
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps  = null;
		Scanner sc = new Scanner(System.in);
		
		String gu = null;
		String nm = null;
		String id = null;
		
		try {
			
			//1. Driver로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB연결하기=>연결이 되면, Connection 객체가 생성됨
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc11", "java");
			
			//3. 입력받기
			while(true) {
			System.out.print("lprod_gu를 입력하세요 > ");
			gu = sc.nextLine();
			
			String sql = "select count(*) from lprod where lprod_gu = '" + gu +"'";
			
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			int temp = 0;
			
			while(rs.next()) {
			temp = rs.getInt(1);
			}
			
			if (temp == 0 ) {
				break;
			}
			System.out.println("이미 존재하는 lprod_gu입니다.");

		}
			
		System.out.print("lprod_nm을 입력하세요 > ");
		nm = sc.next();	
			
			//4. 비교하기
			
			String sql = "INSERT INTO LPROD VALUES (?,?,?)";
			//ps에 sql넘겨주기
			ps = con.prepareStatement(sql);

			//쿼리문 ?에 들어갈 값들 세팅
			String str = "SELECT MAX(LPROD_ID)+1 M FROM LPROD";
			
			st = con.createStatement();
			rs = st.executeQuery(str);
			
			String temp = null;
			
			while(rs.next()) {
			temp = rs.getString(1);
			}
			
			
			ps.setString(1,temp);
			ps.setString(2, gu);
			ps.setString(3, nm);
			
			//데이터 세팅이 완료되면 쿼리문을 실행.
			int cnt = ps.executeUpdate();
			
			System.out.println("반환 값 : " + cnt);   
    		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null) try {con.close();} catch (SQLException e) { }
			if(ps!=null) try {ps.close();} catch (SQLException e) { }
			if(rs!=null) try {rs.close();} catch (SQLException e) { }
			if(st!=null) try {st.close();} catch (SQLException e) { }
		}
    	}

}
    
/////////////////////////////////////////////////////////////////////////////////////////////////////
  /*
- lprod_gu와 lprod_nm은 사용자로부터 직접 입력받아 처리하고, 
  lprod_id는 현재의 lprod_id중 제일 큰값보다 1크게 한다.
  
- 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력받아서 처리한다.
  (select count(*) from lprod where lprod_gu = P101; 가 있는지 없는지..)
  =>DB에 등록되어있는지 아닌지 if문쓰고, 등록되어있으면 다시 입력하게 하기.
 
 */
package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest05 {

	public static void main(String[] args) throws ClassNotFoundException {   
    
 //쌤방법
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PreparedStatement ps  = null;
		Scanner sc = new Scanner(System.in);
		
		
		try {
			//1. Driver로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//2. DB연결하기=>연결이 되면, Connection 객체가 생성됨
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc11", "java");

			//3.lprod_Id에서 가장 큰 값 찾기
			String sql = "SELECT MAX(LPROD_ID) MAXI FROM LPROD";
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			//4. 제일큰 lprod_id가 저장 될 변수
			int maxnum = 0;
			//5. select한 결과가 1개의 레코드일 경우에는 반복문보다 if문으로 데이터를 확인한다.
			if(rs.next()) {
//				maxnum = rs.getInt(1); //첫번째 방법. 컬럼번호를 이용
//				maxnum = rs.getInt("MAX(LPROD_ID)"); //두번째 방법. 식을 적기(alias가 없는경우에만)
				maxnum = rs.getInt("MAXI"); //세번째 방법. ALIAS를 적기
				
			}
			maxnum++;//있는 값 중에 max를 찾으면 그 값에 +1해주기.(새로 저장될..)
			
			//6.입력받은 lprod_gu(상품분류코드)가 이미 등록되어있으면 다시 입력받아 처리한다.=>반복문 사용
			String gu; 		//상품분류 코드가 저장 될 변수 선언
			int count = 0; 	//입력한 상품 분류 코드가 DB에 저장된 개수를 저장할 변수
			
			do {
				System.out.print("상품 분류 코드를 입력하시오 > ");
				gu = sc.next();
				
				String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";//7. ? 가 있으므로 preparedStatement를 쓰겟다는 뜻.
				ps = con.prepareStatement(sql2);
				ps.setNString(1, gu);
				
				
				rs = ps.executeQuery();//8. select쿼리니까..
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				if(count > 0) { //9. 중복될 경우...
					System.out.println("입력한 상품 분류 코드 : " + gu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다시 입력해주세요.");
				}
				
			}while(count>0);
			
			//10. 상품 분류명 입력받기
			System.out.print("상품분류명 입력 : ");
			String nm = sc.next();
			
			//11.등록하기 
			String sql3 = "INSERT INTO LPROD VALUES (?,?,?)";
			
			ps = con.prepareStatement(sql3);
			ps.setInt(1, maxnum);
			ps.setNString(2, gu);
			ps.setNString(3, nm);
			
			int cnt = ps.executeUpdate();
			
			if(cnt>0) {//12.잘 등록되었는지 확인
				System.out.println("insert 성공");
			}else System.out.println("insert 실패");
			
		} catch (Exception e) {
			// TODO: handle exception
			}finally {//13. 반환
			if(con!=null) try {con.close();} catch (SQLException e) { }
			if(ps!=null) try {ps.close();} catch (SQLException e) { }
			if(rs!=null) try {rs.close();} catch (SQLException e) { }
			if(st!=null) try {st.close();} catch (SQLException e) { }
		}
		
		
		
	}

}
