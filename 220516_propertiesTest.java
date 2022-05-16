package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {

	public static void main(String[] args) {

		//Properties 객체를 이용하여 파일 내용을 읽어와 출력하기
		
		//1.Properties객체 생성
		Properties prop = new Properties();//key와 value모두 string밖에 못씀
		
		//2. 읽어올 파일 정보를 갖는 File객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		//3. try-catch
		try {
			//4. 입력용 스트림 객체 생성
			fin = new FileInputStream(f);
			
			//5. 입력용 스트림을 이용하여 파일 내용을 일거와 Properties객체에 세팅하기
			prop.load(fin); //=>파일 내용을 읽어와 key 값과 value값을 분류한 후 Properties객체에 추가한다.
			
			//6. 읽어온 정보 출력하기(dbinfo.properties에 있는 내용들 출력하기)
			System.out.println("driver : "+prop.getProperty("driver"));
			System.out.println("url : "+prop.getProperty("url"));
			System.out.println("user : "+prop.getProperty("user"));
			System.out.println("pass : "+prop.getProperty("pass"));
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다.");
			e.printStackTrace();
		}
		
	}

}
