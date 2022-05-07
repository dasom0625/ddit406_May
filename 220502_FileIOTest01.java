//1.FileInputStream을 이용한 파일 내용 읽기




package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {

//1.
		//1.try-catch만들기
		try {
			//2.FileInputStream객체 생성 => 이때, 읽어올 파일 정보를 인수값으로 넣어준다.
			
//			//3.방법 1 => 읽어올 파일 정보를 문자열로 지정하는 방법
//			FileInputStream fin = new FileInputStream("d:/D_Other/test.txt");	//()안의 파일과 연결된 inputStream객체를 만들어라 라는 뜻
			
			//4.방법2 => 읽어올 파일 정보를 File객체로 지정하는 방법
			File file = new File("d:/D_Other/test.txt");//파일객체가 있다고 가정할때
			FileInputStream fin = new FileInputStream(file);
			
			int c ;	//4-1.읽어올 데이터가 저장될 변수
			while((c=fin.read()) != -1) {//차근차근 하나씩 읽어가기
				// 4-2.읽어온 문자를 화면에 출력하기
				System.out.print((char) c);// char형으로 형변환을 해야 숫자가 나오지 않음
				//출력값 :
				//I want go home => d:/D_Other/test.txt에 쓰여져있는대로 출력 됨
			}
	
			// 4-3.작업 완료 후 스트림 닫기
			fin.close();
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다.");
		}
	}

}
