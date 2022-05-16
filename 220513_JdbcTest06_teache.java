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

public class JdbcTest06_teacher {
	private Connection con = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	private Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException {

		new JdbcTest06_teacher().start();
		
	}

	
//////////////////////////////////////////////////////////////////////////	
//finally 뒤에 올 것.
	private void disConnect() {
		if(con!=null) try {con.close();} catch (SQLException e) { }
		if(ps!=null) try {ps.close();} catch (SQLException e) { }
		if(rs!=null) try {rs.close();} catch (SQLException e) { }
		if(st!=null) try {st.close();} catch (SQLException e) { }
	}
	
//////////////////////////////////////////////////////////////////////////	
//1. 메뉴만들기
	private void start() throws SQLException {
		while(true){
	System.out.println("===========================");	
	System.out.println("1. 자료추가");
	System.out.println("2. 자료삭제");
	System.out.println("3. 전체 자료수정");
	System.out.println("4. 전체출력");
	System.out.println("5. 원하는 부분만 자료수정");
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
	case 5 : partUpdate(); break;
	case 0 : System.exit(0);
	default : System.out.println("작업번호를 다시 입력해주세요.");
	}
}
}

////////////////////////////////////////////////////////////////////////////
private void insert() {
	System.out.println();
	System.out.println("추가할 회원 정보를 입력하세요 .");

	int count = 0;
	String id = null; // 회원ID가 저장 될 변수

	do {

		System.out.print("회원 아이디 > ");
		id = sc.next();

		count = getMemberCount(id);

		if (count > 0) {// 0보다 크면 중복된다는 것.
			System.out.println("존재하는 아이디입니다.");
		}

	} while (count > 0);
	
	System.out.print("등록할 비밀번호 >");
	String pw = sc.next();
	System.out.print("등록할 이름 > ");
	String name = sc.next();
	System.out.print("등록할 전화번호 > ");
	String tel = sc.next();

	sc.nextLine();// 아래에서 사용할 nextLine을 위하여 입력버퍼 비워주기
	System.out.print("등록할 주소 > ");
	String addr = sc.nextLine();// 띄어쓰기까지 들어가야 하므로,

	try {
		con = DBUtil.getConnection();

		String sql = "INSERT INTO MYMEMBER VALUES(?,?,?,?,?)";
		ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ps.setString(2, pw);
		ps.setString(3, name);
		ps.setString(4, tel);
		ps.setString(5, addr);

		int cnt = ps.executeUpdate();

		if (cnt > 0) {
			System.out.println("등록 성공");
		} else
			System.out.println("등록 실패");

	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		disConnect();
	}
}

