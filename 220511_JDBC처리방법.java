//JDBC (Java DataBase Connectivity)라이브러리를 이용한 DB자료 처리하기

/*

데이터베이스 처리 순서

1. JDBC드라이버 로딩 => JDBC라이버르러를 사용할 수 있도록 메모리를 읽어들이는 작업
 	=>Class.forName("oracle.jdbc.driver.OracleDriver");  
	=> 드라이버를 로딩해주는 명령어.
	       참고로, JDBC버전4이상에서는 getConnection()메서드에서 자동으로 로딩해주기때문에 생략할 수 있다.
	   (그렇지만...생략하는 버릇을 들이면 안되니까..? 생략하지 않고 사용할거에용~!)

2. DB에 접속하기 
	=> DriverManager.getConnection()메서드를 이용.)
	=> 접속이 완료되면 Connection이라는 객체가 반환된다.
 
3. 질의 
	=>SQL문장을 DB서버로 보내서 결과를 얻어온다.
	  (Statement객체나 PreparedStatement객체를 이용하여 작업)

4. 얻어온 결과를 가지고 처리 
	=> 질의 결과를 받아서 원하는 작업을 수행한다.
	1) SQL문이 SELECT문일 경우, SELECT한 결과가 ResultSet객체에 저장되어 반환된다.
	   SELECT문을 실행할때는 st.executeQuery()메서드를 사용
	
	2) SQL문이 SELECT문이 아닐경우(ex.INSERT, UPDATE, DLELTE등..), 정수값이 반환된다.
	  (보통 이 정수값은 실행에 성공한 레코드 수를 의미한다.)
      - SLELCT문 외에것을 실행할때는 ps.executeUpdate()메서드를 사용
	  - 쿼리문에서 데이터가 들어갈 자리를 물음표(?)로 표시하여 작성한다.
	  - PreparedStatement객체 생성 =>사용할 쿼리문을 인수값으로 넘겨준다.
	    (ex.ps = con.prepareStatement(sql);)
	  - 쿼리문의 물음표(?)자리에 들어갈 데이터를 세팅한다.
		형식 ) ps.set자료형이름(물음표 번호, 세팅할 데이터)
		(ex.ps.setString(1, bankNo);
			ps.setString(2, bankName);
			ps.setString(3, userName);)
			
5. 사용한 자원들 반납
	=>close() 메서드 사용.
 */

package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest01 {

	public static void main(String[] args) {

		
		//1. DB작업에 필요한 객체 변수 선언
		//1)커넥션이 저장될 변수
		Connection conn = null;//초기화
		//2)statement와 prestatement
		Statement stmt = null;//초기화
		//3)sellect쓸꺼니까 resultset객체변수
		ResultSet rs = null;
		
		//2. try-catch
		try {
			//3. Driver로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//4. DB연결하기=>연결이 되면, Connection 객체가 생성됨
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc11", "java");
			
			//5. 질의 
			//5-1. SQL문 작성
			String sql = "select lprod_id, lprod_gu gu, lprod_nm nm from lprod";
			
			//5-2. Statement객체 생성 =>Statement객체 : 질의를 서버를 전송하고 결과를 얻어오는 객체이다.(Connection 객체를 이용해서 생성한다.)
			stmt = conn.createStatement();
			
			//5-3. SQL문을 서버로 보내서 결과를 얻어온다.
			//	  (실행할 SQL문이 SELECT문이기 때문에 처리 결과가 ResultSet객체에 저장되어 반환된다.)
			rs = stmt.executeQuery(sql);
			
			//6. 결과 처리하기 => 한 레코드(튜플)씩 화면에 출력하기
			//			  => SELECT한 결과가 저장된 ResultSet객체에서 데이터를 차례로 꺼내오려면 반복문과 next()메서드를 이용한다.
			System.out.println("<< 쿼리문 처리 결과 >>");
			
			while(rs.next()) {	
//			rs.next() : ResultSet객체의 데이터를 가리키는 포인터를 다음번째 레코드로 이동시키고,
//						그곳에 데이터가 있으면 true, 없으면 false를 반환시킨다.
//						포인터가 가리키는 곳의 자료를 가져오는 방법
//						형식 1) rs.get자료형이름("컬럼명" 또는 "alias명"); => ex)rs.getInt("name");
//						형식 2) rs.get자료형이름("컬럼번호"); => 컬럼번호는 1번부터 시작됨.
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));	//lprod_id라는 컬럼명으로 지정한것.
				System.out.println("Lprod_gu : " + rs.getString(2)); //2번째 컬럼이라는 컬럼번호로 지정한것.
				System.out.println("Lprod_nm : " + rs.getString("nm")); //컬럼명의 Alias가 nm이라고 지정한것.
				System.out.println("---------------------------------");
				
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//7. 자원 반납해주기
			if(rs!=null) try {rs.close();} catch (SQLException e) {	}
			if(stmt!=null) try {stmt.close();} catch (SQLException e) {	}
			if(conn!=null) try {conn.close();} catch (SQLException e) {	}
		}
		
	}

}
