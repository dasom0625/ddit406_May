/*
회원관리를 하는 프로그램을 작성하시오(MYMEMBER테이블 이용)

아래 메뉴의 기능을 모두 구현하시오.(CRUD기능 구현하기 -> DB로 치면, INSERT / SELECT / UPDATE / DELETE)

메뉴 예시)
======================
1. 자료추가 INSERT
2. 자료삭제 DELETE
3. 자료수정 UPDATE
4. 전체자료출력 SELECT
0. 작업끝
======================

처리조건 )
1) '자료추가'에서 '회원ID'는 중복되지 앟는다(중복되면 다시 입력받는다.)
2) '자료삭제'는 '회원ID'를 입력받아서 처리한다.
3) '자료수정'에서 '회원ID'는 변경되지 않는다.

 */

package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

public class JdbcTest06 {
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {

		new JdbcTest06().start();
		
	}

	
//////////////////////////////////////////////////////////////////////////	
//1. 메뉴만들기
	private void start() throws SQLException {

	System.out.println("===========================");	
	System.out.println("1. 자료추가");
	System.out.println("2. 자료삭제");
	System.out.println("3. 자료수정");
	System.out.println("4. 전체출력");
	System.out.println("0. 작업 끝");
	System.out.println("===========================");	
	System.out.println();
	System.out.print("작업할 번호를 입력하세요 > ");
	int input = sc.nextInt();
	System.out.println();
	
	switch(input) {
	
	case 1 : insert(); break;
	case 2 : delete(); break;
	case 3 : update(); break;
	case 4 : print(); break;
	case 0 : System.exit(0);
	default : System.out.println("작업번호를 다시 입력해주세요.");
	}

}

////////////////////////////////////////////////////////////////////////////
	private  void insert(){
		try {
			
		
		con = DBUtil.getConnection();
		
		String id = null; //아이디가 저장될 변수
		String pw = null ; //비밀번호 저장될 변수
		String name = null;//이름이 저장 될 변수
		String tel = null; //전화번호가 저장될 변수
		String addr = null ; //주소가 저장될 변수
		int count = 0 ; //입력한 이름이 DB에 저장된 개수를 저장할 변수
		do {
			System.out.print("등록할 아이디  > ");
			id = sc.next();
			
			String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);	//첫번째물음표에 등록
			
			rs = ps.executeQuery(); //select쿼리니까 executeQuery사용
			
			if(rs.next()) {
				count = rs.getInt("CNT");
			}
			if(count >0) {//만약 중복된다면...
				System.out.println("이미 등록된 아이디입니다. 다시 입력해주세요.");
			}else System.out.println("등록 가능한 아이디입니다.");
		}while(count > 0);	//등록되어있지 않으면 이름을 등록하고 do-while문을 빠져나옴.
		
		//아이디 중복 확인 끝났으면 다른것도 등록받기
		System.out.print("등록할 비밀번호 >");
		pw = sc.next();
		System.out.print("등록할 이름 > ");
		name = sc.next();
		System.out.print("등록할 전화번호 > ");
		tel = sc.next();
		System.out.print("등록할 주소 > ");
		addr = sc.next();
		
		//전체 등록하기
		String sql = "INSERT INTO MYMEMBER VALUES(?,?,?,?,?)";	
		ps = con.prepareStatement(sql);
		ps.setString(1,id);
		ps.setString(2,pw);
		ps.setString(3,name);
		ps.setString(4,tel);
		ps.setString(5,addr);
		
		int cnt = ps.executeUpdate();
		
		if(cnt>0) {
			System.out.println("성공적으로 등록되었습니다.");
		}else System.out.println("등록에 실패하였습니다.");


		} catch (SQLException e) {

		}finally {
			if(con!=null) try {con.close();} catch (SQLException e) { }
			if(ps!=null) try {ps.close();} catch (SQLException e) { }
			if(rs!=null) try {rs.close();} catch (SQLException e) { }
			if(st!=null) try {st.close();} catch (SQLException e) { }
		}
		

}