////////////////////////////////////////////////////////////////////////////
//회원아이디가 있는지 없는지 계속 확인 할 공통 메서드
	private int getMemberCount(String id) {
		int count = 0;
		try {
			con = DBUtil.getConnection();

			String sql = "SELECT COUNT(*) CNT FROM MYMEMBER WHERE MEM_ID = ?";

			ps = con.prepareStatement(sql);
			ps.setString(1, id); // 물음표에 들어갈것 세팅

			rs = ps.executeQuery();

			if (rs.next()) {
				count = rs.getInt("CNT");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
		}
		return count;
	}
	
////////////////////////////////////////////////////////////////////////////
	private void delete() {

		System.out.println();
		System.out.print("삭제할 아이디  > ");
		String id = sc.next();
		try {

			con = DBUtil.getConnection();

			// 쿼리작성
			String sql = "DELETE FROM MYMEMBER WHERE MEM_ID = ?";

			ps = con.prepareStatement(sql);
			// DELETE사용할 때 결과 값

			ps.setString(1, id);

			int cnt = ps.executeUpdate();

			if (cnt > 0) { // 행의 변동이 있는지 없는지
				System.out.println("삭제 성공");
			} else
				System.out.println("삭제 실패");

		} catch (SQLException e) {

		} finally {
			disConnect();
		}

	}


////////////////////////////////////////////////////////////////////////////
	private  void update() {
		String pw = null ; //비밀번호 저장될 변수
		String name = null;//이름이 저장 될 변수
		String tel = null; //전화번호가 저장될 변수
		String addr = null ; //주소가 저장될 변수

		System.out.println();
		System.out.print("변경할 아이디  > ");
		String id = sc.next();
		
		//id가 존재하는지 아닌지 확인 (count가 0이면 없는사람)
		int count = getMemberCount(id);

		if(count == 0) {//등록되어있지 않는 사람
			System.out.println("등록되어있지 않는 아이디입니다.");
			System.out.println("수정작업을 종료합니다.");
			return;
		}
		
			// 아이디 있다는거 확인 끝났으면 변경사항 입력받기
			System.out.print("변경할 비밀번호 >");
			pw = sc.next();
			System.out.print("변경할 이름 > ");
			name = sc.next();
			System.out.print("변경할 전화번호 > ");
			tel = sc.next();
			
			sc.nextLine();
			System.out.print("변경할 주소 > ");
			addr = sc.nextLine();
			
			
			try {
				
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
				disConnect();
			}

		}

	////////////////////////////////////////////////////////////////////////////

	private void partUpdate() {

		System.out.println();
		System.out.print("변경할 아이디  > ");
		String id = sc.next();
		
		//id가 존재하는지 아닌지 확인 (count가 0이면 없는사람)
		int count = getMemberCount(id);

		if(count == 0) {//등록되어있지 않는 사람
			System.out.println("등록되어있지 않는 아이디입니다.");
			System.out.println("수정작업을 종료합니다.");
			return;
		}
		
		//원하는 항목을 선택하기 위한 숫자변수와 수정할 부분의 컬럼명이 들어갈 string변수
		//update mymember set 컬럼명 = 변경할 값 where mem_id = 변경할id;
		int num ; 
		String updateField = null;
		String updateTitle = null; //업데이트할께 전화번호인지, 주소인지를 알려줄 
		
		
		do {
			
			System.out.println();
			System.out.println("1.비밀번호  2.회원이름  3.전화번호  4.주소 ");
			System.out.println("----------------------------------");
			System.out.print("수정할 항목을 선택하세요. > ");
			num = sc.nextInt();
			
			switch(num) {
			case 1 : updateField = "MEM_PW"; updateTitle = "비밀번호"; break;
			case 2 : updateField = "MEM_NAME"; updateTitle = "회원이름"; break;
			case 3 : updateField = "MEM_TEL"; updateTitle = "전화번호"; break;
			case 4 : updateField = "MEM_ADDR"; updateTitle = "주소"; break;
			default : System.out.println("수정 항목을 다시 입력해주세요 ");
			}
			
		}while(num <1 || num > 4);
		
		System.out.println();
		sc.nextLine();//버퍼비우기
		System.out.println("새로운 " + updateTitle + ">> ");
		String updateData = sc.nextLine();
		
		
		try {
			con = DBUtil.getConnection();
			
			String sql = "UPDATE MYMEMBER SET " + updateField + " = ? WHERE MEM_ID = ?";
			
			ps = con.prepareStatement(sql);
			ps.setString(1, updateData);//첫번째 물음표가 문자열이라서 setString
			ps.setString(2, id);
			
			int cnt = ps.executeUpdate();
			
			if(cnt>0) {
				System.out.println("수정 완료");
			}else System.out.println("수정 실패");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	
	////////////////////////////////////////////////////////////////////////////
	private  void print() throws SQLException {
		
		
		System.out.println("----------------------------------------------------");
		System.out.println("ID\tPW\tNAME\tTEL\tADDR");
		System.out.println("----------------------------------------------------");
		try {
			con = DBUtil.getConnection();
			
			//쿼리문 작성
			String sql = "SELECT MEM_ID, MEM_PW, MEM_NAME, MEM_TEL, MEM_ADDR FROM MYMEMBER";
			st = con.createStatement();
			rs  = st.executeQuery(sql);
			
			System.out.println("<<<< 전체 정보 >>>>");
			while(rs.next()) {
				
				
				System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
				System.out.println("------------------");
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		
	}
	
////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	

}
