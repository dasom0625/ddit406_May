//JDBC드라이버를 로딩하고 Connection 객체를 생성하여 반환하는 메서드로 구성된 class
//(dbinfo.properties파일의 내용으로 설정하는 방법)

package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil2 {
	static Properties prop; //Properties객체 변수 선언.
	
	static {// 정적(static) 초기화 블럭 => 드라이버 로딩 작업을 수행
		prop = new Properties(); // 2. Properties객체 생성

		// 3.
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;

		// 4. try-catch
		try {
			// 5.
			fin = new FileInputStream(f);
			prop.load(fin);
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("드라이버 로딩 실패!");
			e.printStackTrace();
		}

	}

	// connection 객체를 생성하여 반환하는 메서드
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","pc11","java");
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("pass"));

		} catch (SQLException e) {
			System.out.println("DB 연결 실패!");
			return null;
		}
	}

}
