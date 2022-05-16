//JDBC드라이버를 로딩하고 Connection 객체를 생성하여 반환하는 메서드로 구성된 class
//(dbinfo.properties파일의 내용으로 설정하는 방법) => ResourceBundle객체를 이용하여 처리하기.
package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DBUtil3 {
	
	static ResourceBundle bundle; //1.ResourceBundle객체 변수 선언
	
	static {
	
		//2. 객체생성
		bundle = ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
		
			return DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
		
		} catch (SQLException e) {
			System.out.println("DB 연결 실패!");
			return null;
		}
	}
	

}