////////////////////////////////////////////////////////////////////////////
	private  void delete() {
		
		try {
			con = DBUtil.getConnection();
			
			int count = 0 ; //입력한 이름이 DB에 저장된 개수를 저장할 변수
			do {
				System.out.print("삭제할 아이디  > ");
				String id = sc.next();

				String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, id); // 첫번째물음표에 등록

				rs = ps.executeQuery(); // select쿼리니까 executeQuery사용

				if (rs.next()) {
					count = rs.getInt("CNT");
				}
				if (count < 0) {// 만약 없는 아이디라면...
					System.out.println("등록되어있지 않은 아이디입니다. 다시 입력해주세요.");
				} else
					System.out.println("삭제 가능한 아이디입니다.");
			} while (count < 0); // 등록되어있으면 변경하기위해 do-while문을 빠져나옴.
			
			
			//쿼리작성
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";
			
			ps = con.prepareStatement(sql);
			//DELETE사용할 때 결과 값
			
			int cnt = ps.executeUpdate(); 
			
			if(cnt>0) {
				System.out.println("성공적으로 삭제되었습니다.");
			}else System.out.println("삭제에 실패하였습니다.");
			
			
		} catch (SQLException e) {
			
		}finally {
			if(con!=null) try {con.close();} catch (SQLException e) { }
			if(ps!=null) try {ps.close();} catch (SQLException e) { }
			if(rs!=null) try {rs.close();} catch (SQLException e) { }
		}
		
		
	}


////////////////////////////////////////////////////////////////////////////
	private  void update() {
		try {
			
			String id = null; //아이디가 저장될 변수
			String pw = null ; //비밀번호 저장될 변수
			String name = null;//이름이 저장 될 변수
			String tel = null; //전화번호가 저장될 변수
			String addr = null ; //주소가 저장될 변수
			int count = 0 ; //입력한 이름이 DB에 저장된 개수를 저장할 변수
			do {
				System.out.print("변경할 아이디  > ");
				id = sc.next();

				String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, id); // 첫번째물음표에 등록

				rs = ps.executeQuery(); // select쿼리니까 executeQuery사용

				if (rs.next()) {
					count = rs.getInt("CNT");
				}
				if (count < 0) {// 만약 없는 아이디라면...
					System.out.println("등록되어있지 않은 아이디입니다. 다시 입력해주세요.");
				} else
					System.out.println("변경 가능한 아이디입니다.");
			} while (count < 0); // 등록되어있으면 변경하기위해 do-while문을 빠져나옴.

			// 아이디 있다는거 확인 끝났으면 변경사항 입력받기
			System.out.print("변경할 비밀번호 >");
			pw = sc.next();
			System.out.print("변경할 이름 > ");
			name = sc.next();
			System.out.print("변경할 전화번호 > ");
			tel = sc.next();
			System.out.print("변경할 주소 > ");
			addr = sc.next();
			
			con = DBUtil.getConnection();

			String sql = "UPDATE MYMEMBER SET MEM_PW = ?, MEM_NAME = ? , MEM_TEL = ? , MEM_ADDR = ? WHERE MEM_ID = ? ";

			ps = con.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setString(2, name);
			ps.setString(3, tel);
			ps.setString(4, addr);
			ps.setString(5, id);

			int cnt = ps.executeUpdate();

			if (cnt > 0) {
				System.out.println("성공적으로 변경되었습니다.");
			} else
				System.out.println("변경에 실패하였습니다.");

		} catch (SQLException e) {

			}finally {
				if(con!=null) try {con.close();} catch (SQLException e) { }
				if(ps!=null) try {ps.close();} catch (SQLException e) { }
				if(rs!=null) try {rs.close();} catch (SQLException e) { }
				if(st!=null) try {st.close();} catch (SQLException e) { }
			}

		}

////////////////////////////////////////////////////////////////////////////
	private  void print() throws SQLException {
		
		try {
			con = DBUtil.getConnection();
			
			//쿼리문 작성
			String sql = "SELECT MEM_ID, MEM_PW, MEM_NAME, MEM_TEL, MEM_ADDR FROM MYMEMBER";
			st = con.createStatement();
			rs  = st.executeQuery(sql);
			
			System.out.println("<<<< 전체 정보 >>>>");
			while(rs.next()) {
				
				System.out.println("MEM_ID : " + rs.getString(1));
				System.out.println("MEM_PW : " + rs.getString(2));
				System.out.println("MEM_NAME : " + rs.getString(3));
				System.out.println("MEM_TEL : " + rs.getString(4));
				System.out.println("MEM_ADDR : " + rs.getString(5));
				System.out.println("------------------");
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();} catch (SQLException e) {	}
			if(st!=null) try {st.close();} catch (SQLException e) {	}
			if(con!=null) try {con.close();} catch (SQLException e) {	}
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	

}
